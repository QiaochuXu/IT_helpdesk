<template>
  <el-card shadow="never" class="aui-card--fill">
    <div class="app-container">
      <el-form ref="form" :model="dataForm" label-width="120px" :inline="true">
        <el-form-item>
          <el-input v-model="dataForm.name" placeholder="Search in all tickets" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">Query</el-button>
          <el-button type="success" @click="onCancel" round>Open</el-button>
          <el-button type="danger" @click="onCancel" round>Pending</el-button>
          <el-button type="warning" @click="onCancel" round>Unsolved</el-button>
          <el-button type="success" @click="onCancel" round>Spam</el-button>
          <el-button type="info" @click="onCancel" round>Closed</el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="dataListLoading" :data="dataList" border style="width: 100%;">
        <el-table-column prop="QUESTION" label="Assessment Title" show-overflow-tooltip></el-table-column>
        <el-table-column prop="SUBJECT" label="Credit" show-overflow-tooltip></el-table-column>
        <el-table-column prop="AGENT" label="Final Grade" show-overflow-tooltip></el-table-column>
        <el-table-column prop="STATUS" label="Final Grade" show-overflow-tooltip></el-table-column>
        <el-table-column prop="LAST MESSAGE" label="Final Grade" show-overflow-tooltip></el-table-column>
        <el-table-column label="edit" fixed="right" header-align="center" align="center" width="150">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">update</el-button>
            <el-button  type="text" size="small" @click="deleteHandle(scope.row.id)">delete</el-button>
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
    </div>
  </el-card>
</template>

<script>
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
      // 设置属性
      mixinViewModuleOptions: {
        createdIsNeed: true,
        getDataListURL: 'grade/page',
        getDataListIsPage: true
      },
      dataList: [],
      order: '',
      orderField: '',
      page: 1,
      limit: 20,
      total: 0,
      dataListLoading: false,
      dataListSelections: []
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
        if (res.code !== 0) {
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
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

