<template>
  <el-card shadow="never" class="aui-card--fill">
    <div class="app-container">
      <el-form ref="form" :model="dataForm" label-width="120px" :inline="true">
        <el-form-item v-if="this.isAdmins === 1">
          <el-input v-model="dataForm.name" placeholder="Search in all name" clearable></el-input>
        </el-form-item>
        <el-form-item v-if="this.isAdmins === 1">
          <el-button type="primary" @click="getDataList()">Query</el-button>
          <el-button type="primary" @click="addOrUpdateHandle()">Add</el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="dataListLoading" :data="dataList" border style="width: 100%;" v-if="this.isAdmins === 1">
        <el-table-column prop="name" header-align="center" align="center" label="Name" show-overflow-tooltip></el-table-column>
        <el-table-column prop="memo" header-align="center" align="center" label="Description" show-overflow-tooltip></el-table-column>
        <el-table-column label="Edit" fixed="right" header-align="center" align="center" width="150">
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
        @current-change="pageCurrentChangeHandle"
        v-if="this.isAdmins === 1">
      </el-pagination>
      <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
    </div>
  </el-card>
</template>

<script>
import AddOrUpdate from './tag-add-or-update.vue'
import user from "@/store/modules/user";
export default {
  data() {
    return {
      dataForm: {
        name: ''
      },
      // set attributes
      mixinViewModuleOptions: {
        createdIsNeed: true,
        getDataListURL: '/api/tags/page',
        getDataListIsPage: true
      },
      isAdmins: '',
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
    this.isAdmins = user.state.isAdmin
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
    // ps
    pageSizeChangeHandle(val) {
      this.page = 1
      this.limit = val
      this.query()
    },
    // pn
    pageCurrentChangeHandle(val) {
      this.page = val
      this.query()
    },
    getDataList: function() {
      this.page = 1
      this.query()
    },
    addOrUpdateHandle(id) {
      console.log(id)
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.dataForm.id = id
        this.$refs.addOrUpdate.init()
      })
    },
    deleteHandle(id) {
      this.$ajax.delete(`/api/tags/${id}`
      ).then(({ data: res }) => {
        if (res.code !== 20000) {
          return this.$message.error(res.msg)
        }
        this.$message({
          message: 'Succeed!',
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
