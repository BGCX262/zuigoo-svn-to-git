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
            "url" : "${approot}/static/gen/article_category_tree.html"
	    }},
		"themes" : {"theme" : "classic","dots" : true,"icons" :true},
		"core": { "initially_open" : [ "0"]},
		"languages": [ "zh-cn", "bg" ],
		"plugins" : ["themes", "html_data","ui","languages"]
	}).bind("select_node.jstree", function(e, data){
		var cid=data.rslt.obj.attr("id");
        //alert(cid);
		window.parent.document.getElementById("treeRight").src="${approot}/${app}/admin/${from}/list/"+cid+"/menu/0/0";
    }) 

}
</script>
<!-- <script type="text/javascript">
$(function () {
   parseTree();
});

function parseTree(){
    $.ajax({
	   	url:'${approot}/${app}/admin/articlecategory/parse_tree',
	   	dataType: 'json',
	   	contentType: 'application/json',
		type:'post',
		beforeSend:function(){
	   	
		},
		error:function(request){
			alert(request.responseText);
		},
		success:function(data){
			loadtree(data.tree);
		}
	   });
}
function loadtree(html) {
	$("#catetree").jstree({ 
		"html_data" : {"data" :html},
		"themes" : {"theme" : "classic","dots" : true,"icons" :true},
		"core": { "initially_open" : [ "0"]},
		"languages": [ "zh-cn", "bg" ],
		"plugins" : ["themes", "html_data","ui","languages"]
	}).bind("select_node.jstree", function(e, data){
		var cid=data.rslt.obj.attr("id");
        //alert(cid);
		window.parent.document.getElementById("treeRight").src="${approot}/${app}/admin/${from}/list/"+cid+"/menu/0/0";
    }) 

}
</script>-->

</head>  
<body>
  <div id="catetree"></div>
</body>
</html>
