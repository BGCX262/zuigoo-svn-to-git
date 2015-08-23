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
                    message: "网站名称不能为空"
                });   
                jQuery("#url").validate({
                    expression: "if (isVURL(VAL)) return true; else return false;",
                    message: "请输入正确的网址.eg:www.baidu.com"
                });
                jQuery('#item').validated(function(){
					//alert("通过验证");
					 KE.sync('contact');
					 ajax_submit('yes','no','添加','item','${approot}${rooturi}/add','${approot}${rooturi}/list/menu/0/0');
				});
            });
            /* ]]> */
        </script>

</head>  
  <body style="background:#E8F5FE; padding:15px 18px;" class="scroll_r">
<div id="right_w"><p>当前位置：友情链接 >>添加链接</p></div>

<div class="add clear">
<form method="post" action="" id="item">
<table width="98%" border="0" cellpadding="0" cellspacing="0" class="add_table">
  <tr>
    <td width="21%"><span class="right">链接类型</span></td>
    <td width="79%">
    <input type="radio" name="type" value="1" checked="checked"/>文字链接
    <input type="radio" name="type" value="2"/>图片链接
      <span class="red">*</span> </td>
  </tr>
  <tr>
    <td width="21%"><span class="right">VIP推荐</span></td>
    <td width="79%">
    <input type="radio" name="isVip" value="1" />是
    <input type="radio" name="isVip" value="0" checked="checked"/>否
      <span class="red">*</span> </td>
    </tr>
  <tr>
    <td width="21%"><span class="right">网站名称</span></td>
    <td width="79%"><input type="text" name="name" id="name" /> 
      <span class="red">*</span> </td>
    </tr>
  <tr>
    <td width="21%"><span class="right">网站网址</span></td>
    <td width="79%"><input type="text" name="url" id="url" value="www."/> 
      <span class="red">*</span> </td>
    </tr>
  <tr>
    <td width="21%"><span class="right">图片链接</span></td>
    <td width="79%"><input type="text" name="logo" id="logo" /> 
      <span class="red">*</span> </td>
    </tr>
  <tr>
  <tr>
    <td ><span class="right">联系方式</span></td>
    <td ><textarea id="contact" name="contact" style="width:500px;height:300px;">联系人:<br>QQ: <br>手机:<br>  微博:<br>联系地址:<br></textarea>
  <script>
			KE.show({
				id : 'contact',
				width:'90%',
	            height:'280px',
				resizeMode : 1,
				allowPreviewEmoticons : false,
				allowUpload : false,
				items : [
				'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist', '|', 'emoticons', 'image', 'link']
			});

		</script>
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
