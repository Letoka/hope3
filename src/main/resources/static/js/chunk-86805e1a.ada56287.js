(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-86805e1a"],{3466:function(t,e,i){"use strict";var s=i("1c8b"),n=i("5dfd").findIndex,a=i("258f"),o=i("ff9c"),r="findIndex",c=!0,l=o(r);r in[]&&Array(1)[r]((function(){c=!1})),s({target:"Array",proto:!0,forced:c||!l},{findIndex:function(t){return n(this,t,arguments.length>1?arguments[1]:void 0)}}),a(r)},"5d2e":function(t,e,i){"use strict";var s=i("72e2"),n=i.n(s);n.a},"72e2":function(t,e,i){},"7d55":function(t,e,i){"use strict";i.r(e);var s=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"search"},[i("SearchBar",{on:{searchVal:t.searchVal}},[i("span",{staticClass:"edit-btn",on:{click:t.editHandle}},[t._v("编辑")])]),i("div",{staticClass:"content"},[i("div",{directives:[{name:"show",rawName:"v-show",value:t.cloneData.img,expression:"cloneData.img"}],staticClass:"clone",class:[t.isTreans?"opacity0":""],style:{left:t.cloneData.left+"px",top:t.cloneData.top+"px",transform:t.isTreans?"translate3d("+t.cloneData.transX+"px, "+t.cloneData.transY+"px, 0)":""}},[i("img",{attrs:{src:t.cloneData.img,alt:""}}),i("h5",[t._v(t._s(t.cloneData.title))])]),i("div",{directives:[{name:"show",rawName:"v-show",value:t.isContentShow,expression:"isContentShow"}],staticClass:"app-wrapper"},[i("section",[i("h4",[t._v("我的关注")]),i("ul",{staticClass:"myapp-list",staticStyle:{"border-bottom":"1px solid #EAEAEA"}},[i("vuedraggable",t._b({attrs:{delay:100},model:{value:t.myAppList,callback:function(e){t.myAppList=e},expression:"myAppList"}},"vuedraggable",t.dragOptions,!1),[i("transition-group",{attrs:{type:"transition"}},t._l(t.myAppList,(function(e,s){return i("li",{key:s,class:[t.isCanEdit?"showBorder":"",t.delIndex===s?"trans":"","myapp-item"],on:{click:function(i){return t.toViewDetail(e.url)}}},[i("span",{directives:[{name:"show",rawName:"v-show",value:t.isCanEdit,expression:"isCanEdit"}],staticClass:"add-btn",on:{click:function(e){return e.stopPropagation(),t.delApp(s)}}}),i("img",{attrs:{src:e.icon,alt:""}}),i("h5",[t._v(t._s(e.modulename))])])})),0)],1)],1)]),i("section",[i("h4",[t._v("更多应用")]),i("ul",{staticClass:"more-list"},[i("vuedraggable",t._b({attrs:{sort:!1,handle:".del-btn"},model:{value:t.moreAppList,callback:function(e){t.moreAppList=e},expression:"moreAppList"}},"vuedraggable",t.dragOptions,!1),[i("transition-group",{attrs:{type:"transition"}},t._l(t.moreAppList,(function(e,s){return i("li",{key:s,class:[t.isCanEdit?"showBorder":"",s>3?"margin":"",t.addIndex===s?"trans":""],on:{click:function(i){return t.toViewDetail(e.url)}}},[i("span",{directives:[{name:"show",rawName:"v-show",value:t.isCanEdit,expression:"isCanEdit"}],staticClass:"del-btn",on:{click:function(e){return e.stopPropagation(),t.addApp(s,e)}}}),i("img",{attrs:{src:e.icon,alt:""}}),i("h5",[t._v(t._s(e.modulename))])])})),0)],1)],1)])]),i("div",{directives:[{name:"show",rawName:"v-show",value:t.isCanEdit,expression:"isCanEdit"}],staticClass:"handle-bar"},[i("div",{staticClass:"cancel btn",on:{click:function(e){t.isCanEdit=!1}}},[t._v("取消")]),i("div",{staticClass:"done btn",on:{click:t.complete}},[t._v("完成")])])])],1)},n=[],a=(i("3466"),i("fe59"),i("ecb4"),i("2eeb"),i("ea69"),i("08ba"),i("5172"),i("0a68")),o=i("b07e"),r=i.n(o),c=i("14ac"),l=document.documentElement.getBoundingClientRect().width/3.75,d={name:"Search",components:{SearchBar:c["a"],vuedraggable:r.a},data:function(){return{inputVal:"",isContentShow:!1,isHistoryShow:!0,delIndex:-1,addIndex:-1,isTreans:!1,isCanEdit:!1,myAppList:[],moreAppList:[],cloneData:{},selectId:[]}},created:function(){},mounted:function(){var t=this.$route.params,e=t.selectId,i=void 0===e?[]:e;t.allList;this.selectId=i.map((function(t){return t.moduleid}))},computed:{dragOptions:function(){return{animation:300,group:"description",ghostClass:"ghost"}}},methods:{searchVal:function(t){var e=this,i=this.$route.params.allList,s=void 0===i?[]:i;this.inputVal=t,this.inputVal&&(s.forEach((function(t){-1!==t.modulename.indexOf(e.inputVal)&&(e.isContentShow=!0,"01"===t.wstatus?e.myAppList.push(t):e.moreAppList.push(t))})),0===this.myAppList.length&&0===this.moreAppList.length&&(this.isContentShow=!1))},editHandle:function(){0===this.moreAppList.length&&0===this.myAppList.length||(this.isCanEdit=!0)},addApp:function(t,e){var i=this,s=e.currentTarget.parentNode,n=this.myAppList.length>0?document.querySelectorAll(".myapp-item")[this.myAppList.length-1]:void 0,a=this.moreAppList[t],o=e.currentTarget.parentNode.getBoundingClientRect(),r=this.myAppList.length%4===0,c=n?r?0:n.offsetLeft+o.width-1:0,d=n?r?n.offsetTop+o.height-1:n.offsetTop:.9*l;this.addIndex=t,this.cloneData={img:a.icon,title:a.title,left:s.offsetLeft,top:s.offsetTop,transX:c-s.offsetLeft,transY:d-s.offsetTop},setTimeout((function(){i.isTreans=!0})),setTimeout((function(){i.addIndex=-1,i.moreAppList.splice(t,1),i.myAppList.push(a),i.selectId.push(t),setTimeout((function(){i.cloneData={},i.isTreans=!1}),400)}),400)},delApp:function(t){var e=this;this.delIndex=t;var i=this.myAppList[t],s=this.selectId.findIndex((function(t){return t===i.moduleid}));this.selectId.splice(s,1),setTimeout((function(){e.delIndex=-1,e.myAppList.splice(t,1),e.moreAppList.push(i)}),300)},toViewDetail:function(t){t&&(window.location.href=t)},complete:function(){var t=this;this.$axios.post("/mobile/hopeModule/editModuleSubV2",{aamid:this.$store.state.userInfoObj.aamid,addModules:this.selectId}).then((function(e){200===e.status&&"000000"===e.data.status?(a["a"].success("编辑成功"),setTimeout((function(){t.$router.go(-1)}),300)):a["a"].fail("请重试")})).catch((function(t){a["a"].fail("请重试")}))}}},p=d,u=(i("5d2e"),i("9ca4")),h=Object(u["a"])(p,s,n,!1,null,"09aa78f0",null);e["default"]=h.exports},ecb4:function(t,e,i){"use strict";var s=i("1c8b"),n=i("45af").indexOf,a=i("d7e1"),o=i("ff9c"),r=[].indexOf,c=!!r&&1/[1].indexOf(1,-0)<0,l=a("indexOf"),d=o("indexOf",{ACCESSORS:!0,1:0});s({target:"Array",proto:!0,forced:c||!l||!d},{indexOf:function(t){return c?r.apply(this,arguments)||0:n(this,t,arguments.length>1?arguments[1]:void 0)}})}}]);
//# sourceMappingURL=chunk-86805e1a.ada56287.js.map