(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6e7880e1"],{"4f0a":function(t,e,i){},ac7b:function(t,e,i){"use strict";i.r(e);var s=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"my-concerns"},[i("div",{staticClass:"top-fixed"},[i("div",{staticClass:"back",on:{click:t.back}}),i("div",{staticClass:"search",on:{click:t.searchClick}}),i("span",{staticClass:"edit-btn",on:{click:t.editHandle}},[t._v("编辑")])]),i("div",{staticClass:"content"},[i("div",{directives:[{name:"show",rawName:"v-show",value:t.cloneData.img,expression:"cloneData.img"}],staticClass:"clone",class:[t.isTreans?"opacity0":""],style:{left:t.cloneData.left+"px",top:t.cloneData.top+"px",transform:t.isTreans?"translate3d("+t.cloneData.transX+"px, "+t.cloneData.transY+"px, 0)":""}},[i("img",{attrs:{src:t.cloneData.img,alt:""}}),i("h5",[t._v(t._s(t.cloneData.title))])]),i("section",[i("h4",[t._v("我的关注")]),i("ul",{staticClass:"myapp-list",staticStyle:{"border-bottom":"1px solid #EAEAEA"}},[i("vuedraggable",t._b({attrs:{delay:100},model:{value:t.myAppList,callback:function(e){t.myAppList=e},expression:"myAppList"}},"vuedraggable",t.dragOptions,!1),[i("transition-group",{attrs:{type:"transition"}},t._l(t.myAppList,(function(e,s){return i("li",{key:s,class:[t.isCanEdit?"showBorder":"",t.delIndex===s?"trans":"","myapp-item"],on:{click:function(i){return t.toViewDetail(e.url)}}},[i("span",{directives:[{name:"show",rawName:"v-show",value:t.isCanEdit,expression:"isCanEdit"}],staticClass:"add-btn",on:{click:function(e){return e.stopPropagation(),t.delApp(s)}}}),i("img",{attrs:{src:e.icon,alt:""}}),i("h5",[t._v(t._s(e.modulename))])])})),0)],1)],1)]),i("section",[i("h4",[t._v("更多应用")]),i("ul",{staticClass:"more-list"},[i("vuedraggable",t._b({attrs:{sort:!1,handle:".del-btn"},model:{value:t.moreAppList,callback:function(e){t.moreAppList=e},expression:"moreAppList"}},"vuedraggable",t.dragOptions,!1),[i("transition-group",{attrs:{type:"transition"}},t._l(t.moreAppList,(function(e,s){return i("li",{key:s,class:[t.isCanEdit?"showBorder":"",s>3?"margin":"",t.addIndex===s?"trans":""],on:{click:function(i){return t.toViewDetail(e.url)}}},[i("span",{directives:[{name:"show",rawName:"v-show",value:t.isCanEdit,expression:"isCanEdit"}],staticClass:"del-btn",on:{click:function(e){return e.stopPropagation(),t.addApp(s,e)}}}),i("img",{attrs:{src:e.icon,alt:""}}),i("h5",[t._v(t._s(e.modulename))])])})),0)],1)],1)])]),i("div",{directives:[{name:"show",rawName:"v-show",value:t.isCanEdit,expression:"isCanEdit"}],staticClass:"handle-bar"},[i("div",{staticClass:"cancel btn",on:{click:function(e){t.isCanEdit=!1}}},[t._v("取消")]),i("div",{staticClass:"done btn",on:{click:t.complete}},[t._v("完成")])])])},a=[],n=(i("fe59"),i("2eeb"),i("ea69"),i("08ba"),i("5172"),i("0a68")),o=i("b07e"),c=i.n(o),r=i("14ac"),l=document.documentElement.getBoundingClientRect().width/3.75,p={name:"MyConcerns",components:{vuedraggable:c.a,SearchBar:r["a"]},data:function(){return{inputVal:"",delIndex:-1,addIndex:-1,isTreans:!1,isCanEdit:!1,myAppList:[],moreAppList:[],cloneData:{}}},mounted:function(){this.getList()},computed:{dragOptions:function(){return{animation:300,group:"description",ghostClass:"ghost"}}},methods:{back:function(){this.$router.go(-1)},editHandle:function(){0===this.moreAppList.length&&0===this.myAppList.length||(this.isCanEdit=!0)},searchClick:function(){var t=this.myAppList.map((function(t){return t.moduleid}));this.$router.push({name:"MyConcernsSearch",params:{selectId:t,allList:this.originList}})},getList:function(){var t=this;this.$axios.post("/hopeModule/queryModuleSub",{aamid:"010203",deptid:"0010101032",odeptid:"0010100003"}).then((function(e){if(200===e.status&&"000000"===e.data.status){var i=e.data.data||[],s=[],a=[];i.forEach((function(t){"01"===t.wstatus?s.push(t):a.push(t)})),t.moreAppList=a,t.myAppList=s.sort((function(t,e){return t.sequence-e.sequence})),t.originList=i}})).catch((function(t){}))},addApp:function(t,e){var i=this,s=e.currentTarget.parentNode,a=this.myAppList.length>0?document.querySelectorAll(".myapp-item")[this.myAppList.length-1]:void 0,n=this.moreAppList[t],o=e.currentTarget.parentNode.getBoundingClientRect(),c=this.myAppList.length%4===0,r=a?c?0:a.offsetLeft+o.width-1:0,p=a?c?a.offsetTop+o.height-1:a.offsetTop:.9*l;this.addIndex=t,this.cloneData={img:n.icon,title:n.title,left:s.offsetLeft,top:s.offsetTop,transX:r-s.offsetLeft,transY:p-s.offsetTop},setTimeout((function(){i.isTreans=!0})),setTimeout((function(){i.addIndex=-1,i.moreAppList.splice(t,1),i.myAppList.push(n),setTimeout((function(){i.cloneData={},i.isTreans=!1}),400)}),400)},delApp:function(t){var e=this;this.delIndex=t;var i=this.myAppList[t];setTimeout((function(){e.delIndex=-1,e.myAppList.splice(t,1),e.moreAppList.push(i)}),300)},toViewDetail:function(t){t&&(window.location.href=t)},complete:function(){var t=this,e=this.myAppList.map((function(t){return t.moduleid}));this.$axios.post("/hopeModule/editModuleSubV2",{aamid:"010203",addModules:e}).then((function(e){200===e.status&&"000000"===e.data.status?(n["a"].success("编辑成功"),t.isCanEdit=!1):n["a"].fail("请重试")})).catch((function(t){n["a"].fail("请重试")}))}}},d=p,u=(i("c67b"),i("9ca4")),m=Object(u["a"])(d,s,a,!1,null,"ec6d30c0",null);e["default"]=m.exports},c67b:function(t,e,i){"use strict";var s=i("4f0a"),a=i.n(s);a.a}}]);
//# sourceMappingURL=chunk-6e7880e1.20ced1c6.js.map