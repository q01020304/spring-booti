<template>
  <div class="app-container">
    <el-row :gutter="16">
      <el-col :span="18">
        <el-card shadow="never">
          <div slot="header"><span>报名缴费管理</span></div>
          <el-form :inline="true" :model="searchForm" size="small">
            <el-form-item label="订单号"><el-input v-model="searchForm.dingdanhao" clearable /></el-form-item>
            <el-form-item label="支付状态">
              <el-select v-model="searchForm.zhifuzhuangtai" clearable>
                <el-option label="未支付" value="未支付" />
                <el-option label="已支付" value="已支付" />
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="fetchData">查询</el-button>
              <el-button @click="resetSearch">重置</el-button>
              <el-button type="success" @click="handleAdd">新增报名</el-button>
            </el-form-item>
          </el-form>
          <el-table border stripe :data="tableData" v-loading="loading" style="margin-top:12px;">
            <el-table-column prop="dingdanhao" label="订单号" min-width="160" />
            <el-table-column prop="xueyuanxingming" label="学员" min-width="100" />
            <el-table-column prop="kechengmingcheng" label="课程" min-width="140" />
            <el-table-column prop="baomingriqi" label="报名日期" width="120" />
            <el-table-column prop="yuanjia" label="原价" width="90" />
            <el-table-column prop="youhui" label="优惠" width="90" />
            <el-table-column prop="shifujine" label="实付" width="90" />
            <el-table-column prop="zhifufangshi" label="支付方式" width="110" />
            <el-table-column prop="zhifuzhuangtai" label="状态" width="100">
              <template slot-scope="scope">
                <el-tag :type="scope.row.zhifuzhuangtai === '已支付' ? 'success' : 'warning'">{{ scope.row.zhifuzhuangtai || '未支付' }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="260" fixed="right">
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="handleEdit(scope.row)">编辑</el-button>
                <el-button type="text" size="small" @click="markPaid(scope.row)" :disabled="scope.row.zhifuzhuangtai==='已支付'">标记已缴费</el-button>
                <el-button type="text" size="small" @click="handleDelete(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="never">
          <div slot="header"><span>缴费汇总</span></div>
          <el-descriptions :column="1" border size="small">
            <el-descriptions-item label="订单数">{{ summary.totalCount || 0 }}</el-descriptions-item>
            <el-descriptions-item label="已支付订单">{{ summary.paidCount || 0 }}</el-descriptions-item>
            <el-descriptions-item label="未支付订单">{{ summary.unpaidCount || 0 }}</el-descriptions-item>
            <el-descriptions-item label="实收总额">{{ summary.totalPaidAmount || 0 }}</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>

    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="760px" destroy-on-close>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="16">
          <el-col :span="12"><el-form-item label="学员ID" prop="xueyuanId"><el-input v-model="form.xueyuanId" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="学员姓名"><el-input v-model="form.xueyuanxingming" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="课程ID"><el-input v-model="form.kechengId" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="课程名称"><el-input v-model="form.kechengmingcheng" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="报名日期"><el-date-picker value-format="yyyy-MM-dd" v-model="form.baomingriqi" type="date" style="width:100%;" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="支付方式"><el-select v-model="form.zhifufangshi" style="width:100%;"><el-option label="微信" value="微信" /><el-option label="支付宝" value="支付宝" /><el-option label="现金" value="现金" /><el-option label="银行卡" value="银行卡" /></el-select></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="原价"><el-input-number v-model="form.yuanjia" :min="0" :precision="2" style="width:100%;" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="优惠"><el-input-number v-model="form.youhui" :min="0" :precision="2" style="width:100%;" /></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="实付"><el-input-number v-model="form.shifujine" :min="0" :precision="2" style="width:100%;" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="支付状态"><el-select v-model="form.zhifuzhuangtai" style="width:100%;"><el-option label="未支付" value="未支付" /><el-option label="已支付" value="已支付" /></el-select></el-form-item></el-col>
          <el-col :span="24"><el-form-item label="备注"><el-input type="textarea" :rows="3" v-model="form.beizhu" /></el-form-item></el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitForm">保存</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import http from '@/utils/http'
import { pickList, pickData, okMessage } from '@/utils/growthPatch'
const defaultForm = () => ({ id: null, xueyuanId: '', xueyuanxingming: '', kechengId: '', kechengmingcheng: '', baomingriqi: '', yuanjia: 0, youhui: 0, shifujine: 0, zhifufangshi: '微信', zhifuzhuangtai: '未支付', beizhu: '' })
export default {
  name: 'BaoMingJiaoFeiList',
  data() { return { loading: false, saving: false, dialogVisible: false, dialogTitle: '新增报名', searchForm: { dingdanhao: '', zhifuzhuangtai: '' }, form: defaultForm(), tableData: [], summary: {} , rules: { xueyuanId:[{required:true,message:'请输入学员ID',trigger:'blur'}], kechengId:[{required:true,message:'请输入课程ID',trigger:'blur'}] } } },
  created() { this.fetchData(); this.loadSummary() },
  methods: {
    async fetchData() { this.loading = true; try { const res = await http.get('/baomingjiaofei/page', { params: this.searchForm }); this.tableData = pickList(res.data) } finally { this.loading = false } },
    async loadSummary() { const res = await http.get('/baomingjiaofei/summary'); this.summary = pickData(res.data) },
    resetSearch() { this.searchForm = { dingdanhao: '', zhifuzhuangtai: '' }; this.fetchData() },
    handleAdd() { this.dialogTitle = '新增报名'; this.form = defaultForm(); this.dialogVisible = true },
    handleEdit(row) { this.dialogTitle = '编辑报名'; this.form = Object.assign(defaultForm(), JSON.parse(JSON.stringify(row))); this.dialogVisible = true },
    submitForm() {
      this.$refs.formRef.validate(async valid => {
        if (!valid) return
        this.saving = true
        try {
          const url = this.form.id ? '/baomingjiaofei/update' : '/baomingjiaofei/save'
          const res = await http.post(url, this.form)
          okMessage(this, res, '保存成功')
          this.dialogVisible = false
          this.fetchData(); this.loadSummary()
        } finally { this.saving = false }
      })
    },
    markPaid(row) { this.$confirm('确认将该订单标记为已缴费吗？', '提示', { type: 'warning' }).then(async () => { const res = await http.post(`/baomingjiaofei/markPaid/${row.id}`); okMessage(this, res, '已标记支付'); this.fetchData(); this.loadSummary() }).catch(() => {}) },
    handleDelete(row) { this.$confirm('确定删除该报名记录吗？', '提示', { type: 'warning' }).then(async () => { const res = await http.post('/baomingjiaofei/delete', [row.id]); okMessage(this, res, '删除成功'); this.fetchData(); this.loadSummary() }).catch(() => {}) }
  }
}
</script>
