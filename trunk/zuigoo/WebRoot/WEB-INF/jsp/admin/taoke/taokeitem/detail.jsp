<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="right_w"><p>当前位置：内容管理 >>文章详情</p></div>

<div class="add clear">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="add_table">
    <tr>
    <td width="21%"><span class="right">标题</span></td>
    <td width="79%">${item.title}
    </td>
    </tr>
  <tr>
    <td width="21%"><span class="right">关键字</span></td>
    <td width="79%">${item.keywords}
    </td>
    </tr>
  <tr>
    <td width="21%"><span class="right">作者</span></td>
    <td width="79%">${item.author}
     </td>
    </tr>

  <tr>
    <td width="21%"><span class="right">来源</span></td>
    <td width="79%">${item.befrom}
     </td>
    </tr>
  <tr>
    <td width="21%"><span class="right">发布日期</span></td>
    <td width="79%">${item.addTime}
    </td>
    </tr>
     <tr>
    <td width="21%"><span class="right">摘要</span></td>
    <td width="79%">${item.brief}
    </td>
  </tr>
     <tr>
    <td width="21%"><span class="right">内容</span></td>
    <td width="79%">${item.content}
    </td>
  </tr>
  <tr>
  <td colspan="2" align="center">
  <input class="button_0" name="" type="button" value="返回" onclick="history.go(-1)"/>  
  </td>
  </tr>
</table>

