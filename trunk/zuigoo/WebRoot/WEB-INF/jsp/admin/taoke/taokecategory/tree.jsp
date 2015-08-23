<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
	<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
	<script type="text/javascript">
$(function () {
	loadtree();
});

function loadtree() {
	$("#catetree").jstree({ 
		"html_data" : {"ajax" : {
            "url" : "${approot}/static/gen/taoke_category_tree.html"
	    }},
		"themes" : {"theme" : "classic","dots" : true,"icons" :true},
		"core": { "initially_open" : [ "0"]},
		"languages": [ "zh-cn", "bg" ],
		"plugins" : ["themes", "html_data","ui","languages"]
	}).bind("select_node.jstree", function(e, data){
		var cid=data.rslt.obj.attr("id");
        //alert(cid);
        <c:choose>
				<c:when test="${from=='article'}">
				window.parent.document.getElementById("treeRight").src="${approot}/cms/admin/${from}/list/"+cid+"/menu/0/0";
				</c:when>
				<c:otherwise>
				window.parent.document.getElementById("treeRight").src="${approot}/taoke/admin/${from}/list/"+cid+"/menu/0/0";
				</c:otherwise>
				</c:choose>

    }) 

}
</script>

</head>  
<body>
  <div id="catetree"></div>
</body>
</html>
