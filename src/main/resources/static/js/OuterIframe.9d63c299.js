(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["OuterIframe"],{"0cd9":function(e,t,n){"use strict";var r=n("a8d6"),a=n.n(r);a.a},"22ef":function(e,t,n){"use strict";var r=n("efe2");function a(e,t){return RegExp(e,t)}t.UNSUPPORTED_Y=r((function(){var e=a("a","y");return e.lastIndex=2,null!=e.exec("abcd")})),t.BROKEN_CARET=r((function(){var e=a("^r","gy");return e.lastIndex=2,null!=e.exec("str")}))},"38eb":function(e,t,n){"use strict";var r=n("f62c").charAt;e.exports=function(e,t,n){return t+(n?r(e,t).length:1)}},5139:function(e,t,n){"use strict";var r=n("99ad"),a=n("22ef"),c=RegExp.prototype.exec,i=String.prototype.replace,o=c,u=function(){var e=/a/,t=/b*/g;return c.call(e,"a"),c.call(t,"a"),0!==e.lastIndex||0!==t.lastIndex}(),l=a.UNSUPPORTED_Y||a.BROKEN_CARET,s=void 0!==/()??/.exec("")[1],f=u||s||l;f&&(o=function(e){var t,n,a,o,f=this,d=l&&f.sticky,p=r.call(f),v=f.source,g=0,x=e;return d&&(p=p.replace("y",""),-1===p.indexOf("g")&&(p+="g"),x=String(e).slice(f.lastIndex),f.lastIndex>0&&(!f.multiline||f.multiline&&"\n"!==e[f.lastIndex-1])&&(v="(?: "+v+")",x=" "+x,g++),n=new RegExp("^(?:"+v+")",p)),s&&(n=new RegExp("^"+v+"$(?!\\s)",p)),u&&(t=f.lastIndex),a=c.call(d?n:f,x),d?a?(a.input=a.input.slice(g),a[0]=a[0].slice(g),a.index=f.lastIndex,f.lastIndex+=a[0].length):f.lastIndex=0:u&&a&&(f.lastIndex=f.global?a.index+a[0].length:t),s&&a&&a.length>1&&i.call(a[0],n,(function(){for(o=1;o<arguments.length-2;o++)void 0===arguments[o]&&(a[o]=void 0)})),a}),e.exports=o},"59da":function(e,t,n){var r=n("2118"),a=n("5139");e.exports=function(e,t){var n=e.exec;if("function"===typeof n){var c=n.call(e,t);if("object"!==typeof c)throw TypeError("RegExp exec method returned something other than an Object or null");return c}if("RegExp"!==r(e))throw TypeError("RegExp#exec called on incompatible receiver");return a.call(e,t)}},"5e9f":function(e,t,n){"use strict";var r=n("b2a2"),a=n("857c"),c=n("3553"),i=n("d88d"),o=n("3da3"),u=n("2732"),l=n("38eb"),s=n("59da"),f=Math.max,d=Math.min,p=Math.floor,v=/\$([$&'`]|\d\d?|<[^>]*>)/g,g=/\$([$&'`]|\d\d?)/g,x=function(e){return void 0===e?e:String(e)};r("replace",2,(function(e,t,n,r){var h=r.REGEXP_REPLACE_SUBSTITUTES_UNDEFINED_CAPTURE,E=r.REPLACE_KEEPS_$0,b=h?"$":"$0";return[function(n,r){var a=u(this),c=void 0==n?void 0:n[e];return void 0!==c?c.call(n,a,r):t.call(String(a),n,r)},function(e,r){if(!h&&E||"string"===typeof r&&-1===r.indexOf(b)){var c=n(t,e,this,r);if(c.done)return c.value}var u=a(e),p=String(this),v="function"===typeof r;v||(r=String(r));var g=u.global;if(g){var R=u.unicode;u.lastIndex=0}var I=[];while(1){var S=s(u,p);if(null===S)break;if(I.push(S),!g)break;var y=String(S[0]);""===y&&(u.lastIndex=l(p,i(u.lastIndex),R))}for(var _="",A=0,$=0;$<I.length;$++){S=I[$];for(var w=String(S[0]),P=f(d(o(S.index),p.length),0),C=[],T=1;T<S.length;T++)C.push(x(S[T]));var U=S.groups;if(v){var k=[w].concat(C,P,p);void 0!==U&&k.push(U);var O=String(r.apply(void 0,k))}else O=m(w,p,P,C,U,r);P>=A&&(_+=p.slice(A,P)+O,A=P+w.length)}return _+p.slice(A)}];function m(e,n,r,a,i,o){var u=r+e.length,l=a.length,s=g;return void 0!==i&&(i=c(i),s=v),t.call(o,s,(function(t,c){var o;switch(c.charAt(0)){case"$":return"$";case"&":return e;case"`":return n.slice(0,r);case"'":return n.slice(u);case"<":o=i[c.slice(1,-1)];break;default:var s=+c;if(0===s)return t;if(s>l){var f=p(s/10);return 0===f?t:f<=l?void 0===a[f-1]?c.charAt(1):a[f-1]+c.charAt(1):t}o=a[s-1]}return void 0===o?"":o}))}}))},"99ad":function(e,t,n){"use strict";var r=n("857c");e.exports=function(){var e=r(this),t="";return e.global&&(t+="g"),e.ignoreCase&&(t+="i"),e.multiline&&(t+="m"),e.dotAll&&(t+="s"),e.unicode&&(t+="u"),e.sticky&&(t+="y"),t}},"9de2":function(e,t,n){"use strict";n.r(t);var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"outerIframe"},[n("iframe",{staticClass:"iframe",attrs:{src:"http://www.baidu.com",frameborder:"0"}}),n("div",{staticClass:"back",on:{click:e.back}},[e._v("回首页")])])},a=[],c=(n("e35a"),n("5e9f"),{name:"OuterIframe",components:{},data:function(){return{}},mounted:function(){},methods:{back:function(e){this.$router.replace({name:"Home"}),e.stopPropagation()}}}),i=c,o=(n("0cd9"),n("9ca4")),u=Object(o["a"])(i,r,a,!1,null,"19b9a324",null);t["default"]=u.exports},a8d6:function(e,t,n){},b2a2:function(e,t,n){"use strict";n("e35a");var r=n("1944"),a=n("efe2"),c=n("90fb"),i=n("5139"),o=n("0fc1"),u=c("species"),l=!a((function(){var e=/./;return e.exec=function(){var e=[];return e.groups={a:"7"},e},"7"!=="".replace(e,"$<a>")})),s=function(){return"$0"==="a".replace(/./,"$0")}(),f=c("replace"),d=function(){return!!/./[f]&&""===/./[f]("a","$0")}(),p=!a((function(){var e=/(?:)/,t=e.exec;e.exec=function(){return t.apply(this,arguments)};var n="ab".split(e);return 2!==n.length||"a"!==n[0]||"b"!==n[1]}));e.exports=function(e,t,n,f){var v=c(e),g=!a((function(){var t={};return t[v]=function(){return 7},7!=""[e](t)})),x=g&&!a((function(){var t=!1,n=/a/;return"split"===e&&(n={},n.constructor={},n.constructor[u]=function(){return n},n.flags="",n[v]=/./[v]),n.exec=function(){return t=!0,null},n[v](""),!t}));if(!g||!x||"replace"===e&&(!l||!s||d)||"split"===e&&!p){var h=/./[v],E=n(v,""[e],(function(e,t,n,r,a){return t.exec===i?g&&!a?{done:!0,value:h.call(t,n,r)}:{done:!0,value:e.call(n,t,r)}:{done:!1}}),{REPLACE_KEEPS_$0:s,REGEXP_REPLACE_SUBSTITUTES_UNDEFINED_CAPTURE:d}),b=E[0],m=E[1];r(String.prototype,e,b),r(RegExp.prototype,v,2==t?function(e,t){return m.call(e,this,t)}:function(e){return m.call(e,this)})}f&&o(RegExp.prototype[v],"sham",!0)}},e35a:function(e,t,n){"use strict";var r=n("1c8b"),a=n("5139");r({target:"RegExp",proto:!0,forced:/./.exec!==a},{exec:a})},f62c:function(e,t,n){var r=n("3da3"),a=n("2732"),c=function(e){return function(t,n){var c,i,o=String(a(t)),u=r(n),l=o.length;return u<0||u>=l?e?"":void 0:(c=o.charCodeAt(u),c<55296||c>56319||u+1===l||(i=o.charCodeAt(u+1))<56320||i>57343?e?o.charAt(u):c:e?o.slice(u,u+2):i-56320+(c-55296<<10)+65536)}};e.exports={codeAt:c(!1),charAt:c(!0)}}}]);
//# sourceMappingURL=OuterIframe.9d63c299.js.map