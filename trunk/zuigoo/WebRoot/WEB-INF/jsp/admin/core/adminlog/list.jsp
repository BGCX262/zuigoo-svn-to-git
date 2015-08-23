<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
</head>  
  <body style="padding-left: 10px;padding-right: 10px;padding-top: 10px;">
  <div class="title_0">您的位置：商品分类 &gt;&gt; 商品分类添加</div>

<form method="post" action="${pageuri}/form/${pageSize}/${startIndex}" id="bean">
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
</div>
  
<div class=" blank4"></div>
<table width="100%" border="0" cellspacing="0" class="table_03 clear" id="ds">
  <tr>
    <th width="4%" ><input type="checkbox" name="all" value="all" onclick="select_all(this.checked)"/></th>
    <th width="10%">操作人</th>
   <th>日志内容</th>
   <th width="12%">操作IP</th>
   <th width="20%">操作时间</th>
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
  <td>${item.userName}</td>
  <td>${fn:replace(item.content,bean.bkw,bean.hightLight)}</td>
  <td>${item.ip}</td>
  <td><fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

  <td>
  <a href="#" onclick="return delete_item('${item.id}','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">[删除]</a>
   </td>
 </tr>
 </c:forEach>
 </c:otherwise>
</c:choose>
</table>
<%@ include file="/WEB-INF/jsp/admin/inc/page.jsp"%>  
</body>
</html>
