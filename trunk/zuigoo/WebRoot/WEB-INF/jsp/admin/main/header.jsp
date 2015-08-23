<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
	<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
	<link href="${approot}/static/css/admin-top.css" type="text/css" rel="stylesheet">
	
	<script type="text/javascript">
	function tomenu(app)
	{
		window.top.frames["left"].location="${approot}/admin/main/left/"+app;
	}
	function doExit()
	{
      if(!confirm('确定退出?'))
      {
          return false;
      }
      parent.location.href="${approot}/admin/main/exit";
	}
	</script>
  </head>
<body>
<div class="header_content">
     <div class="logo">
  </div>
 		<div class="text_right">
 		<img src="${approot}/static/images/index.png" /> <a href="#">首页</a>
 		<img src="${approot}/static/images/logout.jpg" /> <a href="#" onclick="return doExit();">退出</a></div>
</div>
	<div id="nav">
     <div id="user_info">${approot}欢迎您，${sessionScope.online_admin_user.name}<a href="#"></a></div>
      <ul>
        <c:choose>
        <c:when test="${online_app=='console_task'}"> <li><span class="on"><a href="#" onclick="tomenu('task')">任务系统</a></span></li></c:when>
        <c:when test="${online_app=='console_tao'}">  <li><span class="on"><a href="#" onclick="tomenu('tao')">淘客系统</a></span></li></c:when>
        <c:when test="${online_app=='console_cms'}">  <li><span class="on"><a href="#" onclick="tomenu('cms')">网站系统</a></span></li></c:when>
        <c:when test="${online_app=='console_shop'}">  <li><span class="on"><a href="#" onclick="tomenu('shop')">商城系统</a></span></li></c:when>
        <c:when test="${online_app=='console_tuan'}"> <li><span class="on"><a href="#" onclick="tomenu('tuan')">团购系统</a></span></li></c:when>
        <c:otherwise></c:otherwise>
        </c:choose>
      </ul>
	</div>
</body>
</html>
