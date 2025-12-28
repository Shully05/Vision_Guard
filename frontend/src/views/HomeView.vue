<!-- src/views/HomeView.vue -->
<template>
  <div class="home">
    <el-container>
      <el-header style="background-color: #B3C0D1; height: 80px; line-height: 80px;">
        <img src="@/assets/logo.png" style="width: 60px; height: 60px; position: absolute; left: 10px; top: 10px;">
        <span style="position: absolute; left: 80px; top: 30px; font-size: 18px; font-weight: bold;">
          Vue管理系统
        </span>
      </el-header>
      
      <el-container>
        <el-aside style="background-color: #545c64; width: 200px; min-height: calc(100vh - 80px);">
          <el-menu 
            :default-active="$route.path" 
            router 
            class="el-menu-vertical-demo" 
            background-color="#545c64" 
            text-color="#fff" 
            active-text-color="#ffd04b"
          >
            <el-menu-item index="/">
              <i class="el-icon-menu"></i>
              <span slot="title">系统首页</span>
            </el-menu-item>
            
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>用户管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/manager">管理员信息</el-menu-item>
                <!-- 暂时注释 user 路由，等文件创建后再启用 -->
                <!-- <el-menu-item index="/user">用户信息</el-menu-item> -->
              </el-menu-item-group>
            </el-submenu>
            
            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-setting"></i>
                <span>信息管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/student">学生信息</el-menu-item>
                <el-menu-item index="/teacher">老师信息</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
        
        <el-main style="padding: 20px;">
          <!-- 这里显示子页面内容 -->
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'HomeView',
  data() {
    return {
      user: {
        type: 'role_admin'
      }
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log('打开菜单:', key, keyPath)
    },
    handleClose(key, keyPath) {
      console.log('关闭菜单:', key, keyPath)
    }
  }
}
</script>

<style scoped>
.el-menu {
  border-right: none !important;
}
</style>