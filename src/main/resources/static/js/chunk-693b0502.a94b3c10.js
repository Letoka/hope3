(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-693b0502"],{2732:function(e,t,s){},"3b68":function(e,t,s){"use strict";var a=s("2732"),i=s.n(a);i.a},9603:function(e,t){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAaCAYAAACtv5zzAAAAAXNSR0IArs4c6QAAAPtJREFUSA3tk0EKwjAQRWeql/A0gmDBhT1APIDUhR7AtQdQUPEA5gB1IVQQvJd2zLRNCbW2CeIugRKYzH9/+EkB/PIJ+AT+ngBqh4lMhk8iiYibVEQHXXfZQ5ksiGjdRxRXET1YG2jAC6AHgAPKaB+eL0tdt91Zw1pmFKxCWRmoqe+AEAOiGiLbupjkcKVhLTNyVjlZFZGedCyTORAcgUilFazS2XSnz5r2OvwmopPZ92HAh7YmXXBmNRrYmNjAWw3aTGzhnQZNJlzjR6AvtJ45n5vra0Rmk3kneb18LV1w7rUy4MbKpFDFNnBudVrqTx3x5yTyzT6BnxN4Az8ok4PeohipAAAAAElFTkSuQmCC"},"99af":function(e,t,s){"use strict";var a=s("23e7"),i=s("d039"),o=s("e8b5"),r=s("861d"),n=s("7b0b"),c=s("50c4"),l=s("8418"),h=s("65f0"),u=s("1dde"),p=s("b622"),d=s("2d00"),m=p("isConcatSpreadable"),v=9007199254740991,w="Maximum allowed index exceeded",y=d>=51||!i((function(){var e=[];return e[m]=!1,e.concat()[0]!==e})),f=u("concat"),g=function(e){if(!r(e))return!1;var t=e[m];return void 0!==t?!!t:o(e)},A=!y||!f;a({target:"Array",proto:!0,forced:A},{concat:function(e){var t,s,a,i,o,r=n(this),u=h(r,0),p=0;for(t=-1,a=arguments.length;t<a;t++)if(o=-1===t?r:arguments[t],g(o)){if(i=c(o.length),p+i>v)throw TypeError(w);for(s=0;s<i;s++,p++)s in o&&l(u,p,o[s])}else{if(p>=v)throw TypeError(w);l(u,p++,o)}return u.length=p,u}})},cb80:function(e,t,s){"use strict";s.r(t);var a=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"search"},[a("div",{directives:[{name:"show",rawName:"v-show",value:e.isDialogShow,expression:"isDialogShow"}],staticClass:"apply-dialog"},[a("div",{staticClass:"dialog"},[a("div",{staticClass:"input"},[a("textarea",{directives:[{name:"model",rawName:"v-model",value:e.applyVal,expression:"applyVal"}],attrs:{maxlength:"30",placeholder:"如申请开通视图权限，请输入开通理由"},domProps:{value:e.applyVal},on:{input:function(t){t.target.composing||(e.applyVal=t.target.value)}}}),a("span",[e._v("30字以内")])]),a("div",{staticClass:"btn",on:{click:e.submitApply}},[e._v("提交")]),a("div",{staticClass:"close",on:{click:function(t){e.isDialogShow=!1}}})])]),a("SearchBar",{on:{searchVal:e.searchVal,keyboardSearch:e.keyboardSearch}},[a("div",{staticClass:"search-btn",on:{click:e.toSearchResultPage}},[e._v("搜索")])]),a("div",{staticClass:"content"},[a("div",{directives:[{name:"show",rawName:"v-show",value:e.inputVal.length>0,expression:"inputVal.length > 0"}],staticClass:"search-list"},[a("div",{directives:[{name:"show",rawName:"v-show",value:e.isLoadingShow,expression:"isLoadingShow"}],staticClass:"loading"},[a("img",{attrs:{src:s("12ae"),alt:""}}),a("p",[e._v("搜索中...")])]),a("div",{directives:[{name:"show",rawName:"v-show",value:e.isEmptyShow&&0===e.searchViewResultList.length,expression:"isEmptyShow && searchViewResultList.length === 0"}],staticClass:"empty"},[e._v("搜索结果为空")]),a("div",{directives:[{name:"show",rawName:"v-show",value:!e.isLoadingShow,expression:"!isLoadingShow"}],staticClass:"search-result"},[a("div",{directives:[{name:"show",rawName:"v-show",value:e.searchViewResultList.length,expression:"searchViewResultList.length"}],staticClass:"view"},[e._m(0),a("ul",e._l(e.searchViewResultList,(function(t,s){return a("li",{key:s,on:{click:function(t){return e.toLink(e.searchViewResultList[s],"view")}}},[a("span",{staticClass:"left"},[a("span",{staticClass:"text",domProps:{innerHTML:e._s(t.text)}}),a("img",{directives:[{name:"show",rawName:"v-show",value:"keyWord"!==t.type,expression:"item.type !== 'keyWord'"}],attrs:{src:t.icon,alt:""}})]),a("span",{staticClass:"auth"},[a("span",{directives:[{name:"show",rawName:"v-show",value:"keyWord"===t.type,expression:"item.type === 'keyWord'"}],staticClass:"key-icon"},[e._v("关键字")]),a("span",{directives:[{name:"show",rawName:"v-show",value:0===t.quanxianC,expression:"item.quanxianC === 0"}]},[e._v(" {无权限}")])])])})),0),a("ul",{directives:[{name:"show",rawName:"v-show",value:e.searchMoreViewResultList.length&&e.isShowMore,expression:"searchMoreViewResultList.length && isShowMore"}]},e._l(e.searchMoreViewResultList,(function(t,s){return a("li",{key:s,on:{click:function(t){return e.toLink(e.searchMoreViewResultList[s],"view")}}},[a("span",{staticClass:"left"},[a("span",{staticClass:"text",domProps:{innerHTML:e._s(t.text)}}),a("img",{directives:[{name:"show",rawName:"v-show",value:"keyWord"!==t.type,expression:"item.type !== 'keyWord'"}],attrs:{src:t.icon,alt:""}})]),a("span",{staticClass:"auth"},[a("span",{directives:[{name:"show",rawName:"v-show",value:"keyWord"===t.type,expression:"item.type === 'keyWord'"}],staticClass:"key-icon"},[e._v("关键字")]),a("span",{directives:[{name:"show",rawName:"v-show",value:0===t.quanxianC,expression:"item.quanxianC === 0"}]},[e._v(" {无权限}")])])])})),0)]),e.searchMoreViewResultList.length?a("div",{staticClass:"more",on:{click:e.showMore}},[e._v(" "+e._s(e.isShowMore?"收起":"更多")+" "),a("img",{class:[e.isShowMore?"roate":""],attrs:{src:s("9603"),alt:""}})]):e._e(),a("div",{directives:[{name:"show",rawName:"v-show",value:e.searchArticleResultList.length,expression:"searchArticleResultList.length"}],staticClass:"article"},[e._m(1),a("ul",e._l(e.searchArticleResultList,(function(t,s){return a("li",{key:s,on:{click:function(t){return e.toLink(e.searchArticleResultList[s],"article")}}},[a("span",{staticClass:"left"},[a("span",{staticClass:"text",domProps:{innerHTML:e._s(t.text)}})]),a("span",{staticClass:"auth"},[a("span",{directives:[{name:"show",rawName:"v-show",value:"keyWord"===t.type,expression:"item.type === 'keyWord'"}],staticClass:"key-icon"},[e._v("关键字")])])])})),0)])])]),a("div",{directives:[{name:"show",rawName:"v-show",value:0===e.inputVal.length,expression:"inputVal.length === 0"}],staticClass:"history-hot"},[a("div",{directives:[{name:"show",rawName:"v-show",value:e.isHistoryShow&&e.searchHistoryList.length,expression:"isHistoryShow && searchHistoryList.length"}],staticClass:"history"},[a("span",{staticClass:"delete-btn",on:{click:e.hideHistory}}),a("h4",[e._v("历史搜索")]),a("ul",e._l(e.searchHistoryList,(function(t,s){return a("li",{key:s,on:{click:function(s){return e.clickToResult(t.searchtext,t.moduleid)}}},[e._v(e._s(t.searchtext))])})),0)]),a("div",{staticClass:"hot"},[a("h4",[e._v("热门搜索")]),a("ul",e._l(e.searchHotList,(function(t,s){return a("li",{key:s,on:{click:function(s){return e.clickToResult(t.searchtext,t.moduleid)}}},[e._v(e._s(t.searchtext))])})),0)])])])],1)},i=[function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("h5",[s("i"),e._v("相关视图")])},function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("h5",[s("i"),e._v("相关文章")])}],o=(s("99af"),s("4160"),s("d81d"),s("fb6a"),s("d3b7"),s("4d63"),s("ac1f"),s("25f0"),s("5319"),s("498a"),s("159b"),s("2909")),r=(s("e7e5"),s("d399")),n=s("5530"),c=s("14ac"),l={name:"Search",components:{SearchBar:c["a"]},data:function(){return{inputVal:"",applyVal:"",applyModuleId:void 0,isDialogShow:!1,isLoadingShow:!1,isEmptyShow:!1,isHistoryShow:!0,isShowMore:!1,searchViewResultList:[],searchMoreViewResultList:[],searchArticleResultList:[],searchHistoryList:[],searchHotList:[]}},created:function(){},mounted:function(){this.getHistory(),this.getHot()},methods:{searchVal:function(e){var t=this;if(this.inputVal=e,this.searchViewResultList=[],this.inputVal){this.isLoadingShow=!0,this.isEmptyShow=!1;var s=new RegExp(this.inputVal,"g");this.$axios.post("/hopeModule/searchModuleByName",{name:this.inputVal,aamid:this.$store.state.userInfoObj.aamid,deptid:this.$store.state.userInfoObj.deptid,odeptid:this.$store.state.userInfoObj.odeptid}).then((function(e){if(t.isLoadingShow=!1,200===e.status&&"000000"===e.data.status){var a=e.data.data||[],i=a.module.list[1].styleList||[],o=a.module.list[0].styleList||[],r=a.article.list[1].styleList||[],c=a.article.list[0].styleList||[];i.forEach((function(e){e.type="keyWord"})),r.forEach((function(e){e.type="keyWord"}));var l=o.slice(0,3).concat(i.slice(0,3)),h=c.concat(r),u=i.slice(3).concat(o.slice(3));if(0===i.length&&0===o.length&&0===r.length&&0===c.length)return void(t.isEmptyShow=!0);t.searchViewResultList=l.map((function(e){return Object(n["a"])({text:e.type&&"keyWord"===e.type?e.keyname.replace(s,"<span style='color: #45A4B1;'>"+t.inputVal+"</span>"):e.modulename.replace(s,"<span style='color: #45A4B1;'>"+t.inputVal+"</span>")},e)})),t.searchMoreViewResultList=u.map((function(e){return Object(n["a"])({text:e.type&&"keyWord"===e.type?e.keyname.replace(s,"<span style='color: #45A4B1;'>"+t.inputVal+"</span>"):e.modulename.replace(s,"<span style='color: #45A4B1;'>"+t.inputVal+"</span>")},e)})),t.searchArticleResultList=h.map((function(e){return Object(n["a"])({text:e.textname.replace(s,"<span style='color: #45A4B1;'>"+t.inputVal+"</span>")},e)}))}else t.isEmptyShow=!0})).catch((function(e){t.isLoadingShow=!1,t.isEmptyShow=!0}))}},showMore:function(){this.isShowMore=!this.isShowMore},toSearchResultPage:function(){this.inputVal&&this.$router.push({name:"SearchResult",query:{value:this.inputVal}})},keyboardSearch:function(){this.toSearchResultPage()},clickToResult:function(e,t){var s=this;r["a"].loading({message:"请稍后...",forbidClick:!0}),t?this.saveFootprint(t).then((function(){s.$router.push({name:"SearchResult",query:{value:e}})})).catch((function(){s.$router.push({name:"SearchResult",query:{value:e}})})):this.$router.push({name:"SearchResult",query:{value:e}})},saveHistory:function(e){var t=this;return new Promise((function(s,a){t.$axios.post("/hopeSearchHistory/insertSearchRecord",{aamid:t.$store.state.userInfoObj.aamid,searchtext:e,logtime:new Date}).then((function(e){200===e.status&&"000000"===e.data.status?s():a()})).catch((function(e){a()}))}))},saveFootprint:function(e){var t=this;return new Promise((function(s,a){t.$axios.post("/hopeUserLog_h/insertUserLog_h",{aamid:t.$store.state.userInfoObj.aamid,moduleid:e,logtime:Date.parse(new Date)}).then((function(e){200===e.status&&"000000"===e.data.status?s():a()})).catch((function(e){a()}))}))},toLink:function(e,t){var s=this,a="view"===t?e.url:e.textpath;if(0===e.quanxianC)return this.applyModuleId=e.moduleid,void(this.isDialogShow=!0);if(r["a"].loading({message:"请稍后...",forbidClick:!0}),"keyWord"!==e.type)this.saveHistory(e.modulename).then((function(){window.location.href=a})).catch((function(){window.location.href=a}));else{var i="article"===t?e.textname:e.modulename;this.saveFootprint(e.moduleid).then((function(){s.saveHistory(i).then((function(){s.$router.push({name:"SearchResult",query:{value:i,isArticle:"article"===t}})})).catch((function(){s.$router.push({name:"SearchResult",query:{value:i,isArticle:"article"===t}})}))})).catch((function(){s.saveHistory(i).then((function(){s.$router.push({name:"SearchResult",query:{value:i,isArticle:"article"===t}})})).catch((function(){s.$router.push({name:"SearchResult",query:{value:i,isArticle:"article"===t}})}))}))}},submitApply:function(){var e=this,t=this.applyVal.trim();t?(r["a"].loading({message:"请稍后...",forbidClick:!0}),this.$axios.post("/hopeUserApply/insertHopeUserApply",{aamid:this.$store.state.userInfoObj.aamid,moduleid:this.applyModuleId,logtime:new Date,message:t}).then((function(t){r["a"].clear(),200===t.status&&"000000"===t.data.status&&(e.isDialogShow=!1,e.applyVal="",r["a"].success("申请已送达"))})).catch((function(){r["a"].clear(),r["a"].fail("请重试")}))):r["a"].fail("请输入开通理由")},getHistory:function(){var e=this;this.$axios.post("/hopeSearchHistory/querySearchRecord",{aamid:this.$store.state.userInfoObj.aamid}).then((function(t){if(200===t.status&&"000000"===t.data.status){var s=t.data.data||[];e.searchHistoryList=Object(o["a"])(s)}}))},getHot:function(){var e=this;this.$axios.post("/hopeSearchHistory_h/queryHotSearch",{aamid:this.$store.state.userInfoObj.aamid}).then((function(t){if(200===t.status&&"000000"===t.data.status){var s=t.data.data||[];e.searchHotList=Object(o["a"])(s)}}))},hideHistory:function(){var e=this.searchHistoryList.map((function(e){return e.searchtext}));this.isHistoryShow=!1,this.$axios.post("/hopeSearchHistory/delSearchRecord",{aamid:this.$store.state.userInfoObj.aamid,searchArr:e})}}},h=l,u=(s("3b68"),s("2877")),p=Object(u["a"])(h,a,i,!1,null,"590348a1",null);t["default"]=p.exports}}]);
//# sourceMappingURL=chunk-693b0502.a94b3c10.js.map