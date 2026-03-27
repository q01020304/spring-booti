import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
    import news from '@/views/modules/news/list'
    import peixunkecheng from '@/views/modules/peixunkecheng/list'
    import aboutus from '@/views/modules/aboutus/list'
    import zuoyepigai from '@/views/modules/zuoyepigai/list'
    import xuexizhongxin from '@/views/modules/xuexizhongxin/list'
    import xueyuan from '@/views/modules/xueyuan/list'
    import jiaoshi from '@/views/modules/jiaoshi/list'
    import zuoyetijiao from '@/views/modules/zuoyetijiao/list'
    import friendlink from '@/views/modules/friendlink/list'
    import forum from '@/views/modules/forum/list'
    import discusspeixunkecheng from '@/views/modules/discusspeixunkecheng/list'
    import systemintro from '@/views/modules/systemintro/list'
    import kechengfenlei from '@/views/modules/kechengfenlei/list'
    import kehouzuoye from '@/views/modules/kehouzuoye/list'
    import messages from '@/views/modules/messages/list'
    import xuexiziliao from '@/views/modules/xuexiziliao/list'
    import banji from '@/views/modules/banji/list'
    import config from '@/views/modules/config/list'
    import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
    path: '/',
    name: '系统首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '系统首页',
      component: Home,
      meta: {icon:'', title:'center', affix: true}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
      ,{
	path: '/news',
        name: '通知公告',
        component: news
      }
      ,{
	path: '/peixunkecheng',
        name: '培训课程',
        component: peixunkecheng
      }
      ,{
	path: '/aboutus',
        name: '关于我们',
        component: aboutus
      }
      ,{
	path: '/zuoyepigai',
        name: '作业批改',
        component: zuoyepigai
      }
      ,{
	path: '/xuexizhongxin',
        name: '学习中心',
        component: xuexizhongxin
      }
      ,{
	path: '/xueyuan',
        name: '学员',
        component: xueyuan
      }
      ,{
	path: '/jiaoshi',
        name: '教师',
        component: jiaoshi
      }
      ,{
	path: '/zuoyetijiao',
        name: '作业提交',
        component: zuoyetijiao
      }
      ,{
	path: '/friendlink',
        name: '友情链接',
        component: friendlink
      }
      ,{
	path: '/forum',
        name: '论坛交流',
        component: forum
      }
      ,{
	path: '/discusspeixunkecheng',
        name: '培训课程评论',
        component: discusspeixunkecheng
      }
      ,{
	path: '/systemintro',
        name: '系统简介',
        component: systemintro
      }
      ,{
	path: '/kechengfenlei',
        name: '课程分类',
        component: kechengfenlei
      }
      ,{
	path: '/kehouzuoye',
        name: '课后作业',
        component: kehouzuoye
      }
      ,{
	path: '/messages',
        name: '学员反馈',
        component: messages
      }
      ,{
	path: '/xuexiziliao',
        name: '学习资料',
        component: xuexiziliao
      }
      ,{
	path: '/banji',
        name: '班级',
        component: banji
      }
      ,{
	path: '/config',
        name: '轮播图管理',
        component: config
      }
      ,{
	path: '/newstype',
        name: '通知公告分类',
        component: newstype
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}
export default router;
