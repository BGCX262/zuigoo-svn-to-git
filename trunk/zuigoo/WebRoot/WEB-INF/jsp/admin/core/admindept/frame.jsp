<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
	<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
</head>  
        <frameset cols="160,*" border=1 frameSpacing=1 borderColor=#47478d>
				<frame id="tree" name="tree" src="${approot}/${app}/admin/admindept/tree/${from}" scrolling="auto">
				<frame id="treeRight" name="treeRight" src="${approot}/${app}/admin/admin${from}/list/0/menu/0/0" scrolling="auto">
		</frameset>
</html>
