<template>
  <div class="kaoshi-page">
    <el-card shadow="never" class="filter-card">
      <div slot="header">考试情况（后端版）</div>
      <el-form :inline="true" :model="query" size="small">
        <el-form-item label="月份">
          <el-date-picker v-model="query.month" type="month" value-format="yyyy-MM" placeholder="选择月份"></el-date-picker>
        </el-form-item>
        <el-form-item label="学员ID">
          <el-input v-model="query.xueyuanId" placeholder="可选"></el-input>
        </el-form-item>
        <el-form-item label="课程ID">
          <el-input v-model="query.kechengId" placeholder="可选"></el-input>
        </el-form-item>
        <el-form-item label="班级">
          <el-input v-model="query.banji" placeholder="可选"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-select v-model="query.kaoshileixing" clearable>
            <el-option label="周测" value="周测"></el-option>
            <el-option label="月测" value="月测"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="fetchAll">查询</el-button>
          <el-button type="success" @click="openDialog()">新增</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-row :gutter="16" class="stat-row">
      <el-col :span="6" v-for="item in cards" :key="item.label">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-label">{{ item.label }}</div>
          <div class="stat-value">{{ item.value }}</div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="16">
      <el-col :span="12"><el-card><div slot="header">成绩趋势</div><div ref="trendChart" style="height:300px"></div></el-card></el-col>
      <el-col :span="12"><el-card><div slot="header">考试类型分布</div><div ref="typeChart" style="height:300px"></div></el-card></el-col>
    </el-row>

    <el-card class="table-card" shadow="never">
      <div slot="header">考试记录</div>
      <el-table :data="tableData" border stripe>
        <el-table-column type="index" width="60" label="序号"></el-table-column>
        <el-table-column prop="xuehao" label="学号" width="120"></el-table-column>
        <el-table-column prop="xueyuanxingming" label="姓名" width="120"></el-table-column>
        <el-table-column prop="banji" label="班级" width="120"></el-table-column>
        <el-table-column prop="kechengmingcheng" label="课程" width="140"></el-table-column>
        <el-table-column prop="kaoshileixing" label="类型" width="100"></el-table-column>
        <el-table-column prop="chengji" label="成绩" width="90"></el-table-column>
        <el-table-column prop="paiming" label="排名" width="90"></el-table-column>
        <el-table-column prop="kaoshiriqi" label="考试日期" min-width="160"></el-table-column>
        <el-table-column prop="beizhu" label="备注" min-width="180"></el-table-column>
        <el-table-column label="操作" width="140" fixed="right">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="openDialog(scope.row)">编辑</el-button>
            <el-button type="text" size="small" @click="removeRow(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog :title="form.id ? '编辑考试记录' : '新增考试记录'" :visible.sync="dialogVisible" width="700px">
      <el-form :model="form" label-width="100px">
        <el-row :gutter="12">
          <el-col :span="12"><el-form-item label="学员ID"><el-input v-model="form.xueyuanid"></el-input></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="学号"><el-input v-model="form.xuehao"></el-input></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="姓名"><el-input v-model="form.xueyuanxingming"></el-input></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="班级"><el-input v-model="form.banji"></el-input></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="课程ID"><el-input v-model="form.kechengid"></el-input></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="课程名"><el-input v-model="form.kechengmingcheng"></el-input></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="考试类型"><el-select v-model="form.kaoshileixing"><el-option label="周测" value="周测"></el-option><el-option label="月测" value="月测"></el-option></el-select></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="考试日期"><el-date-picker v-model="form.kaoshiriqi" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%"></el-date-picker></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="成绩"><el-input-number v-model="form.chengji" :min="0" :max="100" style="width:100%"></el-input-number></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="排名"><el-input-number v-model="form.paiming" :min="1" style="width:100%"></el-input-number></el-form-item></el-col>
          <el-col :span="24"><el-form-item label="备注"><el-input v-model="form.beizhu" type="textarea" :rows="3"></el-input></el-form-item></el-col>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios'
import echarts from 'echarts'

export default {
  data() {
    return {
      query: { month: '2026-03', xueyuanId: '', kechengId: '', banji: '', kaoshileixing: '' },
      tableData: [],
      summary: { totalExamCount: 0, avgScore: 0, zhouceCount: 0, yueceCount: 0, trend: [] },
      dialogVisible: false,
      form: {},
      trendChart: null,
      typeChart: null
    }
  },
  computed: {
    cards() {
      return [
        { label: '考试总次数', value: this.summary.totalExamCount || 0 },
        { label: '平均成绩', value: this.summary.avgScore || 0 },
        { label: '周测次数', value: this.summary.zhouceCount || 0 },
        { label: '月测次数', value: this.summary.yueceCount || 0 }
      ]
    }
  },
  mounted() {
    this.fetchAll()
    this.$nextTick(() => {
      this.trendChart = echarts.init(this.$refs.trendChart)
      this.typeChart = echarts.init(this.$refs.typeChart)
      window.addEventListener('resize', this.handleResize)
    })
  },
  methods: {
    async fetchAll() {
      await Promise.all([this.fetchList(), this.fetchSummary()])
      this.renderCharts()
    },
    async fetchList() {
      const res = await axios.get('/kaoshiqingkuang/list', { params: { page: 1, limit: 200, kaoshileixing: this.query.kaoshileixing, banji: this.query.banji, xueyuanid: this.query.xueyuanId, kechengid: this.query.kechengId } })
      const payload = res.data && res.data.data
      this.tableData = (payload && (payload.list || payload.records)) || payload || []
      if (!Array.isArray(this.tableData)) this.tableData = []
      if (this.query.month) {
        this.tableData = this.tableData.filter(item => String(item.kaoshiriqi || '').slice(0, 7) === this.query.month)
      }
    },
    async fetchSummary() {
      const res = await axios.get('/kaoshiqingkuang/summary', { params: { month: this.query.month, xueyuanId: this.query.xueyuanId || undefined, kechengId: this.query.kechengId || undefined, banji: this.query.banji || undefined } })
      this.summary = (res.data && res.data.data) || {}
    },
    renderCharts() {
      if (!this.trendChart || !this.typeChart) return
      const trend = this.summary.trend || []
      this.trendChart.setOption({ tooltip: { trigger: 'axis' }, xAxis: { type: 'category', data: trend.map(i => i.label) }, yAxis: { type: 'value', min: 0, max: 100 }, series: [{ type: 'line', smooth: true, data: trend.map(i => i.score) }] })
      this.typeChart.setOption({ tooltip: { trigger: 'item' }, series: [{ type: 'pie', radius: ['45%', '70%'], data: [{ name: '周测', value: this.summary.zhouceCount || 0 }, { name: '月测', value: this.summary.yueceCount || 0 }] }] })
    },
    openDialog(row) {
      this.form = row ? JSON.parse(JSON.stringify(row)) : { xueyuanid: '', xuehao: '', xueyuanxingming: '', banji: '', kechengid: '', kechengmingcheng: '', kaoshileixing: '周测', kaoshiriqi: '', chengji: 80, paiming: 1, beizhu: '' }
      this.dialogVisible = true
    },
    async save() {
      const url = this.form.id ? '/kaoshiqingkuang/update' : '/kaoshiqingkuang/save'
      await axios.post(url, this.form)
      this.$message.success('保存成功')
      this.dialogVisible = false
      this.fetchAll()
    },
    async removeRow(row) {
      await this.$confirm('确认删除该记录吗？', '提示', { type: 'warning' })
      await axios.post('/kaoshiqingkuang/delete', [row.id])
      this.$message.success('删除成功')
      this.fetchAll()
    },
    handleResize() {
      this.trendChart && this.trendChart.resize()
      this.typeChart && this.typeChart.resize()
    }
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize)
  }
}
</script>

<style scoped>
.kaoshi-page { padding: 15px; }
.filter-card, .table-card { margin-bottom: 16px; }
.stat-row { margin: 0 0 16px 0; }
.stat-card { text-align: center; }
.stat-label { color: #909399; margin-bottom: 8px; }
.stat-value { font-size: 26px; font-weight: 700; }
</style>
