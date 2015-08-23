<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
</head>
  <style type="text/css">
  body {
  font-size: 62.5%;
  } //这个样式，控制ui的展示大小，否则，会显示很大
  </style> 

<body>

    <script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
	});

	
	$(function() {
		$("input:button").button();
	});
	

	function createXml(){
	       $.ajax({
		   	url:'${approot}/admin/admindept/treexml',
			data:'',
			type:'post',
			beforeSend:function(){
			   $('#main').disabled='true';
			   $('#loading_main').html("<img  src='${approot}/res/image/loading.gif'/>文件生成中,请稍后");
			   $('#loading_main').show();
			},
			error:function(request){
				alert(request.responseText);
			},
			success:function(data){
//			   alert(data);
	           $('#main').attr('disabled',false);
	           $('#loading_main').html(data+",xml文件生成成功");
			}
		   });
		}
	</script>


<div id="tabs">
	<ul>
		<li><a href="#tabs-1">菜单统计</a></li>
		<li><a href="#tabs-2">栏目生成</a></li>
	
	</ul>
	<div id="tabs-1">
	
    </div>		
	
	<div id="tabs-2">
    <div  id="mainMain" style='padding:10px; background:#fff;'>
	 <input type="button" name="url" value="生成目录文件" onclick="createXml();" id="main"/>
	<div id="loading_main" style="display:none">   </div>
	</div>
	</div>
	
	
</div>
   


</body>
</html>
