<template>
  <div class="app-container">
    <el-row :gutter="16">
      <el-col :span="8">
        <el-card shadow="never">
          <div slot="header"><span>单个学员成绩趋势</span></div>
          <el-form label-width="90px" size="small">
            <el-form-item label="学员ID"><el-input v-model="studentQuery.xueyuanId" /></el-form-item>
            <el-form-item label="课程ID"><el-input v-model="studentQuery.kechengId" /></el-form-item>
            <el-form-item>
              <el-button type="primary" @click="loadStudentTrend">生成趋势图</el-button>
            </el-form-item>
          </el-form>
          <div ref="studentChart" style="height:360px;"></div>
        </el-card>
      </el-col>
      <el-col :span="16">
        <el-card shadow="never">
          <div slot="header"><span>课程平均分趋势</span></div>
          <el-form :inline="true" size="small">
            <el-form-item label="课程ID"><el-input v-model="courseId" /></el-form-item>
            <el-form-item><el-button type="primary" @click="loadCourseTrend">生成趋势图</el-button></el-form-item>
          </el-form>
          <div ref="courseChart" style="height:420px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import http from '@/utils/http'
import { pickList } from '@/utils/growthPatch'
export default {
  name: 'ChengJiJiLuTrend',
  data() { return { studentQuery: { xueyuanId: '', kechengId: '' }, courseId: '' } },
  mounted() {
    this.studentChart = echarts.init(this.$refs.studentChart)
    this.courseChart = echarts.init(this.$refs.courseChart)
  },
  methods: {
    async loadStudentTrend() {
      if (!this.studentQuery.xueyuanId) return this.$message.warning('请输入学员ID')
      const res = await http.get(`/chengjijilu/trend/student/${this.studentQuery.xueyuanId}`, { params: { kechengId: this.studentQuery.kechengId || undefined } })
      const list = pickList(res.data.data || res.data)
      this.studentChart.setOption({ tooltip: { trigger: 'axis' }, xAxis: { type: 'category', data: list.map(i => i.kaoshishijian || i.shijian || i.date) }, yAxis: { type: 'value', name: '成绩' }, series: [{ type: 'line', smooth: true, data: list.map(i => i.chengji || i.defen || i.score) }] })
    },
    async loadCourseTrend() {
      if (!this.courseId) return this.$message.warning('请输入课程ID')
      const res = await http.get(`/chengjijilu/trend/course/${this.courseId}`)
      const list = pickList(res.data.data || res.data)
      this.courseChart.setOption({ tooltip: { trigger: 'axis' }, xAxis: { type: 'category', data: list.map(i => i.kaoshishijian || i.shijian || i.date) }, yAxis: { type: 'value', name: '平均分' }, series: [{ type: 'bar', data: list.map(i => i.pingjunfen || i.avgScore || i.value) }] })
    }
  }
}
</script>
