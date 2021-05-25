<template>
  <div>
    <div>
      <div>
        <h2>400</h2>
        <p>抱歉！您访问的页面<em>失联</em> 啦 ...</p>
        <el-button @click="goBack">返回上一页</el-button>
        <el-button
          type="primary"
          @click="$router.push({ name: 'domain' })"
          >进入首页</el-button
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    username() {
      // 我们很快就会看到 `params` 是什么
      return this.$route.params.username
    }
  },
  methods: {
    goBack() {
      window.history.length > 1 ? this.$router.go(-1) : this.$router.push({name: 'domain'})
    }
  }
}
</script>

<style scoped>
</style>
