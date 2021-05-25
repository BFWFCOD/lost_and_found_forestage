import Vue from 'vue'
import Router from 'vue-router'
import home from "@/components/home"
import domain from "@/components/domain"
import lostList from '@/components/lost-list';
import foundList from '@/components/found-list';
import page404 from '@/components/404';

// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
const originalReplace = Router.prototype.replace;
Router.prototype.replace = function replace(location) {
  return originalReplace.call(this, location).catch(err => err);
};

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: home,
      children: [
        {
          path: "",
          name: "home",
          component: domain
        },
        {
          path: "domain",
          name: "domain",
          component: domain
        },
        {
          path: "lostlist",
          name: "lostList",
          component: lostList
        },
        {
          path: "foundlist",
          name: "foundList",
          component: foundList
        }
      ]
    },
    {
      path: '/404',
      name: '404',
      component: page404,
      meta: {
        title: '404未找到'
      }
    }
  ]
})
