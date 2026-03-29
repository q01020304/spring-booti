<template>
  <div class="app-container">
    <el-card shadow="never">
      <div slot="header"><span>成长评价管理</span></div>
      <el-form :inline="true" :model="searchForm" size="small">
        <el-form-item label="学员姓名"><el-input v-model="searchForm.xueyuanxingming" clearable /></el-form-item>
        <el-form-item label="阶段"><el-input v-model="searchForm.jieduan" clearable placeholder="如：第一阶段" /></el-form-item>
        <el-form-item>
          <el-button type="primary" @click="fetchData">查询</el-button>
          <el-button @click="resetSearch">重置</el-button>
          <el-button type="success" @click="handleAdd">新增评价</el-button>
        </el-form-item>
      </el-form>
      <el-table border stripe :data="tableData" v-loading="loading" style="margin-top:12px;">
        <el-table-column prop="xueyuanxingming" label="学员" min-width="110" />
        <el-table-column prop="kechengmingcheng" label="课程" min-width="130" />
        <el-table-column prop="jieduan" label="阶段" width="100" />
        <el-table-column prop="xuexitaidu" label="学习态度" width="100" />
        <el-table-column prop="ketangbiaoxian" label="课堂表现" width="100" />
        <el-table-column prop="zuoyewancheng" label="作业完成" width="100" />
        <el-table-column prop="zonghedefen" label="综合得分" width="100" />
        <el-table-column prop="pingyu" label="评语" min-width="220" show-overflow-tooltip />
        <el-table-column prop="pingjiaren" label="评价人" width="100" />
        <el-table-column prop="pingjiashijian" label="评价时间" width="120" />
        <el-table-column label="操作" width="220" fixed="right">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="text" size="small" @click="loadLatest(scope.row)">查看最新</el-button>
            <el-button type="text" size="small" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="820px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="16">
          <el-col :span="12"><el-form-item label="学员ID" prop="xueyuanId"><el-input v-model="form.xueyuanId" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="学员姓名"><el-input v-model="form.xueyuanxingming" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="课程ID"><el-input v-model="form.kechengId" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="课程名称"><el-input v-model="form.kechengmingcheng" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="阶段" prop="jieduan"><el-input v-model="form.jieduan" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="学习态度"><el-input-number v-model="form.xuexitaidu" :min="0" :max="100" style="width:100%;" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="课堂表现"><el-input-number v-model="form.ketangbiaoxian" :min="0" :max="100" style="width:100%;" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="作业完成"><el-input-number v-model="form.zuoyewancheng" :min="0" :max="100" style="width:100%;" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="综合得分"><el-input-number v-model="form.zonghedefen" :min="0" :max="100" style="width:100%;" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="评价人"><el-input v-model="form.pingjiaren" /></el-form-item></el-col>
          <el-col :span="24"><el-form-item label="评语"><el-input type="textarea" :rows="4" v-model="form.pingyu" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="评价时间"><el-date-picker value-format="yyyy-MM-dd" v-model="form.pingjiashijian" type="date" style="width:100%;" /></el-form-item></el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitForm">保存</el-button>
      </span>
    </el-dialog>

    <el-dialog title="最新评价" :visible.sync="latestVisible" width="560px">
      <el-descriptions :column="1" border v-if="latestRecord">
        <el-descriptions-item label="学员">{{ latestRecord.xueyuanxingming }}</el-descriptions-item>
        <el-descriptions-item label="阶段">{{ latestRecord.jieduan }}</el-descriptions-item>
        <el-descriptions-item label="综合得分">{{ latestRecord.zonghedefen }}</el-descriptions-item>
        <el-descriptions-item label="评语">{{ latestRecord.pingyu }}</el-descriptions-item>
        <el-descriptions-item label="评价人">{{ latestRecord.pingjiaren }}</el-descriptions-item>
        <el-descriptions-item label="评价时间">{{ latestRecord.pingjiashijian }}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>
  </div>
</template>

<script>
import http from '@/utils/http'
import { pickList, pickData, okMessage } from '@/utils/growthPatch'
const defaultForm = () => ({ id: null, xueyuanId: '', xueyuanxingming: '', kechengId: '', kechengmingcheng: '', jieduan: '', xuexitaidu: 80, ketangbiaoxian: 80, zuoyewancheng: 80, zonghedefen: 80, pingyu: '', pingjiaren: '', pingjiashijian: '' })
export default {
  name: 'ChengZhangPingJiaList',
  data() { return { loading: false, saving: false, dialogVisible: false, dialogTitle: '新增评价', latestVisible: false, latestRecord: null, searchForm: { xueyuanxingming: '', jieduan: '' }, form: defaultForm(), tableData: [], rules: { xueyuanId:[{required:true,message:'请输入学员ID',trigger:'blur'}], jieduan:[{required:true,message:'请输入阶段',trigger:'blur'}] } } },
  created() { this.fetchData() },
  methods: {
    async fetchData() { this.loading = true; try { const res = await http.get('/chengzhangpingjia/page', { params: this.searchForm }); this.tableData = pickList(res.data) } finally { this.loading = false } },
    resetSearch() { this.searchForm = { xueyuanxingming: '', jieduan: '' }; this.fetchData() },
    handleAdd() { this.dialogTitle = '新增评价'; this.form = defaultForm(); this.dialogVisible = true },
    handleEdit(row) { this.dialogTitle = '编辑评价'; this.form = Object.assign(defaultForm(), JSON.parse(JSON.stringify(row))); this.dialogVisible = true },
    submitForm() { this.form.zonghedefen = Number(((Number(this.form.xuexitaidu || 0) + Number(this.form.ketangbiaoxian || 0) + Number(this.form.zuoyewancheng || 0)) / 3).toFixed(2)); this.$refs.formRef.validate(async valid => { if (!valid) return; this.saving = true; try { const url = this.form.id ? '/chengzhangpingjia/update' : '/chengzhangpingjia/save'; const res = await http.post(url, this.form); okMessage(this, res, '保存成功'); this.dialogVisible = false; this.fetchData() } finally { this.saving = false } }) },
    async loadLatest(row) { const res = await http.get(`/chengzhangpingjia/latest/${row.xueyuanId}`); this.latestRecord = pickData(res.data); this.latestVisible = true },
    handleDelete(row) { this.$confirm('确定删除该评价记录吗？', '提示', { type: 'warning' }).then(async () => { const res = await http.post('/chengzhangpingjia/delete', [row.id]); okMessage(this, res, '删除成功'); this.fetchData() }).catch(() => {}) }
  }
}
</script>
