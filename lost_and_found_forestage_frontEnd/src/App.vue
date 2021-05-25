<template>
  <transition name="fade">
    <router-view v-if="refreshPage === false"></router-view>
  </transition>
</template>

<script>
export default {
  provide() {
    return {
      reload: this.reload,
    };
  },
  data() {
    return {
      refreshPage: false,
    };
  },
  watch: {
    $route: function (to, from) {
      //监听路由是否变化
      if (to.path === from.path) {
        console.log(to);
        this.refreshPage = true;
        setTimeout(() => {
          this.refreshPage = false;
        }, 10);
      }
    },
  },
  methods: {
    reload() {
      this.refreshPage = true;
      this.$nextTick(() => {
        this.refreshPage = false;
      });
    },
  },
};
</script>
