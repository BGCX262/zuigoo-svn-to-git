<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  <%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
	<link href="${approot}/static/css/admin-login.css" type="text/css" rel="stylesheet">
    <link href="${approot}/static/css/admin-reset.css" type="text/css" rel="stylesheet">
	<title>系统后台管理</title>
		   <script type="text/javascript">
        function getImage()
        {
        var path ="${approot}/entry/validateCode?"+(new Date().getTime());
        var preImage = document.getElementById("displayImage");
        preImage.innerHTML = "";
        var imageZone = document.createElement('img');
        imageZone.setAttribute('id','imageZone');
        //提取服务器上的图片显示下来:
        imageZone.setAttribute('src',path);
        preImage.appendChild(imageZone);
        } 
        </script>
	
  </head>

 <body>
<div class="login">
     <div class="user">
      <form action="${approot}/entry/login" method="post">
     	<span>用 户：
     	<input class="input_1" type="text" name="name" value="admin" id="name"/>
   	   </span>
        <span>密 码：
        <input  class="input_1" type="password" name="password" value="" id="password"/>
        </span>
        <span>验 证：
        <input  class="input_2" type="text" name="vcode" id="vcode" style="width:75px;"/>
       <a onclick="getImage();" id="displayImage"><img id="vimage" alt="" src="${approot}/entry/validateCode" width="70" height="20" /></a> </span> 
       <span class="ts">${loginError}</span>  
       </div>
       <div  class="button">
        	<input class="dl" name="" type="submit" value="登 录" />
             <input class="dl" name="" type="button" value="重 置"/>
       </div>
</div>
</body>
</html>
