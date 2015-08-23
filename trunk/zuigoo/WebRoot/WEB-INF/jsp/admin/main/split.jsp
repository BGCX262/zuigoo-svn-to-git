<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
	<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
	
	 <STYLE type="text/css">
body{
  margin:0; padding:0;
  background:#D6F0FF;
}
</STYLE>
<script language="JavaScript">
function switchSysBar(){
 if (parent.document.getElementById('f2').cols=="160,*"){
 document.getElementById('leftbar').style.display="";
 parent.document.getElementById('f2').cols="0,*";
 }
 else{
 parent.document.getElementById('f2').cols="160,*";
 document.getElementById('leftbar').style.display="none"
 }
}
function load(){
 if (parent.document.getElementById('f2').cols=="0,*"){
 document.getElementById('leftbar').style.display="";
 }
}

</script>
  </head>
<body onLoad="load()">
<table cellspacing="0" cellpadding="0" border="0" width="100%" height="100%" style="border-right:1px #A2B9C1 solid;">
 <tbody>
  <tr>
   <td id="leftbar" style="display: none;"><a onClick="switchSysBar()" href="javascript:void(0);"><img border="0" alt="展开左侧菜单" src="${approot}/static/images/pic_24.gif"/></a></td>
   <td id="rightbar">
   <a onClick="switchSysBar()" href="javascript:void(0);"><img border="0" alt="隐藏左侧菜单" src="${approot}/static/images/pic_23.gif"/></a>  </td>
 </tr>
 </tbody>
</table>
</body>
</html>
