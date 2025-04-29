package com.ruoyi.disease.controller;


import com.ruoyi.disease.domain.Crop;
import com.ruoyi.disease.domain.IdentifyForm;
import com.ruoyi.disease.domain.IdentifyVO;
import com.ruoyi.disease.domain.Result;
import com.ruoyi.disease.service.ICropService;
import com.ruoyi.disease.service.IResultService;
import com.ruoyi.disease.service.impl.ResultServiceImpl;
import com.ruoyi.disease.utils.AliOSSUtil;
import com.ruoyi.disease.utils.Base64Util;
import com.ruoyi.disease.utils.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 病虫害识别Controller
 *
 * @author yzx
 * @date 2025-01-18
 */
@RestController
@RequestMapping("/pest_identify/pest")
public class IdentifyController {

    @Autowired
    private IResultService resultService;
    @Autowired
    private ICropService cropService;

    @GetMapping("/cropList")
    public Res communityList(){
        List<Crop> list = this.cropService.selectCropList(new Crop());
        if(list == null) return Res.error("没有农作物数据");
        return Res.ok().put("data", list);
    }

    @PostMapping("/add")
    public Res add(@RequestBody IdentifyForm identifyForm){

        //图片重命名
        String newFileName = UUID.randomUUID()+"." + identifyForm.getExtName();
        //将Base64编码的文件转换为字节数组
        byte[] buffer = Base64Util.decode(identifyForm.getFileBase64());
        // 将字节数组转换为 InputStream
        InputStream inputStream = new ByteArrayInputStream(buffer);


        String imgPath = ""; //上传图片的访问地址
        String resImgPath = ""; //识别结果图片的本地存储地址

        try {
            //将图片上传到阿里云存储服务器，并返回一个访问地址
            imgPath = AliOSSUtil.uploadFile(newFileName,inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //java调用python 程序 识别上传的图片
        //python  .\detect.py   --source   .\data\images\bus.jpg
        //python执行器                                      filePath
        String PythonPath = "C:\\Users\\Future!\\AppData\\Local\\Microsoft\\WindowsApps\\python";  //注意去找自己的python执行器安装地址
        String PyPath  = "E:\\yolov5-master-crop\\yolov5-master\\detect.py"; //注意找训练好的模型存放路径
        String parma1  = "--source";
        String[] cmds = new String[]{PythonPath,PyPath,parma1,imgPath};  //拼接成命令   //python .\detect.py --source .\data\images\bus.jpg
        System.out.println("正在调用python命令:"+Arrays.toString(cmds)+"........");
        Process pcs = null;
        try {
            pcs = Runtime.getRuntime().exec(cmds);
            //等待 Python 程序执行完成。
            pcs.waitFor();
            // 定义Python脚本的返回值
            String result = null;

            // 获取CMD的返回流
            BufferedInputStream in = new BufferedInputStream(pcs.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            // 读取Python程序的输出
            String lineStr;
            while ((lineStr = br.readLine()) != null) {
                result = lineStr;  // 保存返回值
                System.out.println(result);  // 打印输出
            }
            //字符串拼接出，识别结果图片的存储路径
            resImgPath = PyPath.substring(0,PyPath.lastIndexOf("\\"))+"\\runs\\detect"+result.substring(result.lastIndexOf("\\"))+"\\"+newFileName;
            System.out.println(resImgPath);
            System.out.println("结束");
            // 关闭输入流
            br.close();
            in.close();
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String resImgURL = null; //识别结果图的阿里云访问地址
        try {
            //将识别结果图片上传到阿里云存储服务器，并返回一个访问地址
            InputStream resImgIS= new FileInputStream(new File(resImgPath)) ;
            String resImgName = newFileName.substring(0,newFileName.lastIndexOf("."))+"-res."+identifyForm.getExtName();
            System.out.println(resImgName);
            resImgURL = AliOSSUtil.uploadFile(resImgName, resImgIS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //插入识别记录
        Result result = new Result();
        result.setResult(resImgURL);//识别结果云端访问地址
        result.setImgPath(imgPath);//上传文件的云端访问地址
        result.setStatus("1");

        if(result.getResult() != null && !"".equals(result.getResult().trim())){
            System.out.println("插入数据库的："+result.getResult());
            resultService.insertResult(result);
        }else {
            return Res.error("识别出错了");
        }

        return Res.ok().put("data", result.getResult());
    }
}
