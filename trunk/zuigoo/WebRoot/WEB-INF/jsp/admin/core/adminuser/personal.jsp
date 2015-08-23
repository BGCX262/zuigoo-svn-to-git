<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
	<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
     <script type="text/javascript">
            /* <![CDATA[ */
            jQuery(function(){ 
                   jQuery("#realName").validate({
                       expression: "if (VAL) return true; else return false;",
                       message: "真实姓名不能为空"
                   });   
                   jQuery("#repassword").validate({
                       expression: "if ((VAL == jQuery('#password').val())) return true; else return false;",
                       message: "两次输入的密码不一致"
                   });
                jQuery('#item').validated(function(){
					//alert("通过验证");
                	edit_personal();
				});
            });
            /* ]]> */
        </script>
        <script type="text/javascript">

        /**
         * @author jemmyee
         * @des 修改对象入库
         * @param entity entity对应的小写
         * @param redirect 成功后跳转的url
         */
        function edit_personal(){
            var str = $('#item').serializeObject();
            var jsons = $.toJSON(str);
            //alert(jsons);
        	var url=approot +'/core/admin/adminuser/personal';
        	//alert(url);
            $.ajax({
                url: url,
                dataType: 'json',
                contentType: 'application/json',
                data: jsons,
                type: 'post',
                beforeSend: function(){
                    // $('#main').disabled='true';
                },
                error: function(request){
        			
                    alert("网络故障,修改失败");
                },
                success: function(data){
                    if (data.msg == "rename") {
                        alert('名称已经存在,请重新输入!');
                    }
                    if (data.msg == "error") {
                        alert('系统内部错误,修改失败!');
                    }
                    if (data.msg == "succ") {
                        alert('修改成功');
                    }
                }
            });
        }
        </script>
</head>  
  <body style="background:#E8F5FE; padding:15px 18px;" class="scroll_r">
<div id="right_w"><p>当前位置：用户管理 >>个人信息修改</p></div>

<div class="add clear">
<form method="post" action="" id="item">
<table width="60%" border="0" cellpadding="0" cellspacing="0" class="add_table">
<tr>
    <td width="21%"><span class="right">登录账号</span></td>
    <td width="79%"><input type="text" name="name" id="name" value="${item.name}" disabled="disabled"/> 
      <span class="red">*</span> </td>
    </tr>
    <tr>
    <td width="21%"><span class="right">真实姓名</span></td>
    <td width="79%"><input type="text" name="realName" id="realName" value="${item.realName}"/> 
      <span class="red">*</span> </td>
    </tr>
     <tr>
    <td width="21%"><span class="right">登录密码</span></td>
    <td width="79%"><input type="password" name="password"  id="password">
      <span class="red">*</span> </td>
    </tr>
  <tr>
    <td width="21%"><span class="right">重输密码</span></td>
    <td width="79%"><input type="password" name=""  id="repassword"> 
      <span class="red">*</span> </td>
    </tr>
 
     <tr>
    <td width="21%"><span class="right">性别</span></td>
    <td width="79%">
    <input type="radio" name="sex" value="1" <c:if test='${item.sex==1}'>checked='checked'</c:if>/>男
    <input type="radio" name="sex" value="2" <c:if test='${item.sex==2}'>checked='checked'</c:if>/>女
      <span class="red">*</span> </td>
  </tr>
  <tr>
  <td colspan="2" align="center">
  <input type="hidden" name="id" value="${item.id}"/>
    <input class="button_0" name="" type="submit" value="提交"/>  
  <input class="button_0" name="" type="button" value="返回" onclick="history.go(-1)"/>  
  </td>
  </tr>
</table>

</form>
</body>
</html>
