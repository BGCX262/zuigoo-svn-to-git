<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="zh-CN" xml:lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/front/inc/taglibs.jsp"%> 
<meta name="alexaVerifyID" content="ktHfDQPwUuJIpWBweF80HlS4Shw" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${title}</title>
<meta name="keywords" content="${keywords}">
<meta name="description" content="${description}">
<link rel="stylesheet" href="${approot}/static/style/taoke/css/main.css" type="text/css">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
</head>
<body>
<c:import url="/header" context="${ctxroot}"></c:import>

<div class="main">

<div class="warp">

<c:import url="${webroot}/cate/single/${rid}"></c:import>
<c:import url="/single/hots/${cid}" context="${ctxroot}"></c:import>

<div class="wid950b">
<div class="listbox1">

<div class="ibox">
<div class="tt">
<h2><a href="#">${nowcate.name}知识</a></h2><span class="more"><a href="#">更多...</a></span></div>
<div class="tb">
<ul>
<c:forEach var="item" items="${articles}"> 
    <li><span><fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd"/></span><a href="${approot}${item.fileUrl}" title="${item.title}">${item.title}</a> </li>
</c:forEach>
</ul>
</div>
</div>
</div>
</div><!--wid950b/-->

</div>
</div>
<c:import url="/footer" context="${ctxroot}"></c:import>
<c:import url="${webroot}/float2/single"></c:import>
</body></html>