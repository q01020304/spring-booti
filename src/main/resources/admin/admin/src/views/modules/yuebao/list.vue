<template>
  <div class="yuebao-page">
    <el-card shadow="never" class="filter-card">
      <div slot="header">月报中心（后端版）</div>
      <el-form :inline="true" :model="query" size="small">
        <el-form-item label="月份">
          <el-date-picker v-model="query.month" type="month" value-format="yyyy-MM" placeholder="选择月份"></el-date-picker>
        </el-form-item>
        <el-form-item label="学员ID"><el-input v-model="query.xueyuanId"></el-input></el-form-item>
        <el-form-item label="课程ID"><el-input v-model="query.kechengId"></el-input></el-form-item>
        <el-form-item label="班级"><el-input v-model="query.banji"></el-input></el-form-item>
        <el-form-item label="生成人"><el-input v-model="query.shengchengren"></el-input></el-form-item>
        <el-form-item>
          <el-button type="primary" @click="generate">生成并保存月报</el-button>
          <el-button @click="loadHistory">查看历史月报</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-row :gutter="16" class="card-row">
      <el-col :span="6" v-for="card in cards" :key="card.label">
        <el-card class="card-item" shadow="hover">
          <div class="label">{{ card.label }}</div>
          <div class="value">{{ card.value }}</div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="16">
      <el-col :span="12"><el-card><div slot="header">考试趋势</div><div ref="scoreChart" style="height:300px"></div></el-card></el-col>
      <el-col :span="12"><el-card><div slot="header">考勤结构</div><div ref="attendanceChart" style="height:300px"></div></el-card></el-col>
    </el-row>

    <el-card class="report-card" shadow="never">
      <div slot="header">自动生成月报</div>
      <el-descriptions :column="2" border>
        <el-descriptions-item label="月份">{{ report.month || '-' }}</el-descriptions-item>
        <el-descriptions-item label="阶段评价分">{{ report.evaluationScore || 0 }}</el-descriptions-item>
        <el-descriptions-item label="应缴费">{{ payment.due || 0 }}</el-descriptions-item>
        <el-descriptions-item label="实缴费">{{ payment.paid || 0 }}</el-descriptions-item>
        <el-descriptions-item label="月报摘要" :span="2">{{ report.summaryText || '暂无' }}</el-descriptions-item>
        <el-descriptions-item label="月报建议" :span="2">{{ report.suggestionText || '暂无' }}</el-descriptions-item>
      </el-descriptions>
    </el-card>

    <el-card shadow="never">
      <div slot="header">月报历史</div>
      <el-table :data="history" border stripe>
        <el-table-column type="index" width="60" label="序号"></el-table-column>
        <el-table-column prop="yuefen" label="月份" width="100"></el-table-column>
        <el-table-column prop="xueyuanid" label="学员ID" width="100"></el-table-column>
        <el-table-column prop="kechengid" label="课程ID" width="100"></el-table-column>
        <el-table-column prop="pingjunchengji" label="平均成绩" width="100"></el-table-column>
        <el-table-column prop="zhoucecishu" label="周测" width="90"></el-table-column>
        <el-table-column prop="yuececishu" label="月测" width="90"></el-table-column>
        <el-table-column prop="yuebaozhaiyao" label="摘要" min-width="280" show-overflow-tooltip></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios'
import echarts from 'echarts'

export default {
  data() {
    return {
      query: { month: '2026-03', xueyuanId: '1', kechengId: '101', banji: '启航一班', shengchengren: '管理员' },
      report: {},
      history: [],
      scoreChart: null,
      attendanceChart: null
    }
  },
  computed: {
    cards() {
      const exam = this.report.exam || {}
      const attendance = this.report.attendance || {}
      return [
        { label: '考试总次数', value: exam.totalExamCount || 0 },
        { label: '平均成绩', value: exam.avgScore || 0 },
        { label: '出勤次数', value: attendance.present || 0 },
        { label: '迟到/缺勤', value: `${attendance.late || 0}/${attendance.absent || 0}` }
      ]
    },
    payment() {
      return this.report.payment || {}
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.scoreChart = echarts.init(this.$refs.scoreChart)
      this.attendanceChart = echarts.init(this.$refs.attendanceChart)
      window.addEventListener('resize', this.handleResize)
      this.loadHistory()
    })
  },
  methods: {
    async generate() {
      const res = await axios.get('/yuebao/generate', { params: { month: this.query.month, xueyuanId: this.query.xueyuanId || undefined, kechengId: this.query.kechengId || undefined, banji: this.query.banji || undefined, shengchengren: this.query.shengchengren || undefined } })
      this.report = (res.data && res.data.data) || {}
      this.renderCharts()
      this.loadHistory()
      this.$message.success('月报已生成并保存')
    },
    async loadHistory() {
      const res = await axios.get('/yuebao/list', { params: { page: 1, limit: 50, yuefen: this.query.month, xueyuanid: this.query.xueyuanId || undefined } })
      const payload = res.data && res.data.data
      this.history = (payload && (payload.list || payload.records)) || payload || []
      if (!Array.isArray(this.history)) this.history = []
    },
    renderCharts() {
      if (!this.scoreChart || !this.attendanceChart) return
      const trend = (this.report.exam && this.report.exam.trend) || []
      const attendance = this.report.attendance || {}
      this.scoreChart.setOption({ tooltip: { trigger: 'axis' }, xAxis: { type: 'category', data: trend.map(i => i.label) }, yAxis: { type: 'value', min: 0, max: 100 }, series: [{ type: 'line', smooth: true, data: trend.map(i => i.score) }] })
      this.attendanceChart.setOption({ tooltip: { trigger: 'item' }, series: [{ type: 'pie', radius: ['45%', '68%'], data: [
        { name: '出勤', value: attendance.present || 0 },
        { name: '迟到', value: attendance.late || 0 },
        { name: '请假', value: attendance.leave || 0 },
        { name: '缺勤', value: attendance.absent || 0 }
      ] }] })
    },
    handleResize() {
      this.scoreChart && this.scoreChart.resize()
      this.attendanceChart && this.attendanceChart.resize()
    }
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize)
  }
}
</script>

<style scoped>
.yuebao-page { padding: 15px; }
.filter-card, .report-card { margin-bottom: 16px; }
.card-row { margin: 0 0 16px 0; }
.card-item { text-align: center; }
.label { color: #909399; margin-bottom: 8px; }
.value { font-size: 26px; font-weight: 700; }
</style>
