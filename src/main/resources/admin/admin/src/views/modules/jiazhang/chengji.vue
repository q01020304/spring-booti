<template>
  <div class="mod-config">
    <el-table :data="dataList" border style="width: 100%">
      <el-table-column prop="xueyuanxingming" label="学生姓名" align="center" />
      <el-table-column prop="xuehao" label="学号" align="center" />
      <el-table-column prop="kechengmingcheng" label="课程名称" align="center" />
      <el-table-column prop="kaoshileixing" label="考试类型" align="center" />
      <el-table-column prop="chengji" label="成绩" align="center" />
      <el-table-column prop="kaoshishijian" label="考试时间" align="center" width="180" />
      <el-table-column prop="paiming" label="排名" align="center" />
      <el-table-column prop="beizhu" label="备注" align="center" />
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'jiazhang-chengji',
  data() {
    return {
      dataList: []
    }
  },
  mounted() {
    this.getDataList()
  },
  methods: {
    getDataList() {
      const jiazhangId = this.$storage.get('userId')
      this.$http({
        url: `chengjijilu/parentScores/${jiazhangId}`,
        method: 'get'
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data || []
        } else {
          this.dataList = []
          this.$message.error(data.msg || '获取成绩失败')
        }
      }).catch(() => {
        this.dataList = []
        this.$message.error('请求失败')
      })
    }
  }
}
</script>

<style scoped>
.mod-config {
  padding: 20px;
  background: #fff;
}
</style>