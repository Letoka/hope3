(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-f55dcfe8"],{"0d26":function(t,e,i){},"7d55":function(t,e,i){"use strict";i.r(e);var s=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"search"},[i("SearchBar",{on:{searchVal:t.searchVal}},[i("span",{staticClass:"edit-btn",on:{click:t.editHandle}},[t._v("编辑")])]),i("p",{directives:[{name:"show",rawName:"v-show",value:t.isEmpty,expression:"isEmpty"}],staticClass:"empty-tip"},[t._v("搜索结果为空")]),i("div",{staticClass:"content"},[i("div",{directives:[{name:"show",rawName:"v-show",value:t.cloneData.img,expression:"cloneData.img"}],staticClass:"clone",class:[t.isTreans?"opacity0":""],style:{left:t.cloneData.left+"px",top:t.cloneData.top+"px",transform:t.isTreans?"translate3d("+t.cloneData.transX+"px, "+t.cloneData.transY+"px, 0)":""}},[i("img",{attrs:{src:t.cloneData.img,alt:""}}),i("h5",[t._v(t._s(t.cloneData.title))])]),i("div",{directives:[{name:"show",rawName:"v-show",value:t.isContentShow,expression:"isContentShow"}],staticClass:"app-wrapper"},[i("section",[i("h4",[t._v("我的关注")]),0===t.myAppList.length?i("p",{staticClass:"tip"},[t._v("点击“编辑”按钮可以添加关注的视图")]):t._e(),i("ul",{staticClass:"myapp-list",staticStyle:{"border-bottom":"1px solid #EAEAEA"}},[i("vuedraggable",t._b({attrs:{delay:100},model:{value:t.myAppList,callback:function(e){t.myAppList=e},expression:"myAppList"}},"vuedraggable",t.dragOptions,!1),[i("transition-group",{attrs:{type:"transition"}},t._l(t.myAppList,(function(e,s){return i("li",{key:s,class:[t.isCanEdit?"showBorder":"",t.delIndex===s?"trans":"","myapp-item"],on:{click:function(i){return t.toViewDetail(e.url,e.moduleid)}}},[i("span",{directives:[{name:"show",rawName:"v-show",value:t.isCanEdit,expression:"isCanEdit"}],staticClass:"add-btn",on:{click:function(e){return e.stopPropagation(),t.delApp(s)}}}),i("img",{attrs:{src:e.icon,alt:""}}),i("h5",[t._v(t._s(e.modulename))])])})),0)],1)],1)]),i("section",[i("h4",[t._v("更多应用")]),i("ul",{staticClass:"more-list"},[i("vuedraggable",t._b({attrs:{sort:!1,handle:".del-btn"},model:{value:t.moreAppList,callback:function(e){t.moreAppList=e},expression:"moreAppList"}},"vuedraggable",t.dragOptions,!1),[i("transition-group",{attrs:{type:"transition"}},t._l(t.moreAppList,(function(e,s){return i("li",{key:s,class:[t.isCanEdit?"showBorder":"",s>3?"margin":"",t.addIndex===s?"trans":""],on:{click:function(i){return t.toViewDetail(e.url,e.moduleid)}}},[i("span",{directives:[{name:"show",rawName:"v-show",value:t.isCanEdit,expression:"isCanEdit"}],staticClass:"del-btn",on:{click:function(e){return e.stopPropagation(),t.addApp(s,e)}}}),i("img",{attrs:{src:e.icon,alt:""}}),i("h5",[t._v(t._s(e.modulename))])])})),0)],1)],1)])]),i("div",{directives:[{name:"show",rawName:"v-show",value:t.isCanEdit,expression:"isCanEdit"}],staticClass:"handle-bar"},[i("div",{staticClass:"cancel btn",on:{click:t.cancel}},[t._v("取消")]),i("div",{staticClass:"done btn",on:{click:t.complete}},[t._v("完成")])])])],1)},n=[],a=(i("c740"),i("4160"),i("c975"),i("a434"),i("d3b7"),i("159b"),i("e7e5"),i("d399")),o=i("2909"),r=i("310e"),c=i.n(r),p=i("14ac"),l=(document.documentElement.getBoundingClientRect().width,{name:"Search",components:{SearchBar:p["a"],vuedraggable:c.a},data:function(){return{inputVal:"",isContentShow:!1,isHistoryShow:!0,delIndex:-1,addIndex:-1,isTreans:!1,isCanEdit:!1,myAppList:[],moreAppList:[],originMyAppList:[],originMoreAppList:[],cloneData:{},selectId:[],isEmpty:!1}},created:function(){},mounted:function(){var t=this.$route.params.selectId,e=void 0===t?[]:t;this.selectId=e},computed:{dragOptions:function(){return{animation:300,group:"description",ghostClass:"ghost"}}},methods:{searchVal:function(t){var e=this,i=this.$route.params.allList,s=void 0===i?[]:i;this.inputVal=t,this.inputVal&&(this.myAppList=[],this.moreAppList=[],s.forEach((function(t){-1!==t.modulename.indexOf(e.inputVal)&&(e.isContentShow=!0,"01"===t.wstatus?e.myAppList.push(t):e.moreAppList.push(t))})),this.originMyAppList=Object(o["a"])(this.myAppList),this.originMoreAppList=Object(o["a"])(this.moreAppList),0===this.myAppList.length&&0===this.moreAppList.length&&(this.isContentShow=!1,this.isEmpty=!0))},editHandle:function(){0===this.moreAppList.length&&0===this.myAppList.length||(this.isCanEdit=!0)},addApp:function(t,e){var i=this,s=e.currentTarget.parentNode,n=this.myAppList.length>0?document.querySelectorAll(".myapp-item")[this.myAppList.length-1]:void 0,a=this.moreAppList[t],o=e.currentTarget.parentNode.getBoundingClientRect(),r=this.myAppList.length%4===0;n&&(r||(n.offsetLeft,o.width)),n&&(r?(n.offsetTop,o.height):n.offsetTop);this.addIndex=t,this.cloneData={img:a.icon,title:a.title,left:s.offsetLeft,top:s.offsetTop,transX:0-s.offsetLeft,transY:o.height-1-s.offsetTop},setTimeout((function(){i.isTreans=!0})),setTimeout((function(){i.addIndex=-1,i.moreAppList.splice(t,1),i.myAppList.unshift(a),i.selectId.unshift(a.moduleid),setTimeout((function(){i.cloneData={},i.isTreans=!1}),400)}),400)},delApp:function(t){var e=this;this.delIndex=t;var i=this.myAppList[t],s=this.selectId.findIndex((function(t){return t===i.moduleid}));this.selectId.splice(s,1),setTimeout((function(){e.delIndex=-1,e.myAppList.splice(t,1),e.moreAppList.push(i)}),300)},saveFootprint:function(t){var e=this;return new Promise((function(i,s){e.$axios.post("/hopeUserLog_h/insertUserLog_h",{aamid:e.$store.state.userInfoObj.aamid,moduleid:t,logtime:Date.parse(new Date)}).then((function(t){200===t.status&&"000000"===t.data.status?i():s()})).catch((function(t){s()}))}))},toViewDetail:function(t,e){t?(a["a"].loading({message:"请稍后...",forbidClick:!0}),this.saveFootprint(e).then((function(){a["a"].clear(),window.location.href=t})).catch((function(){a["a"].clear(),window.location.href=t}))):a["a"].fail("缺失跳转链接")},complete:function(){var t=this;a["a"].loading({message:"请稍后...",forbidClick:!0}),this.$axios.post("/hopeModule/editModuleSubV2",{aamid:this.$store.state.userInfoObj.aamid,addModules:this.selectId.reverse()}).then((function(e){200===e.status&&"000000"===e.data.status?(a["a"].clear(),a["a"].success("编辑成功"),setTimeout((function(){t.$router.go(-1)}),300)):(a["a"].clear(),a["a"].fail("请重试"))})).catch((function(t){a["a"].clear(),a["a"].fail("请重试")}))},cancel:function(){this.isCanEdit=!1,this.myAppList=this.originMyAppList,this.moreAppList=this.originMoreAppList}},watch:{inputVal:function(t){""===t&&(this.isEmpty=!1)}}}),d=l,u=(i("c7e5"),i("2877")),h=Object(u["a"])(d,s,n,!1,null,"6f731638",null);e["default"]=h.exports},c740:function(t,e,i){"use strict";var s=i("23e7"),n=i("b727").findIndex,a=i("44d2"),o=i("ae40"),r="findIndex",c=!0,p=o(r);r in[]&&Array(1)[r]((function(){c=!1})),s({target:"Array",proto:!0,forced:c||!p},{findIndex:function(t){return n(this,t,arguments.length>1?arguments[1]:void 0)}}),a(r)},c7e5:function(t,e,i){"use strict";var s=i("0d26"),n=i.n(s);n.a},c975:function(t,e,i){"use strict";var s=i("23e7"),n=i("4d64").indexOf,a=i("a640"),o=i("ae40"),r=[].indexOf,c=!!r&&1/[1].indexOf(1,-0)<0,p=a("indexOf"),l=o("indexOf",{ACCESSORS:!0,1:0});s({target:"Array",proto:!0,forced:c||!p||!l},{indexOf:function(t){return c?r.apply(this,arguments)||0:n(this,t,arguments.length>1?arguments[1]:void 0)}})}}]);
//# sourceMappingURL=chunk-f55dcfe8.4f29a6bf.js.map