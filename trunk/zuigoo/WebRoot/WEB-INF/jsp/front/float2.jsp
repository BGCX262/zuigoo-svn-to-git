<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.g-u{display:inline-block;letter-spacing:normal;word-spacing:normal;vertical-align:top;*display:inline;*zoom:1;}
#leftmenu .bd a{background:url('${approot}/static/style/taoke/images/float_banner_2.png') no-repeat;}
#leftmenu {position:fixed;_position:absolute;width:98px;top:150px;_top:expression(eval(documentElement.scrollTop-140));margin-left:1190px;border:1px solid #FFE6AC;}
#leftmenu .bd{position:relative;/*height:403px;*/height:830px;overflow:hidden;background:#FFF8E9;}
#leftmenu .bd ul{position:absolute;top:0;width:98px;border-top:4px solid #ffeec4;border-bottom:4px solid #ffeec4;}
#leftmenu .bd li{border-bottom:solid 1px #FEC;line-height:32px;height:32px;}
#leftmenu .bd a{display:block;padding-left:40px;}
#leftmenu .bd .select{font-weight:bold;color:#D84600;background-color:#fff;}
#leftmenu .cat1 a{background-position:10px -60px;}
#leftmenu .cat2 a{background-position:13px -132px;}
#leftmenu .cat3 a{background-position:13px -362px;}
#leftmenu .cat4 a{background-position:11px -96px;}
#leftmenu .cat5 a{background-position:13px -548px;}
#leftmenu .cat6 a{background-position:13px -590px;}
#leftmenu .cat7 a{background-position:10px -662px;}
#leftmenu .cat8 a{background-position:12px -766px;}
#leftmenu .cat9 a{background-position:12px -800px;}
#leftmenu .cat10 a{background-position:11px -832px;}
#leftmenu .cat11 a{background-position:11px -862px;}
#leftmenu .cat12 a{background-position:15px -896px;}
#leftmenu .cat13 a{background-position:15px -896px;}
#leftmenu .cat14 a{background-position:15px -896px;}
#leftmenu .cat15 a{background-position:15px -896px;}
#leftmenu .cat16 a{background-position:15px -896px;}
#leftmenu .cat17 a{background-position:15px -896px;}
#leftmenu .cat18 a{background-position:15px -896px;}
#leftmenu .cat19 a{background-position:15px -896px;}
#leftmenu .cat20 a{background-position:15px -896px;}
#leftmenu .cat21 a{background-position:15px -896px;}
#leftmenu .cat22 a{background-position:15px -896px;}
</style>

<div id="J_LeftMenu">
<div id="leftmenu" class="g-u">
<div class="bd">
<ul>
<c:forEach var="cate" items="${cates}" varStatus="ind">
<li class="cat${ind.index+1}">
<c:choose>
<c:when test="${cate.id==defaultId}">
<c:if test="${from=='single'}">
     <a href="${approot}/html/single/${cate.id}.html" data-num="${ind.index+1}">${cate.name}</a>
</c:if>
<c:if test="${from=='index'}">
     <a href="${approot}/index.html" data-num="${ind.index+1}">${cate.name}</a>
</c:if>
</c:when>
<c:otherwise>
<c:if test="${from=='single'}">
     <a href="${approot}/html/single/${cate.id}.html" data-num="${ind.index+1}">${cate.name}</a>
</c:if>
<c:if test="${from=='index'}">
     <a href="${approot}/html/index/${cate.id}.html" data-num="${ind.index+1}">${cate.name}</a>
</c:if>
</c:otherwise>
</c:choose>
</li>
</c:forEach>
</ul>
</div>
</div>
</div>

