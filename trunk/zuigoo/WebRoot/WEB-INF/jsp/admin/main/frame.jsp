<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
	<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
</head>
<frameset rows="80,*,30" border=0 framespacing="0" borderColor=#47478d name="mainFrame" frameborder="no">
  <frame id="top" name="top" src="${approot}/admin/main/header" scrolling="no" noresize="noresize">
        <frameset cols="160,*" border=0 frameSpacing=0 id="f2" frameborder="no">
				<frame id="left" name="left" src="${approot}/admin/main/left/taoke" scrolling="auto">
			    <frameset cols="9,*" framespacing="0" frameborder="no" border="0">
                <frame src="${approot}/admin/main/split" name="split" scrolling="no" noresize="noresize" id="split" />
                <frame id="main" name="main" src="${approot}/admin/main/right" scrolling="auto">
			 </frameset>
		</frameset>
   <frame id="footer" name="footer" src="${approot}/admin/main/footer" scrolling="no">
</frameset>

<noframes>
<body>
对不起，您的浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架……
</body>
</noframes>
</html>
