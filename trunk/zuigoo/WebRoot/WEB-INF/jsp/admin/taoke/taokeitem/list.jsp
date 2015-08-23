<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
<script type="text/javascript">
//弹出添加界面
function showAdd(){
   $("#addDiv").dialog('destroy');//解决直接关闭一次后，没有刷新的情况下，无法第二次打开dialog的问题
   $("#addDiv").dialog({
	    title:"添加新商品",
	    width:500,
		height:200,
		position:['center','center'],
		modal: true,
		//zIndex: 3999,
		draggable:true,
		disabled:false
	   });
  
}
</script>
 <script type="text/javascript">
            /* <![CDATA[ */
            jQuery(function(){
                jQuery("#numids").validate({
                    expression: "if (VAL) return true; else return false;",
                    message: "标题名称不能为空"
                });   
                jQuery('#item').validated(function(){
					//alert("通过验证");
                	add_item();
				});
            });
            /* ]]> */

function add_item(){
    var val = $('#numids').val();
    var url= approot + '/taoke/admin/taokeitem/add/'+val+'/blank';
    //alert("url:"+url);
    $.ajax({
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        //data: jsons,
        type: 'post',
        beforeSend: function(){
            // $('#main').disabled='true';
        },
        error: function(request){
            alert("网络故障,添加失败");
        },
        success: function(data){
            if (data.msg == "rename") {
                alert('名称已经存在,请重新输入!');
            }
            if (data.msg == "error") {
                alert('系统内部错误,添加失败!');
            }
            if (data.msg == "succ") {
                alert('添加成功');
                window.location.reload();
            }
        }
    });
}
</script>
 <script type="text/javascript">
	function dostatic(type){
	       $.ajax({
		   	url:'${approot}/taoke/admin/taokecategory/dostatic/'+type,
			data:'',
			type:'post',
			beforeSend:function(){
			   $('#main'+type).disabled='true';
			   $('#loading').html("<img  src='${approot}/static/images/loading.gif'/>文件生成中,请稍后");
			   $('#loading').show();
			},
			error:function(request){
				alert(request.responseText);
			},
			success:function(data){
//			   alert(data);
            if (data.msg == "error") {
            	$('#main'+type).attr('disabled',false);
            	$('#loading').html("html文件生成失败!");
            }
            if (data.msg == "succ") {
               
                $('#main'+type).attr('disabled',false);
 	            $('#loading').html("生成成功:共生成:[<font color='red'>"+data.count+"</font>]个html文件!");
            }
	          
			}
		   });
		}
	</script>
</head>  
  <body style="padding-left: 10px;padding-right: 10px;padding-top: 10px;">
  <div class="title_0">您的位置：商品管理 &gt;&gt; 淘客商品</div>
<div id="addDiv" style="display: none">
<form method="post" action="" id="item">
<table width="98%" border="0" cellpadding="0" cellspacing="0" class="add_table2">
  <tr>
    <td><span class="right">商品ID(多个用,隔开)<font color='red'>*</font></span></td>
    <td><textarea name="numids" id="numids" cols="40" rows="4"></textarea>
     </td>
  </tr>
</table>

<div align="center"> 
  <input type="hidden" name="categoryId" value="${online_article_category_id}"/>         
  <input class="button_0" name="" type="submit" value="提 交" />  
  <input class="button_0" name="" type="button" value="返回" onclick="history.go(-1)"/>  
  </div>
  </form>
</div>

<form method="post" action="${approot}/taoke/admin/${entity}/list/form/${parentId}/${pageSize}/${startIndex}" id="bean">
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
  <a href="${approot}/taoke/admin/taokeitem/query/menu/0/0" onclick="showWebContent('itemquery','98','90')" id="itemquery">商品查询</a> 
  <input class="button_0" name="" type="button" value="批量删除" onclick="return delete_batch('${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')"/>  
 <c:if test="${isLast==1}"> 
  <input class="button_0" name="" type="button" value="添加商品" onclick="showAdd()"/>  
  <input class="button_0" name="" id="main_detail" type="button" value="更新文章" onclick="return dostatic('detail')"/>  
  <input class="button_0" name="" id="main_single" type="button" value="更新single" onclick="return dostatic('single')"/>  
  <div id="loading" style="display:none"> </div> 
  </c:if>
</div>
  
<div class=" blank4"></div>
<table width="100%" border="0" cellspacing="0" class="table_03 clear" id="ds">
  <tr>
   <th width="4%" ><input type="checkbox" name="all" value="all" onclick="select_all(this.checked)"/></th>
  <th>商品图片</th>
  <th>商品名称</th>
  <th>商家信用</th>
  <th width="10%">单价</th>
  <th width="10%">佣金</th>
  <th width="10%">佣金比率</th>
  <th width="10%">30天支出佣金</th>
  <th width="10%">30天推广量</th>
  <th width="10%">入库时间</th>
  <th width="10%">是否重要</th>
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
  <td><a href="${item.clickUrl}" target="_blank"><img src="${item.picUrl}" height="80" width="80" align="absmiddle"/></a></td>
  <td><a href="${item.clickUrl}" target="_blank">${item.title}</a>-<a href="${item.shopClickUrl}" target="_blank">【${item.nick}】</a></td>
  <td>
  <c:choose>
  <c:when test="${item.sellerCreditScore<=20}"><img src="${approot}/static/images/taobao/tao_rank_${item.sellerCreditScore}.gif"/> </c:when>
  <c:otherwise>未知</c:otherwise>
  </c:choose>
  </td>
  <td>${item.price}</td>
  <td>${item.commission}</td>
  <td>${item.commission/item.price}</td>
  <td>${item.commissionVolume}</td>
  <td>${item.commissionNum}</td>
  <td>
  <c:choose>
  <c:when test="${item.isVip=='1'}"> 
  <a href="#" title="点击取消推荐" onclick="return modify_item('${item.id}','isVip','0','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">
  <img src="${approot}/static/images/common/yes.gif"/> 
  </a>
  </c:when>
  <c:when test="${item.isVip=='0'}"> 
  <a href="#" title="点击进行推荐" onclick="return modify_item('${item.id}','isVip','1','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">
  <img src="${approot}/static/images/common/no.gif"/>
  </a>
   </c:when>
  <c:otherwise>未知</c:otherwise>
  </c:choose>
  </td>
  <td><fmt:formatDate value="${item.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
  <td>
  <a href="#" onclick="return delete_item('${item.id}','${approot}${rooturi}','${pageuri}/menu/${pageSize}/${startIndex}')">[删除]</a>
  <a href="${item.clickUrl}" onclick="showWebContent('${item.id}','98','90')" id="${item.id}">商品详情</a>
  <a href="${item.shopClickUrl}" onclick="showWebContent('${item.id}_1','98','90')" id="${item.id}_1">店铺详情</a>
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
