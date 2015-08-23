<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
</head>  
  <body style="padding-left: 10px;padding-right: 10px;padding-top: 10px;background:#E8F5FE;">
  <div class="title_0">您的位置：商品管理 &gt;&gt; 分类管理</div>

<div class="blank12"></div>
<div align="left">    
  <input class="button_0" name="" type="button" value="添加分类" onclick="location.href='${approot}${rooturi}/toAdd'"/>  
  <input class="button_0" name="" type="button" value="批量删除" onclick="return delete_batch('${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')"/>  
</div>
  
<div class=" blank4"></div>
<table width="100%" border="0" cellspacing="0" class="table_03 clear" id="ds">
  <tr>
    <th width="4%" ><input type="checkbox" name="all" value="all" onclick="select_all(this.checked)"/></th>
   <th width="10%">分类名称</th>
   <th width="8%">编码</th>
   <th width="15%">seo标题</th>
   <th>seo关键字</th>
   <th>seo描述</th>
    <th width="8%">状态</th>
    <th width="8%">推荐</th>
   <th width="8%">是否品牌</th>
   <th width="8%">是否底层</th>
  <th width="15%">创建时间</th>
  <th width="8%">操作</th>
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
 <td>${item.code}</td>
 <td>${item.seoTitle}</td>
 <td>${item.seoKeyword}</td>
 <td>${item.seoDesc}</td>
 <td>
  <c:choose>
  <c:when test="${item.isDelete=='1'}"> 
  <a href="#" title="点击启用" onclick="return modify_item('${item.id}','isDelete','0','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">
  <img src="${approot}/static/images/common/yes.gif"/> 
  </a>
  </c:when>
  <c:when test="${item.isDelete=='0'}"> 
  <a href="#" title="点击禁用" onclick="return modify_item('${item.id}','isDelete','1','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">
  <img src="${approot}/static/images/common/no.gif"/>
  </a>
   </c:when>
  <c:otherwise>未知</c:otherwise>
  </c:choose>
 </td>
 <td>
  <c:choose>
  <c:when test="${item.isRecommend=='1'}"> 
  <a href="#" title="点击取消推荐" onclick="return modify_item('${item.id}','isRecommend','0','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">
  <img src="${approot}/static/images/common/yes.gif"/> 
  </a>
  </c:when>
  <c:when test="${item.isRecommend=='0'}"> 
  <a href="#" title="点击进行推荐" onclick="return modify_item('${item.id}','isRecommend','1','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">
  <img src="${approot}/static/images/common/no.gif"/>
  </a>
   </c:when>
  <c:otherwise>未知</c:otherwise>
  </c:choose>
 </td>
   <td>
   <c:choose>
  <c:when test="${item.type=='1'}"> 
  <a href="#" title="点击取消推荐" onclick="return modify_item('${item.id}','type','0','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">
  <img src="${approot}/static/images/common/yes.gif"/> 
  </a>
  </c:when>
  <c:when test="${item.type=='0'}"> 
  <a href="#" title="点击进行推荐" onclick="return modify_item('${item.id}','type','1','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">
  <img src="${approot}/static/images/common/no.gif"/>
  </a>
   </c:when>
  <c:otherwise>未知</c:otherwise>
  </c:choose>
  </td>
   <td>
   <c:choose>
  <c:when test="${item.isLast=='1'}"> 
  <a href="#" title="点击取消推荐" onclick="return modify_item('${item.id}','isLast','0','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">
  <img src="${approot}/static/images/common/yes.gif"/> 
  </a>
  </c:when>
  <c:when test="${item.isLast=='0'}"> 
  <a href="#" title="点击进行推荐" onclick="return modify_item('${item.id}','isLast','1','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">
  <img src="${approot}/static/images/common/no.gif"/>
  </a>
   </c:when>
  <c:otherwise>未知</c:otherwise>
  </c:choose>
  </td>
  <td><fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
  <td>
  <a href="#" onclick="return delete_item('${item.id}','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">[删除]</a>
  <a href="${approot}${rooturi}/toEdit/${item.id}">修改</a>
   </td>
 </tr>
 </c:forEach>
 </c:otherwise>
</c:choose>
</table>
<%@ include file="/WEB-INF/jsp/admin/inc/page.jsp"%>
</body>
</html>
