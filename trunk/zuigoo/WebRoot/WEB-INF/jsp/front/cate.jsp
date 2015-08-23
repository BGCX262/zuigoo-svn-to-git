<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.grid{letter-spacing:-0.31em;word-spacing:-0.43em;*letter-spacing:normal;}
.grid .g-u{display:inline-block;letter-spacing:normal;word-spacing:normal;vertical-align:top;*display:inline;*zoom:1;}
#mycate .market-cat .hd a{background:url(${approot}/static/style/taoke/images/T1F9yXXjNlXXXXXXXX-18-919.png) no-repeat;}
#mycate{width:950px;margin:0 auto;overflow:hidden;}
#mycate .view-container{border:solid 1px #fff;position:relative;display:none;padding:10px 5px;}
#mycate .view-container:hover,#mycate .view-container-hover{border:solid 1px #cdf;background:#eef4ff;}
#mycate .last-view{line-height:18px;height:18px;overflow:hidden;vertical-align:bottom;width:630px;}
#mycate .last-view dt{font-weight:bold;font-size:14px;}
#mycate .last-view dd{padding:0 10px;border-right:1px solid #eaeaea;}
#mycate .last-view .first{padding-left:0;}
#mycate .last-view .last{border-right:none;}
#mycate .view-clear{position:absolute;z-index:10;right:0;top:0;border-right:none;border-left:solid 1px #d8e5ff;padding:10px;display:none;}
#mycate .view-show{display:block;}
#mycate .view-clear a{color:#0092d2;}
#mycate .view-clear a:hover{color:#f60;}
#mycate .market-cat{padding-top:10px;border:solid 1px #fff;border-bottom:dotted 1px #e6e6e6;}
#mycate .market-cat:hover,#mycate .select-cat{border:dotted 1px #cdf;background:#eef4ff;}
#mycate .market-cat .hd h4{padding-top:120px;margin-top:-120px;}
#mycate .market-cat .hd a{padding-left:22px;color:#d84600;font-size:14px;font-weight:bold;}
#mycate .market-cat .hd .cat1{background-position:1px -67px;}
#mycate .market-cat .hd .cat2{background-position:1px -103px;}
#mycate .market-cat .hd .cat3{background-position:6px -140px;}
#mycate .market-cat .hd .cat4{background-position:7px -178px;}
#mycate .market-cat .hd .cat5{background-position:4px -257px;}
#mycate .market-cat .hd .cat6{background-position:3px -218px;}
#mycate .market-cat .hd .cat7{background-position:4px -257px;}
#mycate .market-cat .hd .cat8{background-position:1px -409px;}
#mycate .market-cat .hd .cat9{background-position:3px -478px;}
#mycate .market-cat .hd .cat10{background-position:6px -556px;}
#mycate .market-cat .hd .cat11{background-position:6px -598px;}
#mycate .market-cat .hd .cat12{background-position:1px -669px;}
#mycate .market-cat .hd .cat13{background-position:3px -738px;}
#mycate .market-cat .hd .cat14{background-position:5px -371px;}
#mycate .market-cat .hd .cat15{background-position:4px -772px;}
#mycate .market-cat .hd .cat16{background-position:3px -868px;}
#mycate .market-cat .hd .cat17{background-position:4px -296px;}
#mycate .market-cat .hd .cat18{background-position:1px -704px;}
#mycate .market-cat .hd .cat19{background-position:4px -808px;}
#mycate .market-cat .hd .cat20{background-position:2px -839px;}
#mycate .market-cat .hd .cat21{background-position:2px -334px;}
#mycate .market-cat .hd .cat22{background-position:6px -903px;}
#mycate .market-cat .hd .cat23{background-position:6px -903px;}
#mycate .market-cat .hd .cat24{background-position:6px -903px;}
#mycate .market-cat .section{width:330px;max-width:330px;overflow:hidden;margin:6px 0 10px 24px;}
#mycate .market-cat .section .sub a{color:#0092d2;font-weight:bold;}
#mycate .market-cat .section ul{overflow:hidden;_zoom:1;}
#mycate .market-cat .section ul:after{clear:both;content:'.';display:block;height:0;visibility:hidden;}
#mycate .market-cat .section li{display:inline-block;height:24px;margin-right:9px;line-height:24px;*display:inline;*zoom:1;*margin-right:12px;}
#mycate .market-cat .section a{display:block;overflow:hidden;word-wrap:nowrap;}
}
</style>
<div id="mycate" class="g-u">
<div class="market-cat J_TBMarketCat">
   <div class="hd">
            <h4 id="cat1"><a class="cat1" href="#">${cate.name}</a></h4>
    </div>
    <div class="bd">
        <ul class="grid J_TBLikesTrigger">
        <c:forEach var="scate" items="${scates}" varStatus="ind">
             <li class="g-u section">
                    <ul>
                              <li  class="sub" > <a href="#">${scate.name}</a> </li> 
                               <c:forEach var="lcate" items="${lcates}" varStatus="ind">
                               <c:if test="${scate.id==lcate.parentId}">
                               <c:if test="${from=='single'}">
                                <li><a href="${approot}/html/single/${lcate.parentId}/${lcate.id}.html">${lcate.name}</a></li> 
                               </c:if>
                               <c:if test="${from=='index'}">
                                <li><a href="${approot}/html/index/${lcate.parentId}/${lcate.id}.html">${lcate.name}</a></li> 
                               </c:if>
                               </c:if>
                               </c:forEach>
                    </ul>
                </li>   
          </c:forEach>
        </ul>
    </div> 
</div>
</div>

