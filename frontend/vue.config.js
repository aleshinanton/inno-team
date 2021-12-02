module.exports = {
  css: {
    loaderOptions: {
      scss: {
        additionalData: `@import "~@/assets/scss/main.scss";`
      }
    }
  },
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://localhost:9090',
        changeOrigin: true
      },
    }
  }
}