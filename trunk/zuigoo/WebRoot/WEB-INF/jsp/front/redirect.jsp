<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="zh-CN" xml:lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/front/inc/taglibs.jsp"%>
<meta name="alexaVerifyID" content="ktHfDQPwUuJIpWBweF80HlS4Shw" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta content="IE=EmulateIE7" http-equiv="X-UA-Compatible">
	<title>跳转提示…… | 爱购网(zuigoo.com)</title>
	<link type="image/x-icon" href="http://www.zuigoo.com/static/stlye/taoke/images/favicon.ico" rel="shortcut icon">
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
var refresh = "${item.clickUrl}";
function myrefresh()
{
	window.location.href=refresh;
}
setTimeout('myrefresh()',1100);
</script> 
</head>
<body>
	<h1 id="logo" style="text-align: center">
	<img alt="zuigoo.com" src="${approot}/static/style/taoke/images/logo.jpg" /></h1>
	<h2><img src="${approot}/static/style/taoke/images/time.gif" /></h2>
	<p>您将从&nbsp;<strong>最购网</strong>&nbsp;离开，跳转至<b>&nbsp;${item.nick}&nbsp;</b></p>
	<p>您感兴趣的商品是:<font color='red'>${item.title}</font></a></b></p>
	<p>正在跳转中:如果没有自动跳转<a href="${item.clickUrl}">请点击这里</a></b></p>
</body>
</html>