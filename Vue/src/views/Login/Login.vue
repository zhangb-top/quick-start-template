<template>
  <div class="login-content">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>登录</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="toRegister">没有账号？去注册</el-button>
      </div>
      <el-form :rules="rules" :model="formData" ref="formData" label-width="80px" class="ruleForm" @keyup.enter.native="login('formData')">
        <el-form-item label="用户名" prop="username">
          <el-input v-model.trim="formData.username" type="text" auto-complete="off" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model.trim="formData.password" type="password" auto-complete="off" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="身份" prop="identity">
          <el-select v-model="formData.identity" placeholder="请选择身份">
            <el-option label="用户" :value="2"></el-option>
            <el-option label="管理员" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login('formData')">登录</el-button>
          <el-button @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
// 导入配置好的API文件（返回const数据的，要加{}）
import { loginAPI } from '@/api/userAPI'
import { mapMutations } from 'vuex'
export default {
  name: 'Login',
  data: () => {
    return {
      // 表单数据
      formData: {
        username: '',
        password: '',
        identity: 2
      },
      // 表单验证规则
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
        ],
        identity: { required: true, message: '请选择身份', trigger: 'blur' }
      }
    }
  },
  methods: {
    ...mapMutations(['updateTokenInfo', 'updateUserInfo']),
    // 登录函数
    login(formData) {
      this.$refs[formData].validate(async valid => {
        if (valid) {
          // 从返回的数据中解析data数据，并且重命名为res
          const { data: res } = await loginAPI(this.formData)
          if (res.code !== 200) {
            return this.$message({
              message: res.msg,
              type: 'error'
            })
          } else {
            // 把登录成功的token，存储到 vuex 中
            this.updateTokenInfo(res.data.token)
            // 把登录成功的用户数据保存到 vuex 中
            this.updateUserInfo({ userID: res.data.userID, username: res.data.username, identity: res.data.identity })
            // 登录成功后，跳转到各自的主页
            if (this.formData.identity === 1) this.$router.push('/home')
            else if (this.formData.identity === 2) this.$router.push('/welcome')
          }
        } else {
          this.$message('请填写正确的用户名和密码')
        }
      })
    },
    // 去注册页面
    toRegister() {
      this.$router.push('/register')
    },
    // 重置函数
    reset() {
      this.formData.username = ''
      this.formData.password = ''
      this.formData.identity = ''
    }
  }
}
</script>

<style lang="scss" scope>
.login-content {
  .box-card {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 500px;
  }
}
</style>>