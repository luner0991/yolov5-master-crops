<template>
  <div class="app-container">
    <el-form ref="dataForm" :model="dataForm" label-width="100px" style="width:95%;">
      <el-row>
        <el-col :span="24">
          <el-form-item label="农作物类别" prop="cropId">
            <el-select v-model="dataForm.cropId" placeholder="农作物类别" filterable style="width: 100%">
              <el-option v-for="crop in cropList" :key="crop.cropId" :label="crop.cropName" :value="crop.cropId" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="病虫害识别" style="margin: 0;">
            <video v-if="openSuccess" id="videoCamera" autoplay width="260" height="200" style="border: none;" />
          </el-form-item>
        </el-col>
        <el-col :span="3" style="height: 200px;display: flex; justify-content: center;align-items: center;flex-wrap: wrap;">
          <el-button v-if="openSuccess" size="small" type="warning" style="width: 80px; text-align: center;" @click="setImage()">拍照</el-button>
          <el-upload
            class="upload-demo"
            action="#"
            :show-file-list="false"
            :http-request="fileUpload"
          >
            <el-button size="small" type="warning">上传作物图片</el-button>
          </el-upload>
        </el-col>
        <el-col :span="8" style="display: flex; justify-content: center; align-items: center;">
          <canvas id="canvasCamera" style="display:none;" width="640" height="480" />
          <img v-if="imgSrc" :src="imgSrc" alt height="200">
        </el-col>
      </el-row>
      <hr>
      <el-row>
        <el-col :span="12">
          <el-card>
            <div class="text item">
              <b><font color="red">常见问题:</font></b><br><br>
              1、不支持IE浏览器，建议使用火狐、谷歌、Edge浏览器进行拍照<br><br>
              2、若浏览器弹出是否允许调用摄像头，请点击允许<br><br>
              3、<font color="red">必须使用https加密协议，摄像头方能正常使用</font><br><br>
              4、如果问题还存在，请按<a
                href="https://examai.cn/pc/zhinan.pdf"
                target="_blank"
              ><font color="red"><b>启用摄像头操作指南</b></font></a>进行操作><br><br>
              5、本模块仅供测试接口，具体使用请以实际情况为准
            </div>
          </el-card>
        </el-col>
        <el-col :span="8" style="display: flex; justify-content: center; align-items: center;">
          <!-- <img v-if="resImgURL" :src=resImgURL alt height="200"> -->
           <H1>识别结果：</H1>
          <template>
            <el-image v-if="resImgURL" :src="resImgURL" style="height:100%" title="点击查看大图" :preview-src-list="[resImgURL]" />
          </template>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import { cropList, add } from '@/api/pest_identify/pest'
export default {
  data() {
    return {
      visible: false,
      dataForm: {
        cropId: '',
        extName: '',
        fileBase64: ''
      },
      // dataVO: {
      //   identifyResult:'',
      //   diseaseName:''
      // },
      resImgURL:'',
      imgSrc: '',
      cropList: [],
      video: null,
      context: null,
      canvas: null,
      openSuccess: true
    }
  },
  watch: {
    visible(val) {
      if (val) {
        this.imgSrc = ''
        setTimeout(() => {
          this.getCamera()
        }, 1000)
      } else {
        if (this.openSuccess) {
          this.closeCamera()
        }
      }
    }
  },
  created() {
    setTimeout(() => {
      this.getCamera()
    }, 1000)
    this.init()
  },
  methods: {
    init() {
      this.visible = true
      this.resetForm('dataForm')
      cropList().then(res => {
        if (res && res.code === 200) {
          this.cropList = res.data
          console.log(res.data)
          this.dataForm.cropId = this.cropList[0].cropId
        }
      })
    },
    // 表单提交
    dataFormSubmit() {
      if (this.dataForm.cropId === '') {
        this.$message.error('请选择农作物')
        return
      }
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const param = {
            'cropId': this.dataForm.cropId,
            'extName': this.dataForm.extName,
            'fileBase64': this.dataForm.fileBase64
          }

           // 显示加载中
          const loadingInstance = this.$loading({
            text: '正在识别病虫害...',
            background: 'rgba(0, 0, 0, 0.7)'  // 设置背景色，增加用户体验
          });

          add(param).then(res => {
            loadingInstance.close();  // 确保关闭加载动画
            if (res && res.code === 200) {
              // this.dataVO = res.data
              // console.log(this.dataVO)
            this.resImgURL = res.data
              this.$message.success("识别成功")
            } else {
              
              this.$message.error(res)
            }
          }).catch(error => {
            // 请求发生异常时也需要关闭加载动画
            loadingInstance.close();
            this.$message.error('请求失败，请稍后重试');
            console.error(error);  // 输出更多的错误信息，方便调试
          });
        }
      })
    },
    // 打开摄像头
    getCamera() {
      this.video = document.getElementById('videoCamera')
      this.canvas = document.getElementById('canvasCamera')
      this.context = this.canvas.getContext('2d')
      const errocb = () => {
        this.$message.error('摄像头打开失败！')
        this.openSuccess = false
      }
      if (navigator.webkitGetUserMedia) {
        navigator.webkitGetUserMedia({ audio: false, video: true }, (stream) => {
          // video.src=window.URL.createObjectURL(stream);
          this.video.srcObject = stream
          this.video.play()
        }, errocb)
      } else if (navigator.mediaDevices.getUserMedia) {
        // var constraints = { audio: true, video: { width: 1280, height: 720 } };
        const constraints = { audio: false, video: true }
        navigator.mediaDevices.getUserMedia(constraints).then((stream) => {
          // var video = document.querySelector('video');
          this.video.srcObject = stream
          this.video.onloadedmetadata = (e) => {
            this.video.play()
          }
        })
      } else if (navigator.getUserMedia) {
        navigator.getUserMedia({ audio: false, video: true }, (stream) => {
          this.video.src = window.webkitURL.createObjectURL(stream)
          this.video.srcObject = stream
          this.video.play()
        }, errocb)
      } else {
        alert('你的浏览器不支持打开摄像头')
      }
    },
    //  绘制图片（拍照功能）
    setImage() {
      this.context.drawImage(
        this.video,
        0,
        0,
        this.video.videoWidth,
        this.video.videoHeight
      )
      this.imgSrc = this.canvas.toDataURL('image/png')
      this.dataForm.extName = this.imgSrc.substring(this.imgSrc.indexOf('/') + 1, this.imgSrc.indexOf(';'))
      this.dataForm.fileBase64 = this.imgSrc.substring(22)
      this.dataFormSubmit()
    },
    // 关闭摄像头
    closeCamera() {
      this.video.srcObject.getTracks()[0].stop()
    },
    // 关闭弹出层
    closeDialog() {
      this.visible = false
    },
    // 重写文件上传方法
    fileUpload(file) {
      this.fileToBase64(file.file).then(res => {
        // console.log(res)
        this.imgSrc = res
        this.dataForm.extName = res.substring(res.indexOf('/') + 1, res.indexOf(';'))
        const len = 19 + this.dataForm.extName.length
        this.dataForm.fileBase64 = res.substring(len)
        // console.log(this.dataForm.fileBase64)
        this.dataFormSubmit()
      }).catch(err => {
        console.log(err)
        this.$message.error(err)
      })
    },
    // 转base64
    fileToBase64(file) {
      return new Promise((resolve, reject) => {
        const reader = new FileReader()
        let fileResult = ''
        if (file.size > 1024 * 1024) {
          reject('文件大小不能超过1M')
        }
        reader.readAsDataURL(file)
        // 开始转
        reader.onload = () => {
          fileResult = reader.result
          // data:image/jpeg;base64,
        }
        // 转 失败
        reader.onerror = function(error) {
          reject(error)
        }
        // 转 结束  咱就 resolve 出去
        reader.onloadend = function() {
          resolve(fileResult)
        }
      })
    }
  }
}
</script>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
