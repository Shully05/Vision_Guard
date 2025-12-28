<!-- src/views/IndexView.vue -->
<template>
  <div class="index-page">
    <h1>欢迎使用 Vue 管理系统</h1>
    <p>这是一个基于 Vue + Element UI 的后台管理系统</p>
    
    <el-row :gutter="20" style="margin-top: 30px;">
      <el-col :span="8">
        <el-card shadow="hover">
          <div slot="header">系统统计</div>
          <p>用户数量：100</p>
          <p>订单数量：500</p>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover">
          <div slot="header">最新通知</div>
          <p>系统维护通知</p>
          <p>版本更新公告</p>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover">
          <div slot="header">快捷操作</div>
<el-button type="primary" @click="alertAdd">添加</el-button>
          <el-button type="success">查看报表</el-button>
        </el-card>
      </el-col>
    </el-row>
    <!-- 编辑/添加弹出框 -->
        <el-dialog class="dialog-edit" title="修改用户信息" :visible.sync="dialogFormVisible" width="500px">
            <el-form :model="editForm">
                <el-form-item class="user-editInput" label="姓名：" :label-width="formLabelWidth">
                    <el-input v-model="editForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item class="user-editInput" label="用户名：" :label-width="formLabelWidth">
                    <el-input v-model="editForm.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item class="user-editInput" label="密码：" :label-width="formLabelWidth">
                    <el-input v-model="editForm.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item class="user-editInput" label="邮箱：" :label-width="formLabelWidth">
                    <el-input v-model="editForm.email" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item class="user-editInput" label="电话：" :label-width="formLabelWidth">
                    <el-input v-model="editForm.phone" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="用户类型：" :label-width="formLabelWidth">
                    <el-select v-model="editForm.type" placeholder="请选择用户类型">
                        <el-option label="学生" value="role_student"></el-option>
                        <el-option label="教师" value="role_teacher"></el-option>
                        <el-option label="管理员" value="role_admin"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button class="user-btn" @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" class="user-btn" @click="confirmEdit()">确 定</el-button>
            </div>
        </el-dialog>
  </div>
</template>

<script>
import request from '@/utils/request'

export default {
  name: 'IndexView',
  data() {
    return {
      dialogFormVisible: false,
      editForm: {
        name: '',
        username: '',
        password: '',
        email: '',
        phone: '',
        type: ''
      },
      formLabelWidth: '100px'
    }
  },
  methods: {
    alertAdd() {
      this.dialogFormVisible = true;
      this.editForm = {
        name: '',
        username: '',
        password: '',
        email: '',
        phone: '',
        type: ''
      };
    },
    confirmEdit() {
      // 1. 验证表单
      if (!this.editForm.name || !this.editForm.username || !this.editForm.password) {
        this.$message.warning('请填写姓名、用户名和密码');
        return;
      }
      
      // 2. 显示加载
      const loading = this.$loading({
        lock: true,
        text: '正在添加用户...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      
      // 3. 发送请求到后端
      request.post('/user/add', this.editForm)
        .then(response => {
          loading.close();
          
          // 根据Result格式处理响应
          // Result.success()返回的是 { code: 200, message: "成功", data: {...} }
          if (response.code === 200) {
            this.$message({
              message: response.message || '用户添加成功',
              type: 'success',
              duration: 3000
            });
            
            this.dialogFormVisible = false;
            this.editForm = {
              name: '',
              username: '',
              password: '',
              email: '',
              phone: '',
              type: ''
            };
            
            // 可以在这里刷新页面数据或做其他操作
            console.log('添加的用户数据:', response.data);
            
          } else {
            this.$message.error(response.message || '添加失败');
          }
        })
        .catch(error => {
          loading.close();
          console.error('请求失败:', error);
          this.$message.error(error.message || '网络错误，请检查后端服务');
        });
    }
  }
}
</script>