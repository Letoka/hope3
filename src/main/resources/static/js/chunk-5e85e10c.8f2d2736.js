(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5e85e10c"],{aff6:function(t,a,s){"use strict";s.r(a);var i=function(){var t=this,a=t.$createElement,i=t._self._c||a;return i("div",{staticClass:"chat"},[i("div",{staticClass:"top"},[i("div",{staticClass:"back",on:{click:t.back}}),t._v(" "+t._s(t.chatTitle)+" ")]),t.isShowContent?t._e():i("img",{staticClass:"loading",attrs:{src:s("12ae"),alt:""}}),i("div",{ref:"content",staticClass:"content"},t._l(t.chatList,(function(a,s){return i("div",{key:s,staticClass:"list-item",class:[t.isShowContent?"":"hidden"]},[a.aamid?i("div",{staticClass:"msg-item-right msg-item"},[i("h5",[t._v(t._s(t._f("onlyShowTime")(a.time||a.sendtime)))]),i("div",{staticClass:"main"},[t._m(0,!0),i("div",{staticClass:"msg-content"},[i("p",[t._v(t._s(a.text))])])])]):i("div",{staticClass:"msg-item-left msg-item"},[i("h5",[t._v(t._s(t._f("onlyShowTime")(a.time||a.sendtime)))]),i("div",{staticClass:"main"},[i("div",{staticClass:"avatar"},[i("img",{attrs:{src:t.moduleIcon,alt:""}})]),i("div",{staticClass:"msg-content"},[i("h6",[t._v(t._s(a.text_head?a.text_head:"———— 系 统 消 息 ————"))]),i("p",[t._v(t._s(a.text))])])])])])})),0),i("div",{staticClass:"input-bar"},[i("input",{directives:[{name:"model",rawName:"v-model",value:t.inputVal,expression:"inputVal"}],attrs:{type:"text",placeholder:"请键入您对该视图的建议"},domProps:{value:t.inputVal},on:{input:function(a){a.target.composing||(t.inputVal=a.target.value)}}}),i("div",{staticClass:"submit",on:{click:t.submit}},[t._v("发送")])])])},e=[function(){var t=this,a=t.$createElement,i=t._self._c||a;return i("div",{staticClass:"avatar"},[i("img",{attrs:{src:s("b633"),alt:""}})])}],n=(s("4160"),s("c975"),s("498a"),s("159b"),s("c1df")),o=s.n(n),r=s("bc3a"),c=s.n(r),d=window.origin+"/mobile456/xxtx/py",m=c.a.create({baseURL:d}),l={name:"MessageChat",data:function(){return{chatTitle:"通知消息",inputVal:"",socket:null,chatList:[],moduleIcon:"",isShowContent:!1}},mounted:function(){var t=this.$route.params,a=t.modulename,s=t.icon;t.moduleid;this.chatTitle=a||"通知消息",this.moduleIcon=s,this.getChatList()},methods:{back:function(){this.$router.go(-1)},scrollToBottom:function(){var t=this;setTimeout((function(){window.scrollTo({top:t.$refs.content.scrollHeight}),t.isShowContent=!0}))},scrollToKeyWord:function(){var t=this,a=0;this.chatList.forEach((function(s,i){var e=s.text;e&&-1!==e.indexOf(t.$route.params.kw)&&(a=i)})),this.$nextTick((function(){var s=t.$refs.content.children[a];setTimeout((function(){window.scrollTo({top:s.offsetTop-60}),t.isShowContent=!0}),200)}))},getChatList:function(){var t=this,a=this.$route.params,s=a.moduleid,i=a.modulename,e=this.$store.state.userInfoObj.aamid;m.get("/chat-page",{params:{aamid:e,moduleid:s,modulename:i}}).then((function(a){200===a.status&&"OK"===a.data.message&&(t.chatList=a.data.data||[],t.$route.params.kw?t.scrollToKeyWord():t.scrollToBottom())})).catch((function(t){}))},sendSocketData:function(t){var a=this;if(""!==t){var s=this.$route.params,i=s.moduleid,e=s.modulename,n=this.$store.state.userInfoObj.aamid;this.chatList.push({text:t,aamid:n,time:o()().format("YYYY-MM-DD HH:mm")}),m.post("/reply-msg",{aamid:this.$store.state.userInfoObj.aamid,moduleid:i,modulename:e,replymess:t,replytime:o()().format("YYYY-MM-DD HH:mm:ss.SSS")}).then((function(t){200===t.status&&"OK"===t.data.message&&(a.chatList.push({text_head:t.data.data.text_head,moduleid:i,text:t.data.data.text,time:o()().format("YYYY-MM-DD HH:mm")}),a.scrollToBottom())})),this.scrollToBottom()}},submit:function(){var t=this.inputVal.trim();t&&(this.inputVal="",this.sendSocketData(t))}},filters:{onlyShowTime:function(t){var a=o()().format("YYYY-MM-DD");return-1!==t.indexOf(a)?t.substring(11,16):t.substring(0,16)}},beforeDestroy:function(){}},u=l,h=(s("ed14"),s("2877")),f=Object(h["a"])(u,i,e,!1,null,"a819a7ac",null);a["default"]=f.exports},b633:function(t,a){t.exports="data:image/png;base64,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"},c4a0:function(t,a,s){},c975:function(t,a,s){"use strict";var i=s("23e7"),e=s("4d64").indexOf,n=s("a640"),o=s("ae40"),r=[].indexOf,c=!!r&&1/[1].indexOf(1,-0)<0,d=n("indexOf"),m=o("indexOf",{ACCESSORS:!0,1:0});i({target:"Array",proto:!0,forced:c||!d||!m},{indexOf:function(t){return c?r.apply(this,arguments)||0:e(this,t,arguments.length>1?arguments[1]:void 0)}})},ed14:function(t,a,s){"use strict";var i=s("c4a0"),e=s.n(i);e.a}}]);
//# sourceMappingURL=chunk-5e85e10c.8f2d2736.js.map