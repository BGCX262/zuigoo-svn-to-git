<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
  <script type="text/javascript">
	function dostatic(type){
	       $.ajax({
		   	url:'${approot}/taoke/admin/taokecategory/dostatic/'+type,
			data:'',
			type:'post',
			beforeSend:function(){
			   $('#main'+type).disabled='true';
			   $('#loading').html("<img  src='${approot}/static/images/loading.gif'/>文件生成中,请稍后");
			   $('#loading').show();
			},
			error:function(request){
				alert(request.responseText);
			},
			success:function(data){
//			   alert(data);
            if (data.msg == "error") {
            	$('#main'+type).attr('disabled',false);
            	$('#loading').html("html文件生成失败!");
            }
            if (data.msg == "succ") {
               
                $('#main'+type).attr('disabled',false);
 	            $('#loading').html("生成成功:共生成:[<font color='red'>"+data.count+"</font>]个html文件!");
            }
	          
			}
		   });
		}
	</script>


</head>  
  <body style="padding-left: 10px;padding-right: 10px;padding-top: 10px;">
  <div class="title_0">您的位置：内容管理 &gt;&gt; 内容管理</div>

<form method="post" action="${approot}/${app}/admin/${entity}/list/form/${parentId}/${pageSize}/${startIndex}" id="bean">
 <div class="search">
   	 <fieldset>
     <legend><strong>搜索</strong></legend>
        <form>
        <label><span class="blue">关键字</span>：
        <input type="text" name="bkw" value="${bean.bkw}"/></label>
        <label> <span class="blue"> 开始时间：</span>
       <input type="text" id="startTime" name="startTime"  value="${bean.startTimeString}" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/> </label>
        <label> <span class="blue">结束时间：</span>
       <input type="text" id="endTime" name="endTime" value="${bean.endTimeString}" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/></label>
     </form>
  <div align="center">      
  <input class="button_0" type="submit" class="but" value="开始查询">
  <input class="button_0" type="button" class="but" onclick="formreset();" value="重置条件">
  </div>
     </fieldset>
   </div>
</form> 

<div class="blank12"></div>
<div align="left">    
  <input class="button_0" name="" type="button" value="批量删除" onclick="return delete_batch('${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')"/> 
  <c:if test="${isLast==1}"> 
  <input class="button_0" name="" type="button" value="添加内容" onclick="location.href='${approot}${rooturi}/toAdd'"/>  
  <input class="button_0" name="" id="main_detail" type="button" value="更新文章" onclick="return dostatic('detail')"/>  
  <input class="button_0" name="" id="main_single" type="button" value="跟新single" onclick="return dostatic('single')"/>  
  <div id="loading" style="display:none"> </div> 
  </c:if>
  
</div>
  
<div class=" blank4"></div>
<table width="100%" border="0" cellspacing="0" class="table_03 clear" id="ds">
  <tr>
   <th width="4%" ><input type="checkbox" name="all" value="all" onclick="select_all(this.checked)"/></th>
   <th>标题</th>
   <th>作者</th>
   <th>来源</th>
   <th>所属栏目</th>
   <th width="20%">发布日期</th>
   <th width="16%">操作</th>
 </tr>
 <c:choose>
<c:when test="${fn:length(page.items)<1}">
<tr align="center"><td colspan="6"><font color='red'>${page.noDataMSG}</font></td></tr>
</c:when>
<c:otherwise> 
<c:forEach var="item" items="${page.items}">
 <tr align="center">
  <td><input type="checkbox" name="ids" value="${item.id}"/></td>
  <td><a href="${approot}${item.fileUrl}" target="_blank">${fn:replace(item.title,bean.bkw,bean.hightLight)}</a></td>
  <td><c:choose>
  <c:when test="${!empty item.author}"> 
  ${item.author} 
  </c:when>
  <c:otherwise>无</c:otherwise>
  </c:choose></td>
  <td><c:choose>
  <c:when test="${!empty item.befrom}"> 
  ${item.befrom} 
  </c:when>
  <c:otherwise>无</c:otherwise>
  </c:choose></td>
  <td>
    <c:choose>
  <c:when test="${!empty item.categoryId && item.categoryId!='0'}">${item.category.name}</c:when>
  <c:otherwise>无</c:otherwise>
  </c:choose>
  </td>
  <td><fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

  <td>
  <a href="${approot}${item.fileUrl}" target="_blank">预览</a>
  <a href="#" onclick="return delete_item('${item.id}','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">[删除]</a>
  <a href="${approot}${rooturi}/toEdit/${item.id}">修改</a>
   </td>
 </tr>
 </c:forEach>
 </c:otherwise>
</c:choose>
</table>
<div id="detailDiv" style="display:none"></div>
<%@ include file="/WEB-INF/jsp/admin/inc/page.jsp"%>  
</body>
</html>
