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
        target: process.env.VUE_APP_BACKEND_URL,
        changeOrigin: true
      },
    }
  }
}