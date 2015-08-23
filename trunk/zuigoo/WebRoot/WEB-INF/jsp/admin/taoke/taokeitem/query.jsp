<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>

<script type="text/javascript">
function get_item(id){
	//alert(id);
    $.ajax({
        url: approot + '/taoke/admin/taokeitem/getItem/'+id,
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
                alert('商品已经存在,请重新输入!');
            }
            if (data.msg == "error") {
                alert('系统内部错误,添加失败!');
            }
            if (data.msg == "succ") {
                alert('入库成功');
                window.parent.location.href ='${approot}/taoke/admin/taokeitem/list/menu/${online_taoke_category_id}/0/0';
            }
        }
    });
}



/**
 * @author jemmyee
 * @des 批量入库
 * @param entity entity对应的小写
 * @param redirect 成功后跳转的url
 */
function getitem_batch(entity){
    var count = 0;
    $('input[name=ids]:checked').each(function(){
        count = count + 1;
    })
    if (count == 0) {
        alert("请至少选中一条记录");
        return false;
    }
    else {
        if (!confirm("确定入库?")) {
            return false;
        }
        var ids = "";
        $("input[name='ids']:checked").each(function(){
            ids = ids + $(this).val() + "_";
        })
        var url = approot + '/admin/' + entity + '/getItemBatch/' + ids;
        
        $.ajax({
            url: url,
            dataType: 'json',
            contentType: 'application/json',
            // data: jsons,
            type: 'post',
            beforeSend: function(){
                // $('#main').disabled='true';
            },
            error: function(request){
                alert("网络故障,入库失败");
            },
            success: function(data){
                if (data.msg == "error") {
                    alert('系统内部错误,入库失败!');
                }
                if (data.msg == "succ") {
                    alert('入库成功');
                    window.parent.location.href ='${approot}/taoke/admin/taokeitem/list/menu/${online_taoke_category_id}/0/0';
                }
            }
        });
        
        
    }
    
}
</script>
</head>  
  <body style="padding-left: 10px;padding-right: 10px;padding-top: 10px;">
  <div class="title_0">您的位置：淘客管理>> 商品查询</div>


<form method="post" action="${approot}/taoke/admin/${entity}/query/form/${pageSize}/${startIndex}" id="bean">
 <div class="search">
   	 <fieldset>
     <legend><strong>搜索</strong></legend>
        <form>
        <label><span class="blue">商品类目</span>：
        <select name="cid" id="cid">
        <c:forEach var="cate" items="${cates}">
        <option value="${cate.cid}">${cate.name}</option>
        </c:forEach>
        </select></label>
        <label><span class="blue">关键字</span>：<input type="text" name="keyword" value="${bean.keyword}"/></label>
        <label><span class="blue">佣金比率</span>：<input type="text" name="startCommissionRate" value="${bean.startCommissionRate}" size="3"/>-<input type="text" name="endCommissionRate" value="${bean.endCommissionRate}" size="3"/></label>
        <label><span class="blue">30推广量</span>：<input type="text" name="startCommissionNum" value="${bean.startCommissionNum}" size="3"/>-<input type="text" name="endCommissionNum" value="${bean.endCommissionNum}" size="3"/></label>
        <label><span class="blue">商品价格</span>：<input type="text" name="startPrice" value="${bean.startPrice}" size="3"/>-<input type="text" name="endPrice" value="${bean.endPrice}" size="3"/></label>
      
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
  <input class="button_0" name="" type="button" value="批量入库" onclick="return getitem_batch('${entity}');"/>  
</div>
  
<div class=" blank4"></div>
<table width="100%" border="0" cellspacing="0" class="table_03 clear" id="ds">
  <tr>
  <th width="4%" ><input type="checkbox" name="all" value="all" onclick="select_all(this.checked)"/></th>
  <th>商品图片</th>
  <th>商品名称</th>
  <th width="10%">单价</th>
  <th width="10%">佣金</th>
  <th width="10%">佣金比率</th>
  <th width="10%">30天支出佣金</th>
  <th width="10%">30天推广量</th>
  <th width="10%">所在地</th>
  <th width="16%">操作</th>
 </tr>
 <c:choose>
<c:when test="${fn:length(items)<1}">
<tr align="center"><td colspan="6"><font color='red'>没有查找到商品!</font></td></tr>
</c:when>
<c:otherwise> 
<c:forEach var="item" items="${items}">
 <tr align="center">
  <td><input type="checkbox" name="ids" value="${item.numIid}"/></td>
  <td><img src="${item.picUrl}" height="80" width="80" align="absmiddle"/></td>
  <td>${item.title}-【${item.nick}】</td>
  <td>${item.price}</td>
  <td>${item.commission}</td>
  <td>${item.commission/item.price}</td>
  <td>${item.commissionVolume}</td>
  <td>${item.commissionNum}</td>
  <td>${item.itemLocation}</td>
  <td>
  <a href="#" onclick="return get_item('${item.numIid}');" id="addto">添加入库</a>
  <a href="${approot}/${app}/admin/${entity}/detail/${item.numIid}" onclick="showWebContent('${item.numIid}','60','50')" id="${item.numIid}">详情Frame</a>
  <a href="#" onclick="showDetail('${approot}/${app}/admin/${entity}/detail/${item.numIid}','800','400','友情链接详情')" id="${item.numIid}">详情Form</a>
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
