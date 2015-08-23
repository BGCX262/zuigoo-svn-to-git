<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="right_w"><p>当前位置：友情链接 >>链接详情</p></div>

<div class="add clear">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="add_table">
   <tr>
    <td width="21%"><span class="right">链接类型</span></td>
    <td width="79%">
    <c:choose>
  <c:when test="${item.type==1}">文字链接</c:when>
  <c:when test="${item.type==2}">图片链接</c:when>
  <c:otherwise>未知</c:otherwise>
  </c:choose>
    </td>
    </tr>
  <tr>
    <td width="21%"><span class="right">VIP推荐</span></td>
    <td width="79%">
    <c:choose>
  <c:when test="${item.isVip==0}"><img src="${approot}/static/images/common/no.gif"/> </c:when>
  <c:when test="${item.isVip==1}"><img src="${approot}/static/images/common/yes.gif"/></c:when>
  <c:otherwise>未知</c:otherwise>
  </c:choose> </td>
    </tr>
  <tr>
    <td width="21%"><span class="right">网站名称</span></td>
    <td width="79%">${item.name}
      </td>
    </tr>
  <tr>
    <td width="21%"><span class="right">网站网址</span></td>
    <td width="79%">${item.url}
      </td>
    </tr>
  <tr>
    <td width="21%"><span class="right">图片链接</span></td>
    <td width="79%">${item.logo}
     </td>
    </tr>
  <tr>
  <tr>
    <td ><span class="right">联系方式</span></td>
    <td >${item.contact}
  </td>
    </tr>
  <tr>
  <td colspan="2" align="center">
  <input class="button_0" name="" type="button" value="返回" onclick="history.go(-1)"/>  
  </td>
  </tr>
</table>

