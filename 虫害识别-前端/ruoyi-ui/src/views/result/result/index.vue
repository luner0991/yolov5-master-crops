<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="病害名称" prop="diseaseName">
        <el-input
          v-model="queryParams.diseaseName"
          placeholder="请输入病虫害名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="识别时间" prop="uploadTime">
        <el-date-picker clearable
          v-model="queryParams.uploadTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择识别时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['result:result:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['result:result:edit']"
        >修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['result:result:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['result:result:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="resultList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="resultId" />
      <el-table-column label="病虫害名称" align="center" prop="diseaseName" />
      <el-table-column label="识别结果" align="center" prop="result" show-overflow-tooltip width="130">
        <template slot-scope="scope">
          <el-image v-if="scope.row.result" :src="scope.row.result" style="height:50px" title="点击查看大图" :preview-src-list="[scope.row.result]" />
        </template>
      </el-table-column>
      <el-table-column label="农作物图" align="center" prop="imgPath" show-overflow-tooltip width="130">
        <template slot-scope="scope">
          <el-image v-if="scope.row.imgPath" :src="scope.row.imgPath" style="height:50px" title="点击查看大图" :preview-src-list="[scope.row.imgPath]" />
        </template>
      </el-table-column>
      <el-table-column label="识别时间" align="center" prop="uploadTime" show-overflow-tooltip width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
          <!-- <span>{{ moment(scope.row.uploadTime).format('YYYY-MM-DD HH:mm:ss') }}</span> -->
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['result:result:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['result:result:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改识别记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="使用UUID来命名" prop="pictureName">
          <el-input v-model="form.pictureName" placeholder="请输入使用UUID来命名" />
        </el-form-item>
        <el-form-item label="病虫害名称" prop="diseaseName">
          <el-input v-model="form.diseaseName" placeholder="请输入病虫害名称" />
        </el-form-item>
        <el-form-item label="病虫害ID" prop="diseaseId">
          <el-input v-model="form.diseaseId" placeholder="请输入病虫害ID" />
        </el-form-item>
        <el-form-item label="识别结果" prop="result">
          <el-input v-model="form.result" placeholder="请输入识别结果" />
        </el-form-item>
        <el-form-item label="上传图片" prop="imgPath">
          <el-input v-model="form.imgPath" placeholder="请输入上传图片" />
        </el-form-item>
        <el-form-item label="识别时间" prop="uploadTime">
          <el-date-picker clearable
            v-model="form.uploadTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择识别时间">
          </el-date-picker>
        </el-form-item>
        <el-divider content-position="center">病虫害管理信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddDisease">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteDisease">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="diseaseList" :row-class-name="rowDiseaseIndex" @selection-change="handleDiseaseSelectionChange" ref="disease">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="病害名称" prop="diseaseName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.diseaseName" placeholder="请输入病害名称" />
            </template>
          </el-table-column>
          <el-table-column label="病害介绍" prop="introducement" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.introducement" placeholder="请输入病害介绍" />
            </template>
          </el-table-column>
          <el-table-column label="农作物类型" prop="croptype" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.croptype" placeholder="请选择农作物类型">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listResult, getResult, delResult, addResult, updateResult } from "@/api/result/result";
import moment from'moment';
export default {
  name: "Result",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedDisease: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 识别记录表格数据
      resultList: [],
      // 病虫害管理表格数据
      diseaseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        diseaseName: null,
        uploadTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询识别记录列表 */
    getList() {
      this.loading = true;
      listResult(this.queryParams).then(response => {
        this.resultList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        resultId: null,
        pictureName: null,
        diseaseName: null,
        diseaseId: null,
        result: null,
        status: null,
        imgPath: null,
        uploadTime: null
      };
      this.diseaseList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.resultId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加识别记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const resultId = row.resultId || this.ids
      getResult(resultId).then(response => {
        this.form = response.data;
        this.diseaseList = response.data.diseaseList;
        this.open = true;
        this.title = "修改识别记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.diseaseList = this.diseaseList;
          if (this.form.resultId != null) {
            updateResult(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addResult(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const resultIds = row.resultId || this.ids;
      this.$modal.confirm('是否确认删除识别记录编号为"' + resultIds + '"的数据项？').then(function() {
        return delResult(resultIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 病虫害管理序号 */
    rowDiseaseIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 病虫害管理添加按钮操作 */
    handleAddDisease() {
      let obj = {};
      obj.diseaseName = "";
      obj.introducement = "";
      obj.resolution = "";
      obj.prevention = "";
      obj.croptype = "";
      this.diseaseList.push(obj);
    },
    /** 病虫害管理删除按钮操作 */
    handleDeleteDisease() {
      if (this.checkedDisease.length == 0) {
        this.$modal.msgError("请先选择要删除的病虫害管理数据");
      } else {
        const diseaseList = this.diseaseList;
        const checkedDisease = this.checkedDisease;
        this.diseaseList = diseaseList.filter(function(item) {
          return checkedDisease.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleDiseaseSelectionChange(selection) {
      this.checkedDisease = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('result/result/export', {
        ...this.queryParams
      }, `result_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
