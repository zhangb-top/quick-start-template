<template>
  <div class="customize-content">
    <!-- 提示 -->
    <div class="tip">
      <p>{{msg}}</p>
    </div>
    <!-- 表格 -->
    <el-card class="form-card">
      <div slot="header" class="clearfix">
        <span>定制旅游策略</span>
      </div>
      <el-form ref="formData" :rules="rules" :model="formData">
        <el-form-item prop="content">
          <el-input class="textarea-input" ref="textarea" type="textarea" :autosize="{ minRows: 4}" placeholder="例如：我想去杭州旅游三天两夜，预算2000左右，请问有什么好的安排吗？" resize="none" v-model.trim="formData.content">
          </el-input>
        </el-form-item>
        <div class="form">
          <el-button type="primary" @click="addQuestion('formData')">提交</el-button>
          <el-button @click="resetForm('formData')">重置</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { addQuestionAPI } from '@/api/question'
export default {
  name: 'Customize',
  data() {
    return {
      msg: '根据您的需要，我们将为您定制专属于您的旅游策划，具体答复可以在 "我的-操作-定制" 中进行查看',
      formData: {
        userID: this.$store.state.userInfo.userID,
        content: ''
      },
      rules: {
        content: { required: true, message: '提问不能为空哦，亲~', trigger: 'blur' }
      }
    }
  },
  methods: {
    addQuestion(formName) {
      this.$refs[formName].validate(async valid => {
        if (valid) {
          const { data: res } = await addQuestionAPI(this.formData)
          if (res.code !== 200) return this.$message({ message: res.msg, type: 'error' })
          this.$message({ message: res.msg, type: 'success' })
          this.resetForm(formName)
        } else {
          this.$message({ message: '请填写正确的信息', type: 'error' })
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style lang="scss" scope>
.customize-content {
  margin-top: 60px;
  .tip {
    width: 80%;
    margin: auto;
    padding: 8px 16px;
    background-color: #ecf8ff;
    border-radius: 4px;
    border-left: 5px solid #50bfff;
    p {
      font-size: 14px;
      color: #5e6d82;
      line-height: 1.5em;
    }
  }
  .form-card {
    width: 80%;
    margin: 20px auto;
  }
}
</style>