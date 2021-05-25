<template>
  <div
    class="site-wrapper"
    :class="{ 'site-sidebar--fold': sidebarFold }"
    v-loading.fullscreen.lock="loading"
    element-loading-text="拼命加载中"
  ></div>
</template>

<script>
export default {
  provide() {
    return {
      // 刷新
      refresh() {
        this.$store.commit("common/updateContentIsNeedRefresh", true);
        this.$nextTick(() => {
          this.$store.commit("common/updateContentIsNeedRefresh", false);
        });
      },
    };
  },
  data() {
    return {
      loading: true,
    };
  },
  components: {},
  computed: {
    documentClientHeight: {
      get() {
        return this.$store.state.common.documentClientHeight;
      },
      set(val) {
        this.$store.commit("common/updateDocumentClientHeight", val);
      },
    },
    sidebarFold: {
      get() {
        return this.$store.state.common.sidebarFold;
      },
    },
  },
  created() {},
  mounted() {
    this.resetDocumentClientHeight();
  },
  methods: {
    // 重置窗口可视高度
    resetDocumentClientHeight() {
      this.documentClientHeight = document.documentElement["clientHeight"];
      window.onresize = () => {
        this.documentClientHeight = document.documentElement["clientHeight"];
      };
    },
  },
};
</script>