<template>
    <!-- 包裹所有内容在一个根元素中 -->
    <div class="login-container">
        <div class="home">
            <el-row type="flex" class="row-bg" justify="center">
                <div class="bg-img"></div>
                <el-col :span="2">
                    <div class="grid-content bg-purple"></div>
                </el-col>
                <el-col :span="10">
                    <div class="login-div">
                        <!-- 登录表单 -->
                        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="80px"
                            class="login-form login-label">
                            <div style="text-align: center">
                                <h1>
                                    <pre>欢迎登录虚拟仿真实训教学管理
    及资源共享云平台管理系统</pre>
                                </h1>
                            </div>
                            <el-form-item label="账户" prop="username" class="my-input">
                                <el-input v-model="ruleForm.username"></el-input>
                            </el-form-item>
                            <el-form-item label="密码" prop="password" class="my-input">
                                <el-input @keyup.enter.native="login('ruleForm')" type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item class="login-btn" label-width="0">
                                <el-button type="primary" @click="login('ruleForm')">登录</el-button>
                                <el-button @click="toRegister()">注册</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-col>
            </el-row>
        </div>
        
        <div class="a_div">
            <router-link to="/register">注册</router-link>
            <a href="/">学校官网</a>
            <a>联系我们</a>
        </div>
    </div>
</template>

<script>
// 引入了组件
import request from '../utils/request.js'

export default {
    data() {
        /* 自定义验证密码规则 */
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('密码不能为空'));
            } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
            }
        };
        return {
            options: [{
                value: 1,
                label: '学生'
            }, {
                value: 2,
                label: '教职工'
            }, {
                value: 3,
                label: '实验室管理员'
            }],
            value: '',
            ruleForm: {
                username: '',
                password: '',
                type: '1'
            },
            rules: {
                password: [
                    //validator: 自定义表单校验规则 trigger: 'blur ：当失去焦点时触发校验
                    { min: 6, max: 12, message: '长度在6到12个字符', trigger: 'blur' },
                    { validator: validatePass, trigger: 'blur' },
                ],

                username: [
                    { required: true, message: '用户名不能为空' }
                ],

            }
        };
    },
    methods: {
        //formName = ruleForm
        login(formName) {
            //刚刚的ref生效
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    //获取select选择器中label的值
                    // let test1 = this.options.find(option => option.value ===  this.ruleForm.type);
                    // console.log(test1.label);
                    //如果校验用户输入的信息都正确,那么可以发送请求访问后台登录接口
                    let params = {
                        username: this.ruleForm.username,
                        password: this.ruleForm.password,
                        type: this.ruleForm.type
                    }
                    request.post('/user/login', params).then(res => {
                        //code为0代表登录成功
                        if (res.code == 0) {
                            //    弹窗提示用户登录成功
                            this.$message.success("登录成功,即将跳转到后台首页")
                            //登录成功得到用户的登录信息，将其保存在浏览器的本地存储中
                            localStorage.setItem("user",JSON.stringify(res.data))

                            // 定时器 2s之后跳转页面
                            setTimeout(() => {
                                this.$router.push('/')
                            }, 2000)

                        } else {
                            //code不为0代表失败，弹出提示信息
                            let msg = res.msg
                            this.$message.error(msg)
                        }
                    })
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        toRegister() {
            this.$router.push('/register')
        }
    }
}
</script>

<style scoped>
.login-container {
    position: relative;
    height: 100vh;
}

.my-input {
    width: 400px;
}

button {
    width: 150px;
}

.login-btn {
    text-align: center;
}

.login-div {
    margin-top: 20%;
    width: 770px;
}

.login-form {
    text-align: center;
    width: 70%;
    background-color: rgba(255, 255, 255, 0.20);
    padding: 30px;
    border-radius: 15px;
}

.el-form-item__label {
    color: azure;
}

/* 修改背景样式 */
.bg-img {
    position: fixed;
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
    /* 使用渐变色背景 */
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    z-index: -10;
}

.login-label .el-form-item__label {
    color: aliceblue;
}

/* 添加响应式设计 */
@media (max-width: 768px) {
    .login-div {
        width: 100%;
        margin-top: 10%;
    }
    
    .login-form {
        width: 90%;
    }
    
    .my-input {
        width: 100%;
    }
}

.home {
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
}

/* 底部链接区域样式 */
.a_div {
    position: fixed;
    bottom: 20px;
    left: 0;
    width: 100%;
    text-align: center;
    z-index: 100;
}

.a_div a, .a_div router-link {
    color: white;
    text-decoration: none;
    margin: 0 15px;
    font-size: 14px;
    padding: 5px 10px;
    border-radius: 4px;
    transition: all 0.3s;
}

.a_div a:hover, .a_div router-link:hover {
    background-color: rgba(255, 255, 255, 0.1);
    text-decoration: underline;
}

.a_div router-link {
    display: inline-block;
}
</style>