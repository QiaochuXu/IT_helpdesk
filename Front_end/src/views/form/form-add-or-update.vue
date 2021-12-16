<template>
  <el-dialog :visible.sync="addOrUpdateVisible" :title="!dataForm.id ? 'add' : 'update'" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="dataForm" ref="dataForm" @keyup.enter.native="dataFormSubmitHandle()" :label-width="'120px'">
<!--      <el-form-item label="id" prop="id">-->
<!--        <el-input v-model="dataForm.id" placeholder="id" :disabled="true"></el-input>-->
<!--      </el-form-item>-->
      <el-form-item label="Request" prop="questions">
        <el-input v-model="dataForm.questions" placeholder="questions" :disabled="!dataForm.id ? false : true"></el-input>
      </el-form-item>
      <el-form-item label="Response" prop="content">
        <el-input v-model="dataForm.content" placeholder="Response"></el-input>
      </el-form-item>
      <el-form-item label="CreateTime" prop="createTime">
        <el-date-picker
          v-model="dataForm.createTime"
          value-format="yyyy-MM-dd HH:mm:ss"
          type="date"
          placeholder="createTime"
          :disabled="!dataForm.id ? false : true"
        ></el-date-picker>
      </el-form-item>
      <el-form ref="form" :model="dataForm" label-width="120px" :inline="true">
        <el-form-item label="Tag" prop="tagId">
          <el-select v-model="dataForm.tagId" clearable placeholder="Select" >
            <el-option
              v-for="item in tagsList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-form-item label="Status" prop="origin">
        <el-select v-model="dataForm.workStatus" placeholder="STATUS">
          <el-option
            v-for="item in options_workStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Department" prop="orgId">
        <el-select v-model="dataForm.orgId" @change="changeTypeList" placeholder="Select">
          <el-option
            v-for="item in options_orgList"
            :key="item.id"
            :value="item.id"
            :label="item.name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Follower" prop="checkUserId" placeholder="Select">
        <el-select v-model="dataForm.checkUserId" clearable>
          <el-option
            v-for="item in options_userList"
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
import user from "@/store/modules/user"
export default {
  data() {
    return {
      addOrUpdateVisible: false,
      dataForm: {
        id: '',
        questions: '',
        content: '',
        createTime: '',
        workStatus: '',
        orgId: '',
        checkUserId: '',
        userId: '',
        tagId: ''
      },
      options_orgList: [],
      options_userList: [],
      tagsList: [],
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
        label: 'solved'
      }]
    }
  },
  watch: {
    'dataForm.orgId'(e) {
      if (e) {
        this.getUserList()
      }
    },
    'addOrUpdateVisible'(nval) {
      if (nval === false) {
        this.dataForm.orgId = ''
        this.dataForm.workStatus = ''
        this.options_userList = []
        this.tagsList = []
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
      this.getTagsList()
    },
    getTagsList() {
      this.$ajax.get(`/api/tags/getTagsList`).then(({ data: res }) => {
        this.tagsList = res.data
      }).catch(() => {})
    },
    getOrgList() {
      this.$ajax.get(`/api/organization/organizationList`).then(({ data: res }) => {
        this.options_orgList = res.data
      }).catch(() => {})
    },
    getUserList() {
      this.$ajax.get(`/api/user/getUserByOrganization?id=` + this.dataForm.orgId).then(({ data: res }) => {
        this.options_userList = res.data
      }).catch(() => {})
    },
    changeTypeList() {
      this.dataForm.checkUserId = ''
    },
    initProduceList(val) {
      this.options_userList = [{ id: val.id, name: val.name }]
    },
    // get info
    getInfo() {
      this.$ajax.get(`/api/work/${this.dataForm.id}/detail`).then(({ data: res }) => {
        if (res.code !== 20000) {
          return this.$message.error(res.msg)
        }
        this.initProduceList(res.data)
        this.dataForm = {
          ...this.dataForm,
          ...res.data
        }
      }).catch(() => {})
    },
    // submit form
    dataFormSubmitHandle: debounce(function() {
      this.dataForm.userId = user.state.userId
      this.$ajax[!this.dataForm.id ? 'post' : 'put']('/api/work/', this.dataForm).then(({ data: res }) => {
        if (res.code !== 20000) {
          return this.$message.error(res.msg)
        }
        this.$message({
          message: 'Succeed!',
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
