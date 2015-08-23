<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
</head>  
  <body style="padding-left: 10px;padding-right: 10px;padding-top: 10px;">
  <div class="title_0">您的位置：会员管理>>会员</div>

<div class="blank12"></div>
<div align="left">    
  <input class="button_0" name="" type="button" value="添加支付方式" onclick="location.href='${approot}${rooturi}/toAdd'"/>  
  <input class="button_0" name="" type="button" value="批量删除" onclick="return delete_batch('${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')"/>  
</div>
  
<div class=" blank4"></div>
<table width="100%" border="0" cellspacing="0" class="table_03 clear" id="ds">
  <tr>
  <th width="4%" ><input type="checkbox" name="all" value="all" onclick="select_all(this.checked)"/></th>
  <th width="10%">支付名称</th>
  <th>支付说明</th>
  <th width="6%">是否启用</th>
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
  <td>${item.brief}</td>
  <td>
  <c:choose>
  <c:when test="${item.isEnable=='1'}"> 
  <a href="#" title="点击禁用" onclick="return modify_item('${app}','${entity}','${item.id}','isEnable','0','${approot}/${app}/admin/${entity}/list/menu/${parentId}/${pageSize}/${startIndex}')">
  <img src="${approot}/static/images/common/yes.gif"/> 
  </a>
  </c:when>
  <c:when test="${item.isEnable=='0'}"> 
  <a href="#" title="点击启用" onclick="return modify_item('${app}','${entity}','${item.id}','isEnable','1','${approot}/${app}/admin/${entity}/list/menu/${parentId}/${pageSize}/${startIndex}')">
  <img src="${approot}/static/images/common/no.gif"/>
  </a>
   </c:when>
  <c:otherwise>未知</c:otherwise>
  </c:choose>
  </td>
  <td>
   <a href="#" onclick="return delete_item('${item.id}','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">[删除]</a>
  <a href="${approot}${rooturi}/toEdit/${item.id}">修改</a>
  <a href="#" onclick="showDetail('${approot}/${app}/admin/${entity}/detail/${item.id}','800','500','支付信息详情')" id="${item.id}">详情</a>
  <a href="${approot}/${app}/admin/${entity}/toTest/${item.code}" target="_blank" id="${item.id}">支付测试</a>

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
