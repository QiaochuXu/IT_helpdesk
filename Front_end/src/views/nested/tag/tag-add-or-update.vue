<template>
  <el-dialog :visible.sync="addOrUpdateVisible" :title="!dataForm.id ? 'add' : 'update'" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="dataForm" ref="dataForm" @keyup.enter.native="dataFormSubmitHandle()" :label-width="'120px'">
      <el-form-item label="name" prop="name">
        <el-input v-model="dataForm.name" placeholder="name"></el-input>
      </el-form-item>
      <el-form-item label="memo" prop="memo">
        <el-input v-model="dataForm.memo" placeholder="memo"></el-input>
      </el-form-item>
    </el-form>
    <template slot="footer">
      <el-button @click="addOrUpdateVisible = false">cancel</el-button>
      <el-button type="primary" @click="dataFormSubmitHandle()">commit</el-button>
    </template>
  </el-dialog>
</template>

<script>
import debounce from 'lodash/debounce'
export default {
  data() {
    return {
      addOrUpdateVisible: false,
      dataForm: {
        id: '',
        name: '',
        memo: ''
      }
    }
  },
  methods: {
    init() {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.getInfo()
        }
      })
    },
    // 获取信息
    getInfo() {
      this.$ajax.get(`/api/tags/${this.dataForm.id}/detail`).then(({ data: res }) => {
        if (res.code !== 20000) {
          return this.$message.error(res.msg)
        }
        this.dataForm = {
          ...this.dataForm,
          ...res.data
        }
      }).catch(() => {})
    },
    // 表单提交
    dataFormSubmitHandle: debounce(function() {
      this.$ajax[!this.dataForm.id ? 'post' : 'put']('/api/tags/', this.dataForm).then(({ data: res }) => {
        if (res.code !== 20000) {
          return this.$message.error(res.msg)
        }
        this.$message({
          message: '成功',
          type: 'success',
          duration: 500,
          onClose: () => {
            this.addOrUpdateVisible = false
            this.$emit('refreshDataList')
          }
        })
      }).catch(() => {})
    }, 1000, { 'leading': true, 'trailing': false })
  }
}
</script>

<style scoped>

</style>
