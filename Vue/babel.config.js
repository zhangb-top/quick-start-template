module.exports = {
  presets: ['@vue/cli-plugin-babel/preset'],
  // 实现路由组件按需导入的 babel 插件
  plugins: ['@babel/plugin-syntax-dynamic-import'],
}
