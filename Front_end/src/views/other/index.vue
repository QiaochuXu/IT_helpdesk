<template>
  <el-card shadow="never" class="aui-card--fill">
    <div class="app-container">
      <el-form ref="form" :model="dataForm" label-width="120px" :inline="true">
        <el-form-item>
          <el-input v-model="dataForm.questions" placeholder="Search in all questions" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getDataList()">Query</el-button>
          <el-button type="primary" @click="addOrUpdateHandle()">Add</el-button>
          <el-button type="success" @click="onOpen()" round>Open</el-button>
          <el-button type="danger" @click="onPending()" round>Pending</el-button>
          <el-button type="warning" @click="onUnsolved()" round>Unsolved</el-button>
          <el-button type="success" @click="onSpam()" round>Spam</el-button>
          <el-button type="info" @click="onClosed()" round>Closed</el-button>
          <el-button type="warning" @click="onDelete()" round>Delete</el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="dataListLoading" :data="dataList" border style="width: 100%;">
        <el-table-column prop="userName" header-align="center" align="center" label="REQUESTER" show-overflow-tooltip></el-table-column>
        <el-table-column prop="questions" header-align="center" align="center" label="SUBJECT" show-overflow-tooltip></el-table-column>
        <el-table-column prop="checkName" header-align="center" align="center" label="AGENT" show-overflow-tooltip></el-table-column>
        <el-table-column prop="workStatus" header-align="center" align="center" label="STATUS" show-overflow-tooltip>
          <template slot-scope="scope">
            <span v-if="scope.row.workStatus==0">unsolved</span>
            <span v-if="scope.row.workStatus==1">open</span>
            <span v-if="scope.row.workStatus==2">closed</span>
            <span v-if="scope.row.workStatus==3">pending</span>
            <span v-if="scope.row.workStatus==4">spam</span>
            <span v-if="scope.row.workStatus==5">deleted</span>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" header-align="center" align="center" label="LAST MESSAGE" show-overflow-tooltip></el-table-column>
        <el-table-column label="edit" fixed="right" header-align="center" align="center" width="150">
          <template slot-scope="scope">
            <el-button  type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">update</el-button>
            <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">delete</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        :current-page="page"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="limit"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="pageSizeChangeHandle"
        @current-change="pageCurrentChangeHandle">
      </el-pagination>
      <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
    </div>
  </el-card>
</template>

<script>
import AddOrUpdate from './form-add-or-update.vue'
export default {
  data() {
    return {
      dataForm: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      checkUserId: '',
      // 设置属性
      mixinViewModuleOptions: {
        createdIsNeed: true,
        getDataListURL: '/api/work/page',
        getDataListIsPage: true
      },
      dataList: [],
      order: '',
      orderField: '',
      page: 1,
      limit: 10,
      total: 0,
      dataListLoading: false,
      addOrUpdateVisible: false,
      dataListSelections: []
    }
  },
  created() {
    if (this.mixinViewModuleOptions.createdIsNeed) {
      this.query()
    }
  },
  methods: {
    onSubmit() {
      this.$message('submit!')
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    },
    query() {
      this.dataListLoading = true
      this.$ajax.get(
        this.mixinViewModuleOptions.getDataListURL,
        {
          params: {
            order: this.order,
            orderField: this.orderField,
            page: this.mixinViewModuleOptions.getDataListIsPage ? this.page : null,
            limit: this.mixinViewModuleOptions.getDataListIsPage ? this.limit : null,
            ...this.dataForm
          }
        }
      ).then(({ data: res }) => {
        this.dataListLoading = false
        if (res.code !== 20000) {
          this.dataList = []
          this.total = 0
          return this.$message.error(res.msg)
        }
        this.dataList = this.mixinViewModuleOptions.getDataListIsPage ? res.data.list : res.data
        this.total = this.mixinViewModuleOptions.getDataListIsPage ? res.data.total : 0
      }).catch(() => {
        this.dataListLoading = false
      })
    },
    // 分页, 每页条数
    pageSizeChangeHandle(val) {
      this.page = 1
      this.limit = val
      this.query()
    },
    // 分页, 当前页
    pageCurrentChangeHandle(val) {
      this.page = val
      this.query()
    },
    getDataList: function() {
      this.page = 1
      this.query()
    },
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.dataForm.id = id
        this.$refs.addOrUpdate.init()
      })
    },
    onUnsolved() {
      this.dataForm.workStatus = 0
      this.getDataList()
      this.dataForm.workStatus = ''
    },
    onOpen() {
      this.dataForm.workStatus = 1
      this.getDataList()
      this.dataForm.workStatus = ''
    },
    onClosed() {
      this.dataForm.workStatus = 2
      this.getDataList()
      this.dataForm.workStatus = ''
    },
    onPending() {
      this.dataForm.workStatus = 3
      this.getDataList()
      this.dataForm.workStatus = ''
    },
    onSpam() {
      this.dataForm.workStatus = 4
      this.getDataList()
      this.dataForm.workStatus = ''
    },
    onDelete() {
      this.dataForm.workStatus = 5
      this.getDataList()
      this.dataForm.workStatus = ''
    },
    deleteHandle(id) {
      this.$ajax.delete(`/api/work/${id}`
      ).then(({ data: res }) => {
        if (res.code !== 20000) {
          return this.$message.error(res.msg)
        }
        this.$message({
          message: '成功',
          type: 'success',
          duration: 500,
          onClose: () => {
            this.query()
          }
        })
      }).catch(() => {})
    }
  },
  components: {
    AddOrUpdate
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

