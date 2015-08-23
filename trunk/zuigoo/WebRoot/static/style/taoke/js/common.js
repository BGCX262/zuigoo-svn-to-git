/**
 * @author 愚人码头
 */
(function($){
	$.fn.imgTxtCut=function(options){
		var opts = $.extend({},$.fn.imgTxtCut.deflunt,options);
		var targetObj=$(this).find(".imgs");
		var targetObjH=$(this).find(".imgs img").height()
		return this.each(function() {
			$.fn.imgTxtCut.effect[opts.effects](targetObj,targetObjH,opts);
			
    	});
	};
	$.fn.imgTxtCut.effect = {
		fade:function(targetObj,targetObjH,opts){
			targetObj.hover(function(){
			$(this).find('img').stop().animate({opacity:0.5}, opts.speed);
			},function(){
				$(this).find('img').stop().animate({opacity:1},opts.speed);
			})
		}
	};
	$.fn.imgTxtCut.deflunt={
		effects : "flaser",
		speed : "normal"
	};
})(jQuery);



function replaceLink(){
	var arr		= document.getElementsByTagName('a');
	for(var i=0,l = arr.length;i<l;i++){
		var	e	= arr[i];
		if(e.attributes['linktype']!=undefined){
			
			if(e.attributes['linktype'].nodeValue=='click-url'){
				e.href	= defaultClickUrl.replace('defaultId',e.attributes['nid'].nodeValue);
			}else if(e.attributes['linktype'].nodeValue=='shop-click-url'){
				e.href	= defaultClickUrl.replace('id/defaultId','sid/'+e.attributes['nid'].nodeValue);
				e.href	= e.href.replace('id=defaultId','sid='+e.attributes['nid'].nodeValue);
				e.href	= e.href.replace('id-defaultId','sid-'+e.attributes['nid'].nodeValue);
			}
		}
	}
}

function replaceImage(){
	var arr		= document.getElementsByTagName('img');
	for(var i=0,l = arr.length;i<l;i++){
		var	e	= arr[i];
		if(e.attributes['u']!=undefined){
			var tmp 	= e.attributes['u'].nodeValue;
			var src 	= "";
			var strs	= tmp.split("_");
			for (ii=0;ii<strs.length ;ii++ )
			{
				if(ii==0){
					src = src + base64decode(strs[ii]);
				}else{
					src = src + "_" + strs[ii];
				}
			} 
			e.src = src;
		}
	}
}

function base64encode(str) { 
str = utf16to8(str); 
var out, i, len;    
var c1, c2, c3;    
var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"; 
len = str.length;    
i = 0;    
out = "";    
while(i < len) { 
c1 = str.charCodeAt(i++) & 0xff;    
if(i == len){ 
out += base64EncodeChars.charAt(c1 >> 2); 
out += base64EncodeChars.charAt((c1 & 0x3) << 4); 
out += "==";       
break;    
}    
c2 = str.charCodeAt(i++);    
if(i == len)    {        
out += base64EncodeChars.charAt(c1 >> 2);        
out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));        
out += base64EncodeChars.charAt((c2 & 0xF) << 2);        
out += "=";        
break;    
} 
c3 = str.charCodeAt(i++);    
out += base64EncodeChars.charAt(c1 >> 2);    
out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));    
out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >>6));    
out += base64EncodeChars.charAt(c3 & 0x3F);    
}   
return out; 
} 
function base64decode(str){ 
var c1, c2, c3, c4;    
var i, len, out;   
var base64DecodeChars = new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 
52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, 
-1,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 
15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, 
-1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 
41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1); 
len = str.length;    
i = 0;    
out = "";    
while(i < len) {    
do {        
c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];    
} 
while(i < len && c1 == -1);    
if(c1 == -1) break;   
do {        
c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];    
} 
while(i < len && c2 == -1);    
if(c2 == -1)  break;    
out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));    
do {        
c3 = str.charCodeAt(i++) & 0xff;        
if(c3 == 61) return out;        
c3 = base64DecodeChars[c3];    
} 
while(i < len && c3 == -1);    
if(c3 == -1) break;    
out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));    
do {        
c4 = str.charCodeAt(i++) & 0xff;        
if(c4 == 61) return out;        
c4 = base64DecodeChars[c4];    
} 
while(i < len && c4 == -1);    
if(c4 == -1) break;    
out += String.fromCharCode(((c3 & 0x03) << 6) | c4);    
} 
out = utf8to16(out); 
return out; 
} 
function utf16to8(str) {    
var out, i, len, c;    
out = "";    len = str.length;    
for(i = 0; i < len; i++) {    
c = str.charCodeAt(i);    
if ((c >= 0x0001) && (c <= 0x007F)) {        
out += str.charAt(i);    
} else if (c > 0x07FF) {        
out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));        
out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));        
out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));    
} else {        
out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));        
out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));    
}    
}    
return out; 
} 

function utf8to16(str) {    
var out, i, len, c;    
var char2, char3;    
out = "";    
len = str.length;    
i = 0;    
while(i < len) {    
c = str.charCodeAt(i++);    
switch(c >> 4){       
case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:            
out += str.charAt(i-1);        
break;      
case 12: case 13:              
char2 = str.charCodeAt(i++);        
out += String.fromCharCode(((c & 0x1F) << 6)|(char2 & 0x3F));        
break;      
case 14:               
char2 = str.charCodeAt(i++);        
char3 = str.charCodeAt(i++);        
out += String.fromCharCode(((c & 0x0F) << 12)|((char2 & 0x3F) << 6)|((char3 & 0x3F) << 0)); 
break;    
} 
}   
return out; 
}


//JavaScript Document
var t = n = 0, count;
	$(document).ready(function(){	
		count=$(".left-nr").length;
		$("#banner_list .left-nr:not(:first-child)").hide();
		$("#banner li").click(function() {
			var i = $(this).text() - 1;//获取Li元素内的值，即1，2，3，4
			n = i;
			if (i >= count) return;
			$(".left-nr").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000);
			document.getElementById("banner").style.background="";
			$(this).toggleClass("on");
			$(this).siblings().removeAttr("class");
		});
		t = setInterval("showAuto()", 4000);
		$("#banner").hover(function(){clearInterval(t)}, function(){t = setInterval("showAuto()", 4000);});
	})
	
	function showAuto()
	{
		n = n >=(count - 1) ? 0 : ++n;
		$("#banner li").eq(n).trigger('click');
	}

(function($){
$(function(){
$('.right-fxys a').click(function(e){
$d = $(this).siblings("div");
if( $d.css("display") == "none" ) {
$d.show();
} else {
$d.hide();
}
e.preventDefault();
});
$('.right-fxys div').hover(function(){ }, function(){ $(this).hide(); });
});
})(jQuery);

function share_kaixin(a,b)
{
	var id=a;
	var kxurl=b;
	var cont=$("#content_"+a).html();
	var kurl="rcontent="+encodeURIComponent(cont)+"&rtitle="+encodeURIComponent(cont)+"&rurl="+encodeURIComponent(kxurl);
	window.open("http://www.kaixin001.com/repaste/share.php?"+kurl);
	return;
	

}
function share_renren(a,b)
{
	var id=a;
	var kxurl=b;
	var cont=$("#content_"+a).html();
	var kurl="title="+encodeURIComponent(cont)+"&link="+encodeURI(kxurl);
	window.open("http://share.renren.com/share/buttonshare.do?"+kurl,'favit','');
	return;
	

}
function share_sina(a,b)
{
	var id=a;
	var kxurl=b;
	var cont=$("#content_"+a).html();
	var kurl="title="+encodeURIComponent(cont)+"&url="+encodeURI(kxurl);
	window.open("http://v.t.sina.com.cn/share/share.php?"+kurl,'favit','');
	return;
	

}
function share_douban(a,b)
{
	var id=a;
	var kxurl=b;
	var cont=$("#content_"+a).html();
	var kurl="title="+encodeURIComponent(cont)+"&url="+encodeURI(kxurl);
	window.open("http://www.douban.com/recommend/?"+kurl,'favit','');
	return;
	

}
function share_qzone(a,b)
{
	var id=a;
	var kxurl=b;
	var cont=$("#content_"+a).html();
	var kurl="url="+encodeURIComponent(kxurl);
	window.open("http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?"+kurl,'favit','');
	return;
	

}
function share_tt(a,b)
{
	var id=a;
	var kxurl=b;
	var cont=$("#content_"+a).html();
	var _t = encodeURI(cont);
	var _url = encodeURI(kxurl);
	var _appkey = encodeURI("bad8da5fbecc493b973d7ef5ff6a4640");//你从腾讯获得的appkey
	var _pic = encodeURI('');//（列如：var _pic='图片url1|图片url2|图片url3....）
	var _site = '';//你的网站地址
	var _u = 'http://v.t.qq.com/share/share.php?title='+_t+'&url='+_url+'&appkey='+_appkey+'&site='+_site+'&pic='+_pic;
	window.open( _u,'转播到腾讯微博', 'width=700, height=680, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' );
	

}
function AddFavorite(sURL, sTitle)
{
   try
   {
       window.external.addFavorite(sURL, sTitle);
   }
   catch (e)
   {
       try
       {
           window.sidebar.addPanel(sTitle, sURL, "");
       }
       catch (e)
       {
           alert("加入收藏失败，请使用Ctrl+D进行添加");
       }
   }
}
function bookmark(){
	//alert(1111);
	//return false;
	AddFavorite('http://www.zuigoo.com', '淘宝导购网站_淘宝推荐购物_淘宝商城特卖_淘宝网热卖导购_淘宝聚划算尽在zuigoo.com');
	return false;
}

function shareWith(from,content,url)
{
	var title=encodeURIComponent(content);
	var url=encodeURIComponent(url);
	var to;
	if(from=='sina')
	{
		to="http://v.t.sina.com.cn/share/share.php?url="+url+"&title="+title;
	}
	if(from=='kaixin')
	{
		to="http://www.kaixin001.com/repaste/share.php?rurl="+url+"&rtitle="+title;
	}
	if(from=='renren')
	{
		to="http://share.renren.com/share/buttonshare.do?link="+url+"&title="+title;
	}
	if(from=='douban')
	{
		to="http://www.douban.com/recommend/?url="+url+"&title="+title;
	}
	if(from=='qq')
	{
		to="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url="+url+"&title="+title;

	}
	if(from=='baidu')
	{
		to="http://apps.hi.baidu.com/share?url="+url+"&title="+title;

	}
	window.open(to);
	return false;
}

