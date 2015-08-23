/**
 * @author jemmyee
 * @des 根据ID修改单个字段
 * @param entity entity对应的小写
 * @param id entity对应的id
 * @param redirect 成功后跳转的url
 *
 */
function modify_item(app,entity, id,attr,nval,redirect){ 
    if (!confirm("确定操作？")) {
        return false;
    }
    var url = approot +'/'+app+ 'admin/' + entity + '/modify/'+id+'/'+ attr+'/'+nval;
    //alert(url);
    $.ajax({
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        //data:jsons,
        type: 'post',
        beforeSend: function(){
        
        },
        error: function(request){
        	//alert(request.responseText);
            alert("网络故障,操作失败");
        },
        success: function(data){
            if (data.msg == "error") {
                alert('设置失败！');
            }
            if (data.msg == "succ") {
                alert('设置成功!');
                location.href = redirect;
            }
        }
    });
}

/**
 * @author jemmyee
 * @des 根据ID删除
 * @param entity entity对应的小写
 * @param id entity对应的id
 * @param redirect 成功后跳转的url
 *
 */
function delete_item(app,entity, id, redirect){
    if (!confirm("确定删除?删除后不可恢复!")) {
        return false;
    }
    var url = approot +'/'+app+ 'admin/' + entity + '/delete/' + id;
    //alert(url);
    $.ajax({
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        //data:jsons,
        type: 'post',
        beforeSend: function(){
        
        },
        error: function(request){
            alert("网络故障,操作失败");
        },
        success: function(data){
            if (data.msg == "error") {
                alert('删除失败！');
            }
            if (data.msg == "succ") {
                alert('删除成功!');
                var pf=parent.frames["tree"];
				if(typeof(pf)!='undefined')
				{
				parent.frames["tree"].location.reload();
				}
				location.href = redirect;
            }
        }
    });
}

/**
 * @author jemmyee
 * @des 添加对象入库
 * @param entity entity对应的小写
 * @param redirect 成功后跳转的url
 */
function add_item(app,entity, redirect){
    var str = $('#item').serializeObject();
    var jsons = $.toJSON(str);
	//alert(redirect);
    $.ajax({
        url: approot+'/'+app+'admin/' + entity + '/add',
        dataType: 'json',
        contentType: 'application/json',
        data: jsons,
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
				var pf=parent.frames["tree"];
				if(typeof(pf)!='undefined')
				{
				parent.frames["tree"].location.reload();
				}
				location.href = redirect;
            }
        }
    });
}


/**
 * @author jemmyee
 * @des 修改对象入库
 * @param entity entity对应的小写
 * @param redirect 成功后跳转的url
 */
function edit_item(app,entity, redirect){
    var str = $('#item').serializeObject();
    var jsons = $.toJSON(str);
    //alert(jsons);
	var url=approot +'/'+app+'admin/' + entity + '/edit';
	//alert(url);
    $.ajax({
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        data: jsons,
        type: 'post',
        beforeSend: function(){
            // $('#main').disabled='true';
        },
        error: function(request){
			
            alert("网络故障,修改失败");
        },
        success: function(data){
            if (data.msg == "rename") {
                alert('名称已经存在,请重新输入!');
            }
            if (data.msg == "error") {
                alert('系统内部错误,修改失败!');
            }
            if (data.msg == "succ") {
                alert('修改成功');
				var pf=parent.frames["tree"];
				if(typeof(pf)!='undefined')
				{
				parent.frames["tree"].location.reload();
				}
				location.href = redirect;
            }
        }
    });
}

/**
 * @author jemmyee
 * @des 批量删除
 * @param entity entity对应的小写
 * @param redirect 成功后跳转的url
 */
function delete_batch(app,entity, redirect){
    var count = 0;
    $('input[name=ids]:checked').each(function(){
        count = count + 1;
    })
    if (count == 0) {
        alert("请至少选中一条记录");
        return false;
    }
    else {
        if (!confirm("确定删除?删除后不可恢复!")) {
            return false;
        }
        var ids = "";
        $("input[name='ids']:checked").each(function(){
            ids = ids + $(this).val() + "_";
        })
        var url = approot+'/'+app+ 'admin/' + entity + '/deleteBatch/' + ids;
        
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
                alert("网络故障,删除失败");
            },
            success: function(data){
                if (data.msg == "error") {
                    alert('系统内部错误,删除失败!');
                }
                if (data.msg == "succ") {
                    alert('删除成功');
                var pf=parent.frames["tree"];
				if(typeof(pf)!='undefined')
				{
				parent.frames["tree"].location.reload();
				}
				location.href = redirect;
                }
            }
        });
        
        
    }
    
}

/**
 * @author jemmyee
 * @des 下拉翻页
 * @param entity entity对应的小写
 */
function goto_select_page(app,entity,parentId){
    var page = $('#to_page').val();
    var page_size = $('#page_size').val();
	if(page_size!=parseInt(page_size))
	{
		page_size=0;
	}
    var url = approot +'/'+app+'admin/' + entity + '/list/page/'+parentId+'/'+page_size+'/'+ page;
    // alert(url);
    window.location.href = url;
}

/**
 * @author jemmyee
 * @des 上下直接翻页
 * @param entity entity对应的小写
 */
function goto_forward_page(app,entity,topage,parentId){
   var page_size = $('#page_size').val();
   	if(page_size!=parseInt(page_size))
	{
		page_size=0;
	}
   var url = approot+'/'+app+'admin/' + entity + '/list/page/'+parentId+'/'+page_size+'/'+ topage;
    // alert(url);
    window.location.href = url;
}

function showWebContent(id,w,h){
	$("#"+id).colorbox({width:w+"%", height:h+"%", iframe:true});
}
//弹出详情界面
function showDetail(url,w,h,t){
   var href=url;
   //alert(href);
   //alert(w);
   $("#detailDiv").load(href,function(responseText, textStatus, XMLHttpRequest){
  		//alert(responseText);
  		$(this).html(responseText);
   });
   $("#detailDiv").dialog('destroy');//解决直接关闭一次后，没有刷新的情况下，无法第二次打开dialog的问题
   $("#detailDiv").dialog({
	    title:t,
	    width:800,
		height:h,
		position:['center','center'],
		modal: true,
		//zIndex: 3999,
		draggable:true,
		disabled:false
	   });
  
}
