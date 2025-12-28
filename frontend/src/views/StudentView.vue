<template>
  <div>
    <!-- 搜索框 -->
    <div style="margin-bottom: 20px;">
      <el-input v-model="searchName" placeholder="请输入姓名" style="width: 200px; margin-right: 10px;" />
      <el-input v-model="searchPhone" placeholder="请输入电话" style="width: 200px; margin-right: 10px;" />
      <el-button type="primary" @click="searchUsers">搜索</el-button>
      <el-button @click="resetSearch">清空</el-button>
    </div>
    
    <!-- 表格 -->
    <el-table :data="tableData" v-loading="loading" style="width: 100%">
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="name" label="姓名" width="120" />
      <el-table-column prop="username" label="用户名" width="150" />
      <el-table-column prop="email" label="邮箱" width="200" />
      <el-table-column prop="phone" label="电话" width="150" />
      <el-table-column prop="type" label="用户类型" width="120" />
      <el-table-column prop="createTime" label="创建时间" width="180" />
      
      <!-- 操作列 -->
      <el-table-column label="操作" width="180" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageNum"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      style="margin-top: 20px; text-align: center;"
    >
    </el-pagination>
  </div>
</template>

<script>
import request from '@/utils/request'

export default {
  name: 'StudentView',
  data() {
    return {
      tableData: [], // 表格数据
      searchName: '', // 搜索姓名
      searchPhone: '', // 搜索电话
      loading: false, // 加载状态
      pageNum: 1,
      pageSize: 10,
      total: 0
    }
  },
  mounted() {
    // 页面加载时获取数据
    this.searchUsers()
  },
  methods: {
    // 搜索用户
    async searchUsers() {
      this.loading = true
      try {
        const params = {
          name: this.searchName || '',
          phone: this.searchPhone || '',
          pageNum: this.pageNum,
          pageSize: this.pageSize
        }
        
        const response = await request.get('/user/selectAll', { params })
        
        if (Array.isArray(response)) {
          this.tableData = response
          this.total = response.length
        } else if (response && response.list) {
          this.tableData = response.list || []
          this.total = response.total || 0
        } else {
          this.tableData = []
          this.total = 0
        }
        
      } catch (error) {
        console.error('查询失败:', error)
        this.$message.error('查询失败: ' + error.message)
        this.tableData = []
        this.total = 0
      } finally {
        this.loading = false
      }
    },
    
    // 分页处理
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.searchUsers()
    },
    
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.pageNum = 1
      this.searchUsers()
    },
    
    // 重置搜索
    resetSearch() {
      this.searchName = ''
      this.searchPhone = ''
      this.pageNum = 1
      this.searchUsers()
    },
    
    // 编辑用户
    handleEdit(row) {
      console.log('编辑用户:', row)
      this.$message.info('编辑功能待实现')
    },
    
    // 删除用户
    async handleDelete(row) {
      console.log('删除用户:', row)
      
      try {
        // 确认删除
        await this.$confirm(`确定要删除用户 "${row.name}" 吗?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
        
        this.loading = true
        
        // 直接使用最简单的删除方法
        console.log('删除用户ID:', row.id)
        
        // 方法1: 直接调用接口
        const response = await request.delete(`/user/del/${row.id}`)
        console.log('删除响应:', response)
        
        if (response && (response.code === 0 || response.code === '0')) {
          this.$message.success('删除成功')
          // 重新加载数据
          this.searchUsers()
        } else {
          const errorMsg = response?.message || response?.msg || '删除失败'
          this.$message.error('删除失败: ' + errorMsg)
        }
        
      } catch (error) {
        console.error('删除失败:', error)
        
        // 如果是取消操作，不显示错误
        if (error !== 'cancel' && error.message !== 'cancel') {
          this.$message.error('删除失败: ' + error.message)
        }
      } finally {
        this.loading = false
      }
    }
  }
}
</script>

<style scoped>
.el-table {
  margin-top: 20px;
}
</style>