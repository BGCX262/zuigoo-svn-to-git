<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
  <%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
	<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
	<script src="${approot}/static/js/admin_menu.js" type="text/javascript"></script>
	<link href="${approot}/static/css/admin-menu.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
//使用向导
$(function(){
	//获取页面高度
	$("#leftheight").text($(document).height());
	$("#leftwheight").text($(window).height());
	/*点击菜单清除提示*/
	$(".menuon ul li a").click(function(){
		$(".menuon ul li a").removeClass('select');
		$(this).addClass("select");
		$(".robot_tips").remove();
		//alert($(this).attr('href'));
		//return false;
	});
});

</script>
<style type="text/css">
<!--
BODY {
SCROLLBAR-ARROW-COLOR: #FFFFFF;
SCROLLBAR-FACE-COLOR: #C5DAE7;
SCROLLBAR-HIGHLIGHT-COLOR: #f6f9fd; 
SCROLLBAR-SHADOW-COLOR: #f6f9fd;
SCROLLBAR-DARKSHADOW-COLOR: #f6f9fd; 
SCROLLBAR-3DLIGHT-COLOR: #f6f9fd;
SCROLLBAR-TRACK-COLOR: #f6f9fd;
background-color:#f6f9fd;
}
-->
</style>
  </head>
<body>
<div class="box">
	<ul id="soshopmenu">
	    <c:import url="/admin/main/left_sub/taoke" context="${ctxroot}"></c:import>
		</ul>
</div>
<div id="leftheight" style="display: none;">1745</div>
<div id="leftwheight" style="display: none;">666</div>
</body>
</html>
