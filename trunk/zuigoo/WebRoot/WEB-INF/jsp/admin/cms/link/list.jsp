<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
</head>  
  <body style="padding-left: 10px;padding-right: 10px;padding-top: 10px;">
  <div class="title_0">您的位置：用户管理>> 用户列表</div>


<form method="post" action="${pageuri}/form/${pageSize}/${startIndex}" id="bean">
 <div class="search">
   	 <fieldset>
     <legend><strong>搜索</strong></legend>
        <form>
        <label><span class="blue">关键字</span>：
        <input type="text" name="bkw" value="${bean.bkw}"/></label>
        <label> <span class="blue">开始时间：</span>
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
  <input class="button_0" name="" type="button" value="添加网站" onclick="location.href='${approot}${rooturi}/toAdd'"/>  
  <input class="button_0" name="" type="button" value="批量删除" onclick="return delete_batch('${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')"/>  
</div>
  
<div class=" blank4"></div>
<table width="100%" border="0" cellspacing="0" class="table_03 clear" id="ds">
  <tr>
  <th width="4%" ><input type="checkbox" name="all" value="all" onclick="select_all(this.checked)"/></th>
  <th>网站名称</th>
  <th width="20%">网站网址</th>
  <th width="10%">链接类型</th>
  <th width="10%">首页推荐</th>
  <th width="20%">创建时间</th>
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
  <td>${fn:replace(item.name,bean.bkw,bean.hightLight)}</td>
  <td>${item.url}</td>
  <td>
  <c:choose>
  <c:when test="${item.type==1}">文字链接</c:when>
  <c:when test="${item.type==2}">图片链接</c:when>
  <c:otherwise>未知</c:otherwise>
  </c:choose>
  </td>
  <td>
  <c:choose>
  <c:when test="${item.isVip==0}"><img src="${approot}/static/images/common/no.gif"/> </c:when>
  <c:when test="${item.isVip==1}"><img src="${approot}/static/images/common/yes.gif"/></c:when>
  <c:otherwise>未知</c:otherwise>
  </c:choose>
  </td>
  <td><fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
  <td>
  <a href="${approot}${rooturi}/detail/${item.id}" onclick="showWebContent('${item.id}','60','50')" id="${item.id}">详情Frame</a>
  <a href="#" onclick="showDetail('${approot}${rooturi}/detail/${item.id}','800','400','友情链接详情')" id="${item.id}">详情Form</a>
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
