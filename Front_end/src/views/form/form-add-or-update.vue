<template>
  <el-dialog :visible.sync="addOrUpdateVisible" :title="!dataForm.id ? 'add' : 'update'" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="dataForm" ref="dataForm" @keyup.enter.native="dataFormSubmitHandle()" :label-width="'120px'">
<!--      <el-form-item label="id" prop="id">-->
<!--        <el-input v-model="dataForm.id" placeholder="id" :disabled="true"></el-input>-->
<!--      </el-form-item>-->
      <el-form-item label="questions" prop="questions">
        <el-input v-model="dataForm.questions" placeholder="questions" :disabled="!dataForm.id ? false : true"></el-input>
      </el-form-item>
      <el-form-item label="content" prop="content">
        <el-input v-model="dataForm.content" placeholder="content"></el-input>
      </el-form-item>
      <el-form-item label="createTime" prop="createTime">
        <el-date-picker
          v-model="dataForm.createTime"
          value-format="yyyy-MM-dd HH:mm:ss"
          type="date"
          placeholder="createTime"
          :disabled="!dataForm.id ? false : true"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="STATUS" prop="origin">
        <el-select v-model="dataForm.workStatus" placeholder="STATUS">
          <el-option
            v-for="item in options_workStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
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
        questions: '',
        content: '',
        createTime: '',
        workStatus: ''
      },
      options_workStatus: [{
        value: 0,
        label: 'unsolved'
      }, {
        value: 1,
        label: 'open'
      }, {
        value: 2,
        label: 'closed'
      }, {
        value: 3,
        label: 'pending'
      }, {
        value: 4,
        label: 'spam'
      }, {
        value: 5,
        label: 'deleted'
      }]
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
      this.$ajax.get(`/api/work/${this.dataForm.id}/detail`).then(({ data: res }) => {
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
      this.$ajax[!this.dataForm.id ? 'post' : 'put']('/api/work/', this.dataForm).then(({ data: res }) => {
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
