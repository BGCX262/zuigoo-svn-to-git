<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="zh-CN" xml:lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/front/inc/taglibs.jsp"%> 
<meta name="alexaVerifyID" content="ktHfDQPwUuJIpWBweF80HlS4Shw" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=EmulateIE7" http-equiv="X-UA-Compatible">
<title>${title}</title>
<meta name="keywords" content="${keywords}">
<meta name="description" content="${description}">
<link rel="stylesheet" href="${approot}/static/style/taoke/css/main.css" type="text/css">
<script language="javascript" src="${approot}/static/jquery/jquery-1.4.2.min.js" type="text/javascript"></script>
<script language="javascript" src="${approot}/static/style/taoke/js/common.js" type="text/javascript"></script>
<link type="image/x-icon" href="http://www.zuigoo.com/static/stlye/taoke/images/favicon.ico" rel="shortcut icon">
</head>
<body>
<c:import url="${webroot}/header"></c:import>
<div class="main">

<div class="warp">
<c:import url="${webroot}/cate/index/${rootCate.id}"></c:import>
<!--图片广告开始  -->
<!-- banner广告结束 -->
<div class="ju-hslogo"><span class="taobao-ys">淘宝最热卖</span>分享推荐<span class="dianji-fenlei"><a class="dianjiys">${rootCate.name}：</a>
<c:forEach var="hotcate" items="${hotCates}" varStatus="ind">
<c:if test="${ind.index<7}">
<!-- <a href="${approot}/front/tao/index/l/${hotcate.id}/0" <c:if test="${hotcate.id==lastCate.id}">class="dianjiys"</c:if>>${hotcate.name}</a> -->
<a href="${approot}/html/index/${hotcate.parentId}/${hotcate.id}.html" <c:if test="${hotcate.id==lastCate.id}">class="dianjiys"</c:if>>${hotcate.name}</a>
</c:if>
</c:forEach>
</span></div>
<div class="nr-shangmian"></div>
<div class="content">
<div class="nr-daohang"><a href="${approot}/index/f/${rootCate.id}/0" <c:if test="${isAll}">class="bj-dianjiys"</c:if>>全部</a>
<c:forEach var="rcate" items="${relationCates}" varStatus="ind">
<a href="${approot}/html/index/${rcate.parentId}/${rcate.id}.html" <c:if test="${rcate.id==lastCate.id}">class="bj-dianjiys"</c:if>>${rcate.name}
<c:if test="${ind.index==3}">
<img src="${approot}/static/style/taoke/images/hot.gif">
</c:if>
</a>
</c:forEach>
</div>
<div class="nr-zcontent">

<c:forEach var="item" items="${page.items}">
<div class="fl-waikuang">
<div class="fl-topbk"></div>
<div class="fl-conterbk">
<a href="${approot}/r/item/${item.id}" target="_blank">
<img src="${item.picUrl}">
</a>
<div class="xia-dazhenr">
<div class="lef-scys plyangs"><a href="${approot}/r/item/${item.id}" target="_blank">${item.title}</a></div>
<div class="right-fxys plxins">
<span class="yhm-ys"><a href="${approot}/r/shop/${item.id}" target="_blank">${item.nick}</a></span>
  <c:choose>
  <c:when test="${item.sellerCreditScore<=20}"><img src="${approot}/static/images/taobao/tao_rank_${item.sellerCreditScore}.gif"/> </c:when>
  <c:otherwise>未知</c:otherwise>
  </c:choose>

</div>
<div class="lef-scys">已 售 出：<strong>${item.commissionNum}</strong>件 <a href="${approot}/r/item/${item.id}"></a></div>
<div class="right-fxys">
<a href="javascript:void(0);"><img class="tp-fenxiang" src="${approot}/static/style/taoke/images/taobao_27.gif"> 分享给好友</a>
<div style="display:none;" class="subiao-yangs">
<a href="javascript:void(0);" onclick="shareWith('kaixin','淘宝热卖:${item.title}','http://www.zuigoo.com//r/item/${item.id}');">
<img src="${approot}/static/style/taoke/images/fxico2.gif"> 开心网</a>
<a href="javascript:void(0);" onclick="shareWith('renren','淘宝热卖:${item.title}','http://www.zuigoo.com/r/item/${item.id}');">
<img src="${approot}/static/style/taoke/images/fxico3.gif"> 人人网</a>
<a href="javascript:void(0);" onclick="shareWith('douban','淘宝热卖:${item.title}','http://www.zuigoo.com/r/item/${item.id}');">
<img src="${approot}/static/style/taoke/images/fxico4.gif"> 豆瓣网</a>
<a href="javascript:void(0);" onclick="shareWith('sina','淘宝热卖:${item.title}','http://www.zuigoo.com/r/item/${item.id}');">
<img src="${approot}/static/style/taoke/images/fxico5.gif"> 新浪微博</a>
<a href="javascript:void(0);" onclick="shareWith('qq','淘宝热卖:${item.title}','http://www.zuigoo.com/r/item/${item.id}');">
<img src="${approot}/static/style/taoke/images/fxico6.gif"> QQ空间</a>
<a href="javascript:void(0);" onclick="shareWith('baidu','淘宝热卖:${item.title}','http://www.zuigoo.com/r/item/${item.id}');">
<img src="${approot}/static/style/taoke/images/baidu.jpg"> 百度空间</a> -->
</div>
</div>
</div>
</div>
<div class="fl-bottombk"><span>￥${item.price}</span><div class="xihuancs">12</div><a href="${approot}/r/item/${item.id}" target="_blank">立即购买</a></div>
</div>
</c:forEach>
</div>

<!--分页 -->
<div class="fenye-ys">
<div id="pages">
<a href="${approot}/index/${from}/${cateId}/${page.firstIndex}" style="font-weight:bold">首页</a>
<a href="${approot}/index/${from}/${cateId}/${page.previousIndex}" style="font-weight:bold">上一页</a>
<c:forEach items="${page.showpages}" var="ind">
				<c:if test="${ind!=0}">
					<c:choose>
						<c:when test="${ind==page.currentPage}">
						<span>${ind}</span>
						</c:when>
						<c:otherwise>
<a href="${approot}/index/${from}/${cateId}/${(ind-1)*page.pageSize}">${ind}</a>
</c:otherwise>
</c:choose>	
</c:if>
</c:forEach>
<a href="${approot}/index/${from}/${cateId}/${page.nextIndex}" style="font-weight:bold">下一页</a>
<a href="${approot}/index/${from}/${cateId}/${page.lastIndex}" style="font-weight:bold">尾页</a>
</div>
</div>


<div class="nr-bottom"></div>
</div>
</div>

<c:import url="/footer" context="${ctxroot}"></c:import>
<c:import url="${webroot}/float2/index"></c:import>


</body></html>