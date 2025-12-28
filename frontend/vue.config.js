const { defineConfig } = require('@vue/cli-service')

const port = '8080'

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8080,
    proxy: {
      '/api': {  // 代理所有/api开头的请求
        target: `http://localhost:${port}`,
        changeOrigin: true,
        secure: false,
        pathRewrite: {
          '^/api': ''  // 去掉/api前缀
        }
      },
      // 保留原有的/user代理（如果需要）
      '/user': {
        target: `http://localhost:${port}`,
        changeOrigin: true,
        secure: false
      }
    }
  }
})