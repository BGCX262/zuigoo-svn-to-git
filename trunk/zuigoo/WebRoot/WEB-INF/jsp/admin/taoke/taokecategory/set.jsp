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
	

	function createHtml(type){
	       $.ajax({
		   	url:'${approot}/taoke/admin/taokecategory/createHtml/'+type,
			data:'',
			type:'post',
			beforeSend:function(){
			   $('#main'+type).disabled='true';
			   $('#loading_main').html("<img  src='${approot}/static/images/loading.gif'/>文件生成中,请稍后");
			   $('#loading_main').show();
			},
			error:function(request){
				alert(request.responseText);
			},
			success:function(data){
//			   alert(data);
            if (data.msg == "error") {
            	$('#main').attr('disabled',false);
            	$('#loading_main').html("html文件生成失败!");
            }
            if (data.msg == "succ") {
               
                $('#main').attr('disabled',false);
 	            $('#loading_main').html("生成成功:共生成:[<font color='red'>"+data.count+"</font>]个html文件!");
            }
	          
			}
		   });
		}
	</script>


<div id="tabs">
	<ul>
		<li><a href="#tabs-1">静态生成</a></li>
		<li><a href="#tabs-2">栏目生成</a></li>
	
	</ul>
	<div id="tabs-1">
	 <div  id="mainMain" style='padding:10px; background:#fff;'>
	 <input type="button" name="url" value="生成目录树文件" onclick="createHtml('tree');" id="maintree"/>
	 <input type="button" name="url" value="生成一级目录静态页面" onclick="createHtml('one');" id="mainone"/>
	 <input type="button" name="url" value="生成三级目录静态页面" onclick="createHtml('three');" id="mainthree"/>
	 <input type="button" name="url" value="生成主页推广一级静态页面" onclick="createHtml('four');" id="mainfour"/>
	 <input type="button" name="url" value="生成主页推广三级静态页面" onclick="createHtml('two');" id="maintwo"/>
	 <input type="button" name="url" value="生成文章详情静态页面" onclick="createHtml('five');" id="mainfive"/>
	 <div id="loading_main" style="display:none"> </div>
	</div>
    </div>		
	
	<div id="tabs-2">
	</div>
	
</div>
  
</body>
</html>
