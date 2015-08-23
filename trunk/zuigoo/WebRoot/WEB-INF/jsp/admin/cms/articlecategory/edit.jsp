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
                      message: "栏目名称不能为空"
                  });   
                  /* jQuery("#seoTitle").validate({
                  expression: "if (VAL) return true; else return false;",
                  message: "SEO标题不能为空"
              });   
              jQuery("#seoKeyword").validate({
                  expression: "if (VAL) return true; else return false;",
                  message: "SEO关键字不能为空"
              });   
              jQuery("#seoDesc").validate({
                  expression: "if (VAL) return true; else return false;",
                  message: "SEO描述不能为空"
              });  */
                jQuery('#item').validated(function(){
					//alert("通过验证");
               	 ajax_submit('yes','yes','修改','item','${approot}${rooturi}/edit','${approot}${rooturi}/list/menu/${online_article_category_id}/0/0');
				});
            });
            /* ]]> */
        </script>
</head>  
  <body style="background:#E8F5FE; padding:15px 18px;" class="scroll_r">
<div id="right_w"><p>当前位置：系统管理 >>修改部门</p></div>

<div class="add clear">
<form method="post" action="" id="item">
<table width="80%" border="0" cellpadding="0" cellspacing="0" class="add_table">
  <tr>
    <td width="21%"><span class="right">栏目名称</span></td>
    <td width="79%"><input type="text" name="name" id="name" value="${item.name}"/> 
      <span class="red">*</span> </td>
    </tr>
         <tr>
    <td width="21%"><span class="right">是否底层</span></td>
    <td width="79%">
    <input type="radio" name="isLast" value="0" <c:if test='${item.isLast==0}'>checked='checked'</c:if>/>否
    <input type="radio" name="isLast" value="1" <c:if test='${item.isLast==1}'>checked='checked'</c:if>/>是
    </td>
  </tr>
         <tr>
    <td width="21%"><span class="right">首页主菜单</span></td>
    <td width="79%">
    <input type="radio" name="isShowInNav" value="0" <c:if test='${item.isShowInNav==0}'>checked='checked'</c:if>/>否
    <input type="radio" name="isShowInNav" value="1" <c:if test='${item.isShowInNav==1}'>checked='checked'</c:if>/>是
    </td>
  </tr>
      <tr>
    <td width="21%"><span class="right">SEO标题</span></td>
    <td width="79%">
    <textarea rows="2" cols="70" id="seoTitle" name="seoTitle">${item.seoTitle}</textarea>
    </td>
  </tr>
      <tr>
    <td width="21%"><span class="right">SEO关键字</span></td>
    <td width="79%">
     <textarea rows="2" cols="70" id="seoKeyword" name="seoKeyword">${item.seoKeyword}</textarea>
         </td>
  </tr>
      <tr>
    <td width="21%"><span class="right">SEO描述</span></td>
    <td width="79%">
     <textarea rows="2" cols="70" id="seoDesc" name="seoDesc" >${item.seoDesc}</textarea>
         </td>
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
