import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Messages from '../pages/messages/list'
import Forum from '../pages/forum/list'
import ForumAdd from '../pages/forum/add'
import ForumDetail from '../pages/forum/detail'
import MyForumList from '../pages/forum/myForumList'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import xueyuanList from '../pages/xueyuan/list'
import xueyuanDetail from '../pages/xueyuan/detail'
import xueyuanAdd from '../pages/xueyuan/add'
import jiaoshiList from '../pages/jiaoshi/list'
import jiaoshiDetail from '../pages/jiaoshi/detail'
import jiaoshiAdd from '../pages/jiaoshi/add'
import peixunkechengList from '../pages/peixunkecheng/list'
import peixunkechengDetail from '../pages/peixunkecheng/detail'
import peixunkechengAdd from '../pages/peixunkecheng/add'
import xuexizhongxinList from '../pages/xuexizhongxin/list'
import xuexizhongxinDetail from '../pages/xuexizhongxin/detail'
import xuexizhongxinAdd from '../pages/xuexizhongxin/add'
import xuexiziliaoList from '../pages/xuexiziliao/list'
import xuexiziliaoDetail from '../pages/xuexiziliao/detail'
import xuexiziliaoAdd from '../pages/xuexiziliao/add'
import kehouzuoyeList from '../pages/kehouzuoye/list'
import kehouzuoyeDetail from '../pages/kehouzuoye/detail'
import kehouzuoyeAdd from '../pages/kehouzuoye/add'
import zuoyetijiaoList from '../pages/zuoyetijiao/list'
import zuoyetijiaoDetail from '../pages/zuoyetijiao/detail'
import zuoyetijiaoAdd from '../pages/zuoyetijiao/add'
import zuoyepigaiList from '../pages/zuoyepigai/list'
import zuoyepigaiDetail from '../pages/zuoyepigai/detail'
import zuoyepigaiAdd from '../pages/zuoyepigai/add'
import kechengfenleiList from '../pages/kechengfenlei/list'
import kechengfenleiDetail from '../pages/kechengfenlei/detail'
import kechengfenleiAdd from '../pages/kechengfenlei/add'
import banjiList from '../pages/banji/list'
import banjiDetail from '../pages/banji/detail'
import banjiAdd from '../pages/banji/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import aboutusList from '../pages/aboutus/list'
import aboutusDetail from '../pages/aboutus/detail'
import aboutusAdd from '../pages/aboutus/add'
import systemintroList from '../pages/systemintro/list'
import systemintroDetail from '../pages/systemintro/detail'
import systemintroAdd from '../pages/systemintro/add'
import friendlinkList from '../pages/friendlink/list'
import friendlinkDetail from '../pages/friendlink/detail'
import friendlinkAdd from '../pages/friendlink/add'
import discusspeixunkechengList from '../pages/discusspeixunkecheng/list'
import discusspeixunkechengDetail from '../pages/discusspeixunkecheng/detail'
import discusspeixunkechengAdd from '../pages/discusspeixunkecheng/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'messages',
					component: Messages
				},
				{
					path: 'forum',
					component: Forum
				},
				{
					path: 'forumAdd',
					component: ForumAdd
				},
				{
					path: 'forumDetail',
					component: ForumDetail
				},
				{
					path: 'myForumList',
					component: MyForumList
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'xueyuan',
					component: xueyuanList
				},
				{
					path: 'xueyuanDetail',
					component: xueyuanDetail
				},
				{
					path: 'xueyuanAdd',
					component: xueyuanAdd
				},
				{
					path: 'jiaoshi',
					component: jiaoshiList
				},
				{
					path: 'jiaoshiDetail',
					component: jiaoshiDetail
				},
				{
					path: 'jiaoshiAdd',
					component: jiaoshiAdd
				},
				{
					path: 'peixunkecheng',
					component: peixunkechengList
				},
				{
					path: 'peixunkechengDetail',
					component: peixunkechengDetail
				},
				{
					path: 'peixunkechengAdd',
					component: peixunkechengAdd
				},
				{
					path: 'xuexizhongxin',
					component: xuexizhongxinList
				},
				{
					path: 'xuexizhongxinDetail',
					component: xuexizhongxinDetail
				},
				{
					path: 'xuexizhongxinAdd',
					component: xuexizhongxinAdd
				},
				{
					path: 'xuexiziliao',
					component: xuexiziliaoList
				},
				{
					path: 'xuexiziliaoDetail',
					component: xuexiziliaoDetail
				},
				{
					path: 'xuexiziliaoAdd',
					component: xuexiziliaoAdd
				},
				{
					path: 'kehouzuoye',
					component: kehouzuoyeList
				},
				{
					path: 'kehouzuoyeDetail',
					component: kehouzuoyeDetail
				},
				{
					path: 'kehouzuoyeAdd',
					component: kehouzuoyeAdd
				},
				{
					path: 'zuoyetijiao',
					component: zuoyetijiaoList
				},
				{
					path: 'zuoyetijiaoDetail',
					component: zuoyetijiaoDetail
				},
				{
					path: 'zuoyetijiaoAdd',
					component: zuoyetijiaoAdd
				},
				{
					path: 'zuoyepigai',
					component: zuoyepigaiList
				},
				{
					path: 'zuoyepigaiDetail',
					component: zuoyepigaiDetail
				},
				{
					path: 'zuoyepigaiAdd',
					component: zuoyepigaiAdd
				},
				{
					path: 'kechengfenlei',
					component: kechengfenleiList
				},
				{
					path: 'kechengfenleiDetail',
					component: kechengfenleiDetail
				},
				{
					path: 'kechengfenleiAdd',
					component: kechengfenleiAdd
				},
				{
					path: 'banji',
					component: banjiList
				},
				{
					path: 'banjiDetail',
					component: banjiDetail
				},
				{
					path: 'banjiAdd',
					component: banjiAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'aboutus',
					component: aboutusList
				},
				{
					path: 'aboutusDetail',
					component: aboutusDetail
				},
				{
					path: 'aboutusAdd',
					component: aboutusAdd
				},
				{
					path: 'systemintro',
					component: systemintroList
				},
				{
					path: 'systemintroDetail',
					component: systemintroDetail
				},
				{
					path: 'systemintroAdd',
					component: systemintroAdd
				},
				{
					path: 'friendlink',
					component: friendlinkList
				},
				{
					path: 'friendlinkDetail',
					component: friendlinkDetail
				},
				{
					path: 'friendlinkAdd',
					component: friendlinkAdd
				},
				{
					path: 'discusspeixunkecheng',
					component: discusspeixunkechengList
				},
				{
					path: 'discusspeixunkechengDetail',
					component: discusspeixunkechengDetail
				},
				{
					path: 'discusspeixunkechengAdd',
					component: discusspeixunkechengAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
