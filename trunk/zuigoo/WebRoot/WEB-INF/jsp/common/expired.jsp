<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="zh-CN" xml:lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
<meta name="alexaVerifyID" content="ktHfDQPwUuJIpWBweF80HlS4Shw" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta content="IE=EmulateIE7" http-equiv="X-UA-Compatible">
	<title>登录超时或者没有登录!</title>
	<style type="text/css">
		html
		{
			background: #f7f7f7;
		}
		body
		{
			background: #fff;
			color: #333;
			font-family: "MicrosoftYaHei" , "微软雅黑" ,Verdana,Arial;
			margin: 2em auto 0 auto;
			width: 700px;
			height:400px;
			padding: 1em 2em;
			-moz-border-radius: 11px;
			-khtml-border-radius: 11px;
			-webkit-border-radius: 11px;
			border-radius: 11px;
			border: 1px solid #dfdfdf;
		}
		a
		{
			color: #2583ad;
			text-decoration: none;
		}
		a:hover
		{
			color: #d54e21;
		}
		h1
		{
			border-bottom: 1px solid #dadada;
			clear: both;
			color: #666;
			margin: 5px 0 5px 0;
			padding: 0;
			padding-bottom: 1px;
		}
		h2
		{
			text-align:center;
			font-size:30px;
			}
		p
		{
			text-align: center;
			font-size:18px;
		}
		div{margin-bottom:80px;}
		ul
		{
			width:400px;
			margin:0 auto;
		}
	</style>
<script type="text/javascript">
		function gid(id) { return document.getElementById ? document.getElementById(id) : null; }
		function timeDesc() {
			if (all <= 0) {
				<c:if test="${from=='front'}">
				   self.location = "${approot}/toLogin";
				</c:if>
				<c:if test="${from=='system'}">
				   self.location = "${approot}/entry/index";
				</c:if>
				//history.go('-1');
			}
			var obj = gid("ts");
			if (obj) obj.innerHTML = "<font color='red'>"+all + " 秒后</font>";
			all--;
		}
		var all = 1;
		if (all > 0) window.setInterval("timeDesc();", 1000);
</script>
</head>
<body>
	<h1 id="logo" style="text-align: center">
	<img alt="" src="${approot}/static/images/common/expired.png" /></h1>
	<h2><img src="${approot}/static/images/common/time.gif" /></h2>
	<p><font color='red'>您还没有登录或者登录超时,请先登录</font></b></p>
	<p>系统将于&nbsp;&nbsp;<span id="ts"></span> 自动返回</p>
	<c:if test="${from=='front'}">
	<p>正在跳转中:如果没有自动跳转  <a href="#" onclick="location.href='${approot}/task/toLogin'">请点击这里</a></b></p>
	</c:if>
	<c:if test="${from=='system'}">
	<p>正在跳转中:如果没有自动跳转  <a href="#" onclick="location.href='${approot}/entry/index'">请点击这里</a></b></p>
	</c:if>
	
</body>
</html>