<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
	<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
     <script type="text/javascript">
            /* <![CDATA[ */
            jQuery(function(){
                jQuery("#name").validate({
                    expression: "if (VAL) return true; else return false;",
                    message: "角色名称不能为空"
                });   
                jQuery('#item').validated(function(){
					//alert("通过验证");
                	 ajax_submit('yes','no','添加','item','${approot}${rooturi}/add','${approot}${rooturi}/list/${online_dept_id}/menu/0/0');
				});
            });
            /* ]]> */
        </script>

</head>  
  <body style="background:#E8F5FE; padding:15px 18px;" class="scroll_r">
<div id="right_w"><p>当前位置：用户管理 >>添加角色</p></div>

<div class="add clear">
<form method="post" action="" id="item">
<table width="60%" border="0" cellpadding="0" cellspacing="0" class="add_table">
  <tr>
    <td width="21%"><span class="right">角色名称</span></td>
    <td width="79%"><input type="text" name="name" id="name" /> 
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
</body>
</html>
