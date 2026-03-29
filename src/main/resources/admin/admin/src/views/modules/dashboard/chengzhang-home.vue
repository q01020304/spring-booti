<template>
  <div class="app-container">
    <el-row :gutter="16">
      <el-col :span="6" v-for="card in cards" :key="card.label">
        <el-card shadow="hover">
          <div style="font-size:14px;color:#909399;">{{ card.label }}</div>
          <div style="font-size:28px;font-weight:700;margin-top:8px;">{{ card.value }}</div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="16" style="margin-top:16px;">
      <el-col :span="12">
        <el-card shadow="never">
          <div slot="header"><span>缴费状态统计</span></div>
          <div ref="paymentChart" style="height:360px;"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="never">
          <div slot="header"><span>考勤状态统计</span></div>
          <div ref="attendanceChart" style="height:360px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import http from '@/utils/http'
import { pickData } from '@/utils/growthPatch'
export default {
  name: 'ChengZhangHome',
  data() { return { cards: [ { label: '学员总数', value: 0 }, { label: '家长总数', value: 0 }, { label: '缴费订单', value: 0 }, { label: '实收总额', value: 0 } ] } },
  mounted() {
    this.paymentChart = echarts.init(this.$refs.paymentChart)
    this.attendanceChart = echarts.init(this.$refs.attendanceChart)
    this.loadSummary()
  },
  methods: {
    async loadSummary() {
      const [studentRes, parentRes, paymentRes, attendanceRes] = await Promise.all([
        http.get('/xueyuan/page', { params: { page: 1, limit: 1 } }).catch(() => ({ data: {} })),
        http.get('/jiazhang/page', { params: { page: 1, limit: 1 } }).catch(() => ({ data: {} })),
        http.get('/baomingjiaofei/summary').catch(() => ({ data: {} })),
        http.get('/kaoqin/summary/0').catch(() => ({ data: {} }))
      ])
      const payment = pickData(paymentRes.data)
      const attendance = pickData(attendanceRes.data)
      this.cards[0].value = (studentRes.data && (studentRes.data.total || (studentRes.data.page && studentRes.data.page.totalCount))) || 0
      this.cards[1].value = (parentRes.data && (parentRes.data.total || (parentRes.data.page && parentRes.data.page.totalCount))) || 0
      this.cards[2].value = payment.totalCount || 0
      this.cards[3].value = payment.totalPaidAmount || 0
      this.paymentChart.setOption({ tooltip: { trigger: 'item' }, series: [{ type: 'pie', radius: ['45%', '70%'], data: [ { name: '已支付', value: payment.paidCount || 0 }, { name: '未支付', value: payment.unpaidCount || 0 } ] }] })
      this.attendanceChart.setOption({ tooltip: { trigger: 'axis' }, xAxis: { type: 'category', data: ['出勤', '迟到', '请假', '缺勤'] }, yAxis: { type: 'value' }, series: [{ type: 'bar', data: [attendance.chuqin || 0, attendance.chidao || 0, attendance.qingjia || 0, attendance.queqin || 0] }] })
    }
  }
}
</script>
