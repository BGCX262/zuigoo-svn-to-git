<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
</head>  
  <body style="padding-left: 10px;padding-right: 10px;padding-top: 10px;">
  <div class="title_0">您的位置：用户管理 &gt;&gt; 部门管理</div>

<div class="blank12"></div>
<div align="left">    
  <input class="button_0" name="" type="button" value="添加角色" onclick="location.href='${approot}${rooturi}/toAdd'"/>  
  <input class="button_0" name="" type="button" value="批量删除" onclick="return delete_batch('${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')"/>  
</div>
  
<div class=" blank4"></div>
<table width="100%" border="0" cellspacing="0" class="table_03 clear" id="ds">
  <tr>
  <th width="4%" ><input type="checkbox" name="all" value="all" onclick="select_all(this.checked)"/></th>
  <th>角色名称</th>
  <th width="20%">创建时间</th>
  <th width="8%">权限分配</th>
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
<td>${item.name}</td>
  <td><fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
  <td>
  <c:choose>
  <c:when test="${item.rights=='0'}"><img src="${approot}/static/images/common/no.gif"/> </c:when>
  <c:otherwise><img src="${approot}/static/images/common/yes.gif"/></c:otherwise>
  </c:choose>
  </td>
  <td>
   <a href="#" onclick="return delete_item('${item.id}','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">[删除]</a>
  <a href="${approot}${rooturi}/toEdit/${item.id}">修改</a>
     <c:if test="${item.rights=='0'}">
  <a href="${approot}/${app}/admin/role/to_add_right/${item.id}">分配权限</a>
  </c:if>
  <c:if test="${item.rights!='0'}">
  <a href="${approot}/${app}/admin/role/to_edit_right/${item.id}">修改权限</a>
  </c:if>
   </td>
 </tr>
 </c:forEach>
 </c:otherwise>
</c:choose>
</table>
<%@ include file="/WEB-INF/jsp/admin/inc/page.jsp"%>
</body>
</html>
