<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
   <script type="text/javascript">
            /* <![CDATA[ */
            jQuery(function(){
                jQuery("#title").validate({
                    expression: "if (VAL) return true; else return false;",
                    message: "标题名称不能为空"
                });   
                jQuery("#keywords").validate({
                    expression: "if (VAL) return true; else return false;",
                    message: "关键字不能为空"
                });   
                jQuery("#author").validate({
                    expression: "if (VAL) return true; else return false;",
                    message: "作者不能为空"
                });   
                jQuery("#befrom").validate({
                    expression: "if (VAL) return true; else return false;",
                    message: "来源不能为空"
                });   
               // jQuery("#addTime").validate({
               //     expression: "if (VAL) return true; else return false;",
               //     message: "发布日期不能为空"
               // });   
                jQuery("#brief").validate({
                    expression: "if (VAL) return true; else return false;",
                    message: "摘要不能为空"
                });   
                jQuery("#content").validate({
                    expression: "if (VAL) return true; else return false;",
                    message: "内容不能为空"
                });   
                jQuery('#item').validated(function(){
					//alert("通过验证");
					 KE.sync('content');
                	 ajax_submit('yes','no','添加','${approot}${rooturi}/add','${approot}${rooturi}/list/${online_taoke_category_id}/menu/0/0');
				});
            });
            /* ]]> */
        </script>
</head>  
  <body style="padding-left: 10px;padding-right: 10px;padding-top: 10px;">
  <div class="title_0">您的位置：内容管理 &gt;&gt; 添加文章</div>
 
<div class="add clear">
<div class="blank12"></div>
<form method="post" action="" id="item">
<table width="98%" border="0" cellpadding="0" cellspacing="0" class="add_table2">
  <tr>
    <td width="21%"><span class="right">标题<font color='red'>*</font></span></td>
    <td width="79%"><input type="text" id="title" name="title">
         </td>
  </tr>
  <tr>
    <td width="21%"><span class="right">关键字<font color='red'>*</font></span></td>
    <td width="79%"><input type="text" id="keywords" name="keywords" size="40">
         </td>
  </tr>
  <tr>
    <td width="21%"><span class="right">作者<font color='red'>*</font></span></td>
    <td width="79%"><input type="text" id="author" name="author" size="40">
         </td>
  </tr>
  <tr>
    <td width="21%"><span class="right">来源<font color='red'>*</font></span></td>
    <td width="79%"><input type="text" id="befrom" name="befrom" size="40">
         </td>
  </tr>
 <!--   <tr>
    <td width="21%"><span class="right">发布日期<font color='red'>*</font></span></td>
    <td width="79%">
    <input type="text" id="addTime" name="addTime" class="Wdate" value="" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
         </td>
  </tr>-->
  <tr>
    <td width="21%"><span class="right">摘要<font color='red'>*</font></span></td>
    <td width="79%"><textarea rows="4" cols="60" name="brief" id="brief"></textarea>
         </td>
  </tr>
  <tr>
    <td><span class="right">内容<font color='red'>*</font></span></td>
    <td><textarea name="content" id="content" style="width:800px;height:300px;"></textarea>
      <script>
        KE.show({
                id : 'content',
                width:'90%',
                height:'280px',
                allowUpload : true, //允许上传图片
            	imageUploadJson : '${approot}/taoke/admin/article/upload' //服务端上传图片处理URI
        });
       
  </script>
     </td>
  </tr>

</table>

<div align="center"> 
  <input type="hidden" name="categoryId" value="${online_article_category_id}"/>         
  <input class="button_0" name="" type="submit" value="提 交" />  
  <input class="button_0" name="" type="button" value="返回" onclick="history.go(-1)"/>  
  </div>
  </form>
<p>&nbsp;</p>
<div class="blank12"></div>
</div>
</body>
</html>
