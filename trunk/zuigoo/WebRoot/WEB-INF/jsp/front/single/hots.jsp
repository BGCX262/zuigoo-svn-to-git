<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${approot}/static/theme/default/css/style.css" type="text/css">
<!-- 
<div class="header mia">
<div class="nav mia">
<div class="nav_left">
<div class="nav_right">
<p><a href="#">首页</a>
<c:forEach var="cate" items="${recates}" varStatus="ind">
    <c:if test="${ind.index<5}">
|<a href="${approot}/html/single/${cate.parentId}/${cate.id}.html">${cate.name}排行榜</a>
  </c:if>
   </c:forEach>
</p></div></div></div>
<div class="flash_bg mia">
<div class="flash_bg_left">
<div class="flash_bg_right">
<p>
</p></div></div></div></div>-->
 
<div class="wid970">
    <div class="listtab">
    <ul>
    <li class="curr"><a href="${approot}/html/single/${nowcate.parentId}/${nowcate.id}.html">${nowcate.name}产品排行榜</a></li>
    <c:forEach var="cate" items="${recates}" varStatus="ind">
    <c:if test="${ind.index<4}">
    <li class=""><a href="${approot}/html/single/${cate.parentId}/${cate.id}.html">${cate.name}产品排行榜</a></li>
    </c:if>
   </c:forEach>
    </ul>
  </div>
    <div class="nobox">
        <div id="tab1_div_0">
        <table class="listbox">
          <tbody>
        <c:forEach var="item" items="${items}" varStatus="ind"> 
    <c:if test="${ind.index<10}">   
        <tr>
        <td class="topsale"><em>NO.</em><span>${ind.index+1}</span></td>
        <td class="proimg"><a href="${approot}/r/item/${item.id}" target="_blank"><img src="${item.picUrl}" width="150" height="127" alt="${item.title}"></a></td>
        <td class="protext" valign="top"><div><p class="title"><a href="${approot}/r/item/${item.id}" target="_blank" title="${nowcate.name}排行榜第${ind.index+1} ${item.nick}">${nowcate.name}排行榜第${ind.index+1} ${item.nick}</a></p><p class="about">${item.title}</p><p class="key"></p></div></td>
        <td class="probuy"><div><p class="xy">信用等级：
         <c:choose>
  <c:when test="${item.sellerCreditScore<=20}"><img src="${approot}/static/images/taobao/tao_rank_${item.sellerCreditScore}.gif"/> </c:when>
  <c:otherwise>未知</c:otherwise>
  </c:choose>
  </p><p class="xsl">月销售量：<span>${item.volume}</span>件</p><p class="price">疯狂特价：<span>${item.price}</span>元</p><p class="buy"><a href="${approot}/r/item/${item.id}" target="_blank"><img src="${approot}/static/theme/default/images/buy.gif" alt="${item.title}"></a></p></div></td>        
        </tr>
         </c:if>
   </c:forEach>   
                </tbody></table>
        </div>
        
    </div><!--nobox/-->
</div><!--wid970/-->

<!--<div class="wid970">
    <div class="toplogo">
        <div class="leftlogo"><a href="/"><span></span></a></div>

        <div class="rightnav">
        <ul>            
                <li><a href="/jfss/" target="_self">减肥瘦身排行榜</a></li>
                <li><a href="/tbfx/" target="_self">淘宝丰胸产品排行榜</a></li>
                <li><a href="/tbqd/" target="_self">祛痘祛斑美白排行榜</a></li>
                <li><a href="/tbmb/" target="_self">淘宝情趣内衣排行榜</a></li>
                <li><a href="/tbqb/" target="_self">彩妆/香水/美妆工具</a></li>
                </ul>
        </div>
    </div>
</div>-->