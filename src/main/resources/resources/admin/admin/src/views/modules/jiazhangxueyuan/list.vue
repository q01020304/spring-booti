<template>
  <div class="app-container">
    <el-row :gutter="16">
      <el-col :span="14">
        <el-card shadow="never">
          <div slot="header"><span>家长-学员绑定管理</span></div>
          <el-form :inline="true" :model="searchForm" size="small">
            <el-form-item label="家长ID"><el-input v-model="searchForm.jiazhangId" clearable /></el-form-item>
            <el-form-item label="学员ID"><el-input v-model="searchForm.xueyuanId" clearable /></el-form-item>
            <el-form-item>
              <el-button type="primary" @click="fetchData">查询</el-button>
              <el-button @click="resetSearch">重置</el-button>
              <el-button type="success" @click="openBind">新增绑定</el-button>
            </el-form-item>
          </el-form>
          <el-table border stripe :data="tableData" v-loading="loading" style="margin-top:12px;">
            <el-table-column prop="jiazhangId" label="家长ID" width="100" />
            <el-table-column prop="jiazhangxingming" label="家长姓名" min-width="120" />
            <el-table-column prop="xueyuanId" label="学员ID" width="100" />
            <el-table-column prop="xueyuanxingming" label="学员姓名" min-width="120" />
            <el-table-column prop="guanxi" label="关系" min-width="100" />
            <el-table-column prop="beizhu" label="备注" min-width="160" show-overflow-tooltip />
            <el-table-column label="操作" width="180">
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="removeBind(scope.row)">解除绑定</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="10">
        <el-card shadow="never">
          <div slot="header"><span>按家长查看名下学员</span></div>
          <el-input v-model="childrenParentId" placeholder="请输入家长ID" style="margin-bottom:12px;">
            <el-button slot="append" type="primary" @click="loadChildren">查询</el-button>
          </el-input>
          <el-table border stripe :data="childrenData">
            <el-table-column prop="xueyuanId" label="学员ID" width="90" />
            <el-table-column prop="xueyuanxingming" label="学员姓名" min-width="100" />
            <el-table-column prop="guanxi" label="关系" min-width="100" />
          </el-table>
        </el-card>
      </el-col>
    </el-row>

    <el-dialog title="新增绑定" :visible.sync="dialogVisible" width="600px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="家长ID" prop="jiazhangId"><el-input v-model="form.jiazhangId" /></el-form-item>
        <el-form-item label="家长姓名"><el-input v-model="form.jiazhangxingming" /></el-form-item>
        <el-form-item label="学员ID" prop="xueyuanId"><el-input v-model="form.xueyuanId" /></el-form-item>
        <el-form-item label="学员姓名"><el-input v-model="form.xueyuanxingming" /></el-form-item>
        <el-form-item label="关系"><el-input v-model="form.guanxi" /></el-form-item>
        <el-form-item label="备注"><el-input type="textarea" :rows="3" v-model="form.beizhu" /></el-form-item>
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
import { pickList, okMessage } from '@/utils/growthPatch'

const defaultForm = () => ({ jiazhangId: '', jiazhangxingming: '', xueyuanId: '', xueyuanxingming: '', guanxi: '', beizhu: '' })

export default {
  name: 'JiaZhangXueYuanList',
  data() {
    return {
      loading: false,
      saving: false,
      dialogVisible: false,
      searchForm: { jiazhangId: '', xueyuanId: '' },
      form: defaultForm(),
      tableData: [],
      childrenParentId: '',
      childrenData: [],
      rules: {
        jiazhangId: [{ required: true, message: '请输入家长ID', trigger: 'blur' }],
        xueyuanId: [{ required: true, message: '请输入学员ID', trigger: 'blur' }]
      }
    }
  },
  created() { this.fetchData() },
  methods: {
    async fetchData() {
      this.loading = true
      try {
        const res = await http.get('/jiazhangxueyuan/page', { params: this.searchForm })
        this.tableData = pickList(res.data)
      } finally { this.loading = false }
    },
    resetSearch() { this.searchForm = { jiazhangId: '', xueyuanId: '' }; this.fetchData() },
    openBind() { this.form = defaultForm(); this.dialogVisible = true },
    submitForm() {
      this.$refs.formRef.validate(async valid => {
        if (!valid) return
        this.saving = true
        try {
          const res = await http.post('/jiazhangxueyuan/save', this.form)
          okMessage(this, res, '绑定成功')
          this.dialogVisible = false
          this.fetchData()
        } finally { this.saving = false }
      })
    },
    removeBind(row) {
      this.$confirm('确定解除该绑定关系吗？', '提示', { type: 'warning' }).then(async () => {
        const res = await http.post('/jiazhangxueyuan/delete', [row.id])
        okMessage(this, res, '解除成功')
        this.fetchData()
      }).catch(() => {})
    },
    async loadChildren() {
      if (!this.childrenParentId) return this.$message.warning('请先输入家长ID')
      const res = await http.get(`/jiazhangxueyuan/children/${this.childrenParentId}`)
      this.childrenData = pickList(res.data.data || res.data)
    }
  }
}
</script>
