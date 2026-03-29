<template>
  <div class="app-container">
    <el-card shadow="never">
      <div slot="header"><span>家长管理</span></div>
      <el-form :inline="true" :model="searchForm" size="small">
        <el-form-item label="家长姓名">
          <el-input v-model="searchForm.jiazhangxingming" placeholder="请输入家长姓名" clearable />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="searchForm.shouji" placeholder="请输入手机号" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="fetchData">查询</el-button>
          <el-button icon="el-icon-refresh" @click="resetSearch">重置</el-button>
          <el-button type="success" icon="el-icon-plus" @click="handleAdd">新增</el-button>
        </el-form-item>
      </el-form>

      <el-table border stripe :data="tableData" v-loading="loading" style="width:100%;margin-top:12px;">
        <el-table-column prop="jiazhangzhanghao" label="家长账号" min-width="140" />
        <el-table-column prop="jiazhangxingming" label="家长姓名" min-width="120" />
        <el-table-column prop="xingbie" label="性别" width="80" />
        <el-table-column prop="shouji" label="手机号" min-width="140" />
        <el-table-column prop="guanxi" label="与学员关系" min-width="120" />
        <el-table-column prop="beizhu" label="备注" min-width="180" show-overflow-tooltip />
        <el-table-column label="操作" width="220" fixed="right">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="text" size="small" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div style="margin-top:16px;text-align:right;">
        <el-pagination background layout="total, sizes, prev, pager, next, jumper"
          :current-page.sync="pageIndex" :page-size.sync="pageSize" :page-sizes="[10,20,50,100]"
          :total="total" @current-change="fetchData" @size-change="fetchData" />
      </div>
    </el-card>

    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="680px" destroy-on-close>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="16">
          <el-col :span="12">
            <el-form-item label="家长账号" prop="jiazhangzhanghao">
              <el-input v-model="form.jiazhangzhanghao" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="登录密码" prop="mima">
              <el-input v-model="form.mima" show-password />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="家长姓名" prop="jiazhangxingming">
              <el-input v-model="form.jiazhangxingming" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别">
              <el-select v-model="form.xingbie" placeholder="请选择">
                <el-option label="男" value="男" />
                <el-option label="女" value="女" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号" prop="shouji">
              <el-input v-model="form.shouji" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关系">
              <el-input v-model="form.guanxi" placeholder="父亲/母亲/监护人" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input type="textarea" :rows="3" v-model="form.beizhu" />
            </el-form-item>
          </el-col>
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
import { pickList, pickTotal, okMessage } from '@/utils/growthPatch'

const defaultForm = () => ({ id: null, jiazhangzhanghao: '', mima: '', jiazhangxingming: '', xingbie: '男', shouji: '', guanxi: '', beizhu: '' })

export default {
  name: 'JiaZhangList',
  data() {
    return {
      loading: false,
      saving: false,
      dialogVisible: false,
      dialogTitle: '新增家长',
      searchForm: { jiazhangxingming: '', shouji: '' },
      form: defaultForm(),
      tableData: [],
      pageIndex: 1,
      pageSize: 10,
      total: 0,
      rules: {
        jiazhangzhanghao: [{ required: true, message: '请输入家长账号', trigger: 'blur' }],
        mima: [{ required: true, message: '请输入登录密码', trigger: 'blur' }],
        jiazhangxingming: [{ required: true, message: '请输入家长姓名', trigger: 'blur' }],
        shouji: [{ required: true, message: '请输入手机号', trigger: 'blur' }]
      }
    }
  },
  created() { this.fetchData() },
  methods: {
    async fetchData() {
      this.loading = true
      try {
        const res = await http.get('/jiazhang/page', { params: { page: this.pageIndex, limit: this.pageSize, ...this.searchForm } })
        this.tableData = pickList(res.data)
        this.total = pickTotal(res.data)
      } finally { this.loading = false }
    },
    resetSearch() {
      this.searchForm = { jiazhangxingming: '', shouji: '' }
      this.pageIndex = 1
      this.fetchData()
    },
    handleAdd() {
      this.dialogTitle = '新增家长'
      this.form = defaultForm()
      this.dialogVisible = true
    },
    handleEdit(row) {
      this.dialogTitle = '编辑家长'
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    submitForm() {
      this.$refs.formRef.validate(async valid => {
        if (!valid) return
        this.saving = true
        try {
          const url = this.form.id ? '/jiazhang/update' : '/jiazhang/save'
          const res = await http.post(url, this.form)
          okMessage(this, res, '保存成功')
          this.dialogVisible = false
          this.fetchData()
        } finally { this.saving = false }
      })
    },
    handleDelete(row) {
      this.$confirm(`确定删除家长【${row.jiazhangxingming || ''}】吗？`, '提示', { type: 'warning' }).then(async () => {
        const res = await http.post('/jiazhang/delete', [row.id])
        okMessage(this, res, '删除成功')
        this.fetchData()
      }).catch(() => {})
    }
  }
}
</script>
