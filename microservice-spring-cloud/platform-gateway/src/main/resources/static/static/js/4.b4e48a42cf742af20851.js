webpackJsonp([4],{"/V72":function(t,e){},"2+ps":function(t,e){},GBxx:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=n("Xxa5"),i=n.n(r),a=n("exGp"),s=n.n(a),o=n("gyMJ"),c={name:"Carousel",data:function(){return{advertisements:[]}},created:function(){var t=this;return s()(i.a.mark(function e(){return i.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,o.a.warehouse.getAdvertisements();case 2:t.advertisements=e.sent.data;case 3:case"end":return e.stop()}},e,t)}))()},methods:{loadDetail:function(t){this.$router.push("/detail/"+t)}}},u={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("el-carousel",{attrs:{interval:5e3,type:"card",height:"400px"}},t._l(t.advertisements,function(e){return n("el-carousel-item",{key:e.id},[n("el-image",{staticClass:"image",attrs:{src:e.image},on:{click:function(n){return t.loadDetail(e.productId)}}})],1)}),1)},staticRenderFns:[]};var l=n("VU/8")(c,u,!1,function(t){n("/V72")},"data-v-5faa9004",null).exports,d=n("Dd8w"),f=n.n(d),v=n("NYxO"),p={name:"Cabinet",data:function(){return{books:[]}},computed:f()({},Object(v.e)("user",["favorite","account"]),Object(v.e)("cart",["items"])),created:function(){var t=this;return s()(i.a.mark(function e(){return i.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,o.a.warehouse.getAllProducts();case 2:t.books=e.sent.data;case 3:case"end":return e.stop()}},e,t)}))()},methods:f()({},Object(v.d)("user",["addFavorite","removeFavorite"]),Object(v.d)("cart",["addCartItem","removeCartItem"]),Object(v.b)("cart",["setupSettlementBillWithDefaultValue"]),{isFavorite:function(t){return this.favorite.includes(t)},isInCart:function(t){return this.items.find(function(e){return e.id===t})},updateFavorite:function(t){this.isFavorite(t)?this.removeFavorite(t):this.addFavorite(t),this.$notify({title:"成功",message:"恭喜你，已成功更新收藏夹",iconClass:"el-icon-star-on",type:"success"})},updateCart:function(t){this.isInCart(t)?this.removeCartItem(t):this.addCartItem(f()({},this.books.find(function(e){return e.id===t}))),this.$notify({title:"成功",message:"恭喜你，已成功更新购物车",iconClass:"el-icon-s-goods",type:"success"})},loadDetail:function(t){this.$router.push("/detail/"+t)},pureText:function(t){return o.a.stringUtil.pureText(t)},goDirectSettlement:function(t){var e=f()({},t,{amount:1});this.setupSettlementBillWithDefaultValue({items:[e]}),this.$router.push("/settle")}})},m={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("el-card",{staticClass:"box-card"},[n("div",{staticClass:"header",attrs:{slot:"header"},slot:"header"},[n("span",[t._v("热销书籍")])]),t._v(" "),n("el-row",{attrs:{gutter:0}},t._l(t.books,function(e){return n("el-col",{key:e.id,staticClass:"book-container",attrs:{span:6}},[n("el-image",{staticClass:"image",attrs:{src:e.cover},on:{click:function(n){return t.loadDetail(e.id)}}}),t._v(" "),n("div",{staticStyle:{padding:"14px"}},[n("span",{attrs:{id:"price"}},[t._v("￥ "+t._s(e.price.toFixed(2)))]),t._v(" "),n("span",{attrs:{id:"title"}},[t._v(t._s(e.title))]),t._v(" "),n("span",{attrs:{id:"description"}},[t._v(t._s(t.pureText(e.description)))]),t._v(" "),n("div",{attrs:{id:"actions"}},[n("el-button",{attrs:{icon:"el-icon-money",circle:""},on:{click:function(n){return t.goDirectSettlement(e)}}}),t._v(" "),n("el-button",{attrs:{icon:t.isInCart(e.id)?"el-icon-s-goods":"el-icon-goods",circle:""},on:{click:function(n){return t.updateCart(e.id)}}}),t._v(" "),n("el-button",{attrs:{icon:t.isFavorite(e.id)?"el-icon-star-on":"el-icon-star-off",circle:""},on:{click:function(n){return t.updateFavorite(e.id)}}})],1)])],1)}),1)],1)},staticRenderFns:[]};var h={name:"MainPage",components:{Carousel:l,Cabinet:n("VU/8")(p,m,!1,function(t){n("eX9T")},"data-v-43226b54",null).exports}},_={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",[e("Carousel"),this._v(" "),e("Cabinet")],1)},staticRenderFns:[]};var b=n("VU/8")(h,_,!1,function(t){n("2+ps")},"data-v-24b3789e",null);e.default=b.exports},eX9T:function(t,e){}});