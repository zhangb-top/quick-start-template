const { defineConfig } = require('@vue/cli-service')
const CompressionWebpackPlugin = require('compression-webpack-plugin')

module.exports = defineConfig({
  transpileDependencies: true,
  // 增强 vue-cli 的 webpack 配置项
  configureWebpack: {
    // 打包优化
    externals: {
      // import 时的包名称: window 全局的成员名称
      Vue: 'vue',
      VueRouter: 'vue-router',
      Vuex: 'vuex',
      ElementUI: 'element-ui',
    },
    plugins: [
      // 开启Gzip打包
      new CompressionWebpackPlugin({
        test: /\.js$|\.html$|\.css/,
        threshold: 10240 // 只处理比这个值大的资源。按字节计算 设置的是 10kb
      }),
    ]
  }
})
