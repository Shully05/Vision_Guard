const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8080,
    proxy: {
      '/api': {  // 代理所有/api开头的请求
        target: 'http://localhost:8088',
        changeOrigin: true,
        secure: false,
        pathRewrite: {
          '^/api': ''  // 去掉/api前缀
        }
      },
      // 保留原有的/user代理（如果需要）
      '/user': {
        target: 'http://localhost:8088',
        changeOrigin: true,
        secure: false
      }
    }
  }
})