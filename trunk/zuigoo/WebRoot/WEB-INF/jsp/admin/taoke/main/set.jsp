<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
    <script type="text/javascript">
	$(function() {
		 $('#tabs').tabs({'selected':${tabID}});
	});
	function gather(){
	       $.ajax({
		   	url:'${approot}/taoke/admin/main/gather',
			data:'',
			type:'post',
			beforeSend:function(){
			   $('#mainproduct').disabled='true';
			   $('#loading_product').html("<img  src='${approot}/static/images/loading.gif'/>商品采集中,请稍后");
			   $('#loading_product').show();
			},
			error:function(request){
				alert(request.responseText);
			},
			success:function(data){
//			   alert(data);
         if (data.msg == "error") {
        	 $('#mainproduct').attr('disabled',false);
         	$('#loading_product').html("采集失败!");
         }
         if (data.msg == "succ") {
            
        	 $('#mainproduct').attr('disabled',false);
	         $('#loading_product').html("采集成功:共生成:[<font color='red'>"+data.count+"</font>]个商品!");
          }
	          
			}
		   });
		}
	</script>
	<script type="text/javascript">
            /* <![CDATA[ */
            jQuery(function(){
            	  jQuery("#regPoints").validate({
                      expression: "if (isVInteger(VAL)) return true; else return false;",
                      message: "赠送发布点不能为空,只能是整数"
                  });
            	  jQuery("#regMoney").validate({
                      expression: "if (isVInteger(VAL)) return true; else return false;",
                      message: "赠送现金不能为空,只能是整数"
                  });
                  jQuery("#pointPrice").validate({
                      expression: "if (isVDouble(VAL)) return true; else return false;",
                      message: "发布点不能为空,只能是Double数字"
                  });
                jQuery('#item2').validated(function(){
					  //alert("通过验证");
                	  ajax_submit('yes','no','会员参数','item2','${approot}${rooturi}/set/2','no');
				});
            });
            /* ]]> */
   </script>
	<script type="text/javascript">
            /* <![CDATA[ */
            jQuery(function(){
                  jQuery("#nick").validate({
                      expression: "if (VAL) return true; else return false;",
                      message: "淘宝账号不能为空"
                  });
                  jQuery("#appkey").validate({
                      expression: "if (VAL) return true; else return false;",
                      message: "appkey不能为空"
                  });
                  jQuery("#appsecret").validate({
                      expression: "if (VAL) return true; else return false;",
                      message: "appsecret不能为空"
                  });
                  jQuery("#appurl").validate({
                      expression: "if (VAL) return true; else return false;",
                      message: "appurl不能为空"
                  });
                jQuery('#item1').validated(function(){
					  //alert("通过验证");
                	  ajax_submit('yes','no','淘宝客参数','item1','${approot}${rooturi}/set/1','no');
				});
            });
            /* ]]> */
   </script>
</head>
  <style type="text/css">
  body {
  font-size: 62.5%;
  } //这个样式，控制ui的展示大小，否则，会显示很大
  </style> 

<body>
<div id="tabs">
	<ul>
		<li><a href="#tabs-0">系统配置</a></li>
		<li><a href="#tabs-1">淘客设置</a></li>
		<li><a href="#tabs-2">会员设置</a></li>
		<li><a href="#tabs-3">内容设置</a></li>
		<li><a href="#tabs-4">商品采集</a></li>
	</ul>
	<div id="tabs-0">
	 
    </div>		
	
	<div id="tabs-1">
 <form method="post" action="" id="item1" name="item1">
<table width="80%" border="0" cellpadding="0" cellspacing="0" class="add_table">
  <tr>
    <td width="21%"><span class="right">淘宝账号</span></td>
    <td width="79%"><input type="text" name="nick" id="nick" value="${nick}" size="40"/> 
      <span class="red">*</span> </td>
  </tr>
  <tr>
    <td width="21%"><span class="right">APPKEY</span></td>
    <td width="79%"><input type="text" name="appkey" id="appkey" value="${appkey}" size="40"/> 
      <span class="red">*</span> </td>
  </tr>
  <tr>
    <td width="21%"><span class="right">APPSECRET</span></td>
    <td width="79%"><input type="text" id="appsecret" name="appsecret" value="${appsecret}" size="40">
      <span class="red">*</span> </td>
  </tr>
  <tr>
    <td width="21%"><span class="right">APPURL</span></td>
    <td width="79%"><input type="text" id="appurl" name="appurl" value="${appurl}" size="40">
      <span class="red">*</span> </td>
  </tr>
  <tr>
  <td colspan="2" align="center">
    <input class="button_0" name="" type="submit" value="提交"/>  
  <input class="button_0" name="" type="button" value="返回" onclick="history.go(-1)"/>  
  </td>
  </tr>
</table>
</form>
	</div>
	
	<div id="tabs-2">
	<form method="post" action="" id="item2" name="item2">
<table width="80%" border="0" cellpadding="0" cellspacing="0" class="add_table">
  <tr>
    <td width="21%"><span class="right">会员注册赠送发布点(个)</span></td>
    <td width="79%"><input type="text" name="regPoints" id="regPoints" value="${regPoints}"/> 
      <span class="red">*</span> </td>
  </tr>
  <tr>
    <td width="21%"><span class="right">会员注册赠送现金(元)</span></td>
    <td width="79%"><input type="text" name="regMoney" id="regMoney" value="${regMoney}"/> 
      <span class="red">*</span> </td>
  </tr>
  <tr>
    <td width="21%"><span class="right">兑现时发布点单价</span></td>
    <td width="79%"><input type="text" id="pointPrice" name="pointPrice" value="${pointPrice}">
      <span class="red">*</span> </td>
  </tr>
  <tr>
  <td colspan="2" align="center">
    <input class="button_0" name="" type="submit" value="提交"/>  
  <input class="button_0" name="" type="button" value="返回" onclick="history.go(-1)"/>  
  </td>
  </tr>
</table>
</form>
	</div>
	
	<div id="tabs-3">
	 <div  id="mainMain" style='padding:10px; background:#fff;'>
	 <input type="button" name="url" value="生成所有静态文章" onclick="createHtml('all');" id="mainall"/>
	 <div id="loading_main" style="display:none"> </div>
	</div>
    </div>		
    
	<div id="tabs-4">
	 <div  id="mainproduct" style='padding:10px; background:#fff;'>
	 <input type="button" name="url" value="开始采集商品" onclick="gather();" id="mainproduct"/>
	 <div id="loading_product" style="display:none"> </div>
	</div>
    </div>		
	
</div>
  
</body>
</html>
