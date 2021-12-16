<template>
  <el-dialog :visible.sync="addOrUpdateVisible" :title="!dataForm.id ? 'add' : 'update'" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="dataForm" ref="dataForm" @keyup.enter.native="dataFormSubmitHandle()" :label-width="'120px'">
      <!--      <el-form-item label="id" prop="id">-->
      <!--        <el-input v-model="dataForm.id" placeholder="id" :disabled="true"></el-input>-->
      <!--      </el-form-item>-->
      <el-form-item label="name" prop="name">
        <el-input v-model="dataForm.name" placeholder="name" :disabled="!dataForm.id ? false : true"></el-input>
      </el-form-item>
      <el-form-item label="age" prop="age">
        <el-input v-model="dataForm.age" placeholder="age" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="EMAIL" prop="email">
        <el-input v-model="dataForm.email" placeholder="email" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="ISADMIN" prop="isAdmin">
        <el-select v-model="dataForm.isAdmin" placeholder="isAdmin">
          <el-option
            v-for="item in options_workStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所属部门" prop="orgId">
        <el-select v-model="dataForm.orgId" placeholder="请选择">
          <el-option
            v-for="item in options_orgList"
            :key="item.id"
            :value="item.id"
            :label="item.name">
          </el-option>
        </el-select>
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
        age: '',
        email: '',
        username: '',
        orgId: '',
        isAdmin: ''
      },
      options_orgList: [],
      options_workStatus: [{
        value: 0,
        label: '普通用户'
      }, {
        value: 1,
        label: '管理员'
      }]
    }
  },
  watch: {
    'addOrUpdateVisible'(nval) {
      if (nval === false) {
        this.dataForm.orgId = ''
        this.dataForm.isAdmin = ''
        this.dataForm.isAdmin = ''
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
      this.getOrgList()
    },
    getOrgList() {
      this.$ajax.get(`/api/organization/organizationList`).then(({ data: res }) => {
        this.options_orgList = res.data
      }).catch(() => {})
    },
    // 获取信息
    getInfo() {
      this.$ajax.get(`/api/user/${this.dataForm.id}/detail`).then(({ data: res }) => {
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
      this.$ajax[!this.dataForm.id ? 'post' : 'put']('/api/user/', this.dataForm).then(({ data: res }) => {
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
