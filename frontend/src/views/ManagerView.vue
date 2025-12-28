<template>
  <div class="about">
    <h1>管理员信息</h1>
    
    <!-- 添加一个表格测试 -->
    <el-table :data="[{name: '张三', role: '管理员'}]" style="width: 100%; margin-top: 20px;">
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="role" label="角色"></el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'ManagerView'
}
</script>