<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="zh-CN" xml:lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/front/inc/taglibs.jsp"%> 
<meta name="alexaVerifyID" content="ktHfDQPwUuJIpWBweF80HlS4Shw" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=EmulateIE7" http-equiv="X-UA-Compatible">
<title>${item.title}</title>
<meta name="keywords" content="${item.title}">
<meta name="description" content="${item.title}">
<link rel="stylesheet" href="${approot}/static/style/taoke/css/main.css" type="text/css">
<link rel="stylesheet" href="${approot}/static/style/taoke/css/article.css" type="text/css">
<script language="javascript" src="${approot}/static/jquery/jquery-1.4.2.min.js" type="text/javascript"></script>
<script language="javascript" src="${approot}/static/style/taoke/js/common.js" type="text/javascript"></script>
<script language="javascript" src="${approot}/static/style/taoke/js/slidebox.js" type="text/javascript"></script>
<link type="image/x-icon" href="http://www.zuigoo.com/static/stlye/taoke/images/favicon.ico" rel="shortcut icon">
</head>
<body>
<c:import url="${webroot}/header"></c:import>

<c:import url="/single/hots/${nowCategory.id}" context="${ctxroot}"></c:import>

<div class="main">
<div class="warp">
<c:import url="${webroot}/cate/${rootCate.id}"></c:import>
<!-- 文章正文内容 开始-->
<div id="mainfrm">

<div id="leftfrm">
 <div id="articlehd"><h3>${nowCategory.name}导购知识</h3><div></div></div>
 <div id="articlebd">
    <div id="reGoods">
    <div id="ADListRollContainer">    
     <ul>    
      <c:forEach var="item" items="${items}" varStatus="ind">  
          <li>
           <div class="slideFilm"><a class="imga" href="${item.clickUrl}" target="_blank"><img src="${item.picUrl}" alt="${item.title}" /></a>
           <a class="titlea" href="${item.clickUrl}" target="_blank">${item.title}</a>
           <span>${item.price}</span>
           </div>
         </li>
      </c:forEach>
      </ul>
      </div>
   </div>
   <script type="text/javascript">
   new SlideBox('ADListRollContainer', 3000, 'left',5);
   </script>
   
   <div id="articleTitle"><h1>${item.title}</h1></div>

   <div id="fbtime">发布人：<a href="http://www.zuigoo.com" target="blank">最购网</a>&nbsp;&nbsp;发布时间：<fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>

   <div id="articleContent">
   ${item.content}
   </div>

   <div id="ctl00_ContentPlaceHolder1_pagebottom" class="pagebottom"><span class="page"><<</span><strong>1</strong><span class="page">>></span></div>

 <!-- <div id="articleFoot">
    <div id="foothd"><span id="flag"></span><span id="retitle">相关推荐</span></div>
    <div id="footgood">
      <ul id="FootGoods">
          <li><a class="bottomgood" href="topgood.aspx?id=9821672918" target="_blank"><img src="http://img04.taobaocdn.com/bao/uploaded/i4/T1kXSdXitlXXXFGdM5_055347.jpg" alt="淘宝网热卖商品" /></a><a class="bottomtitle" href="topgood.aspx?id=9821672918" target="_blank">丁香花连衣裙 新2011夏装新款 修身名媛豹纹短袖连衣裙 明星范儿</a></li>
          <li><a class="bottomgood" href="topgood.aspx?id=5525584734" target="_blank"><img src="http://img01.taobaocdn.com/bao/uploaded/i1/T1gd5bXg8nXXc4_cZ9_102713.jpg" alt="淘宝网热卖商品" /></a><a class="bottomtitle" href="topgood.aspx?id=5525584734" target="_blank">随意美妙 O.SA 春装夏装新款韩版 打底衫 长款 短袖T恤 女ST00401</a></li>

      </ul>
    </div>
    <div id="relationArt">
         <ul>
          <li><a class="rearttitle " href="ArticleShow-3c0cc7fb-5eaf-4d62-b4b0-542efdaf05f8-1-1.html" target="_blank" >淘宝网欧莱雅热卖商品导购</a></li>
          <li><a class="rearttitle " href="ArticleShow-fa5cad5f-0d4e-45a1-b1af-8e8921884e81-1-1.html" target="_blank" >明星教你秋季如何搭配连衣裙</a></li>
         </ul>  
    </div>
   </div> -->
   <div id="footmargin"></div>
 </div>
</div>

<div id="rightfrm">
<!-- 
<div style="margin-bottom:10px;float:left;width:220px;height:220px;overflow:hidden;">
<embed src="http://a.alimama.cn/widget/yr1/yr1fixed_250_250.swf" flashvars="catid=&count=20&sz=34&type=2&i=mm_10048688_0_0" width="220" height="220" quality="high" wmode="transparent" bgcolor="#ffffff" align="middle" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
</div> -->

<div id="reshophd"><h3>热销推荐</h3></div>
   <div id="reshopbd">
         <ul>
          <c:forEach var="item" items="${items}" varStatus="ind">  
          <c:if test="${ind.index<6}">   
          <li><a class="bottomshop" href="${item.clickUrl}" target="_blank"><img src="${item.picUrl}" alt="${item.title}" target="_blank"/>${item.title}</a></li>
          </c:if>
          </c:forEach>
         </ul>  
   </div>
</div>
</div>
 </div>
</form>


<!-- 文章正文内容 结束-->
</div>
</div>

<c:import url="${webroot}/float2/single"></c:import>
<c:import url="/footer" context="${ctxroot}"></c:import>

</body></html>