/**
 * @author jemmyee
 * @des 全选 反选
 */
function select_all(val){
    if (val) 
        $('input[name=ids]').each(function(){
            $(this).attr('checked', true);
        })
    else 
        $('input[name=ids]').each(function(){
            $(this).attr('checked', false);
        })
}
/**
 * @desc 判断包含关系
 * @author jemmyee
 */
function strcontains(string, substr, isIgnoreCase) {
	if (isIgnoreCase) {
		string = string.toLowerCase();
		substr = substr.toLowerCase();
		// alert(string);
	}
	var startChar = substr.substring(0, 1);
	var strLen = substr.length;
	for ( var j = 0; j < string.length - strLen + 1; j++) {
		if (string.charAt(j) == startChar)// 如果匹配起始字符,开始查找
		{
			if (string.substring(j, j + strLen) == substr)// 如果从j开始的字符与str匹配
			{
				return true;
			}
		}
	}
	return false;
}
/**
 * @author jemmyee
 * @date 2011-08-30
 * @des ajax 表单提交 弹出提示信息,无tree刷新
 * @param ispopup 是否弹出提示  yes  no
 * @param tree 是否有树需要刷新  yes  no
 * @param info
 *            信息提示 eg:添加
 * @param url
 *            要提交的url
 * @param back
 *            成功后跳转的url no表示不跳转
 */
function ajax_submit(ispopup,tree,info,form,url,back) {
	var str = $('#'+form).serializeObject();
	var jsons = $.toJSON(str);
	// alert(jsons);
	//alert(url);
	//alert(back);
	$.ajax( {
		url : url,
		dataType : 'json',
		contentType : 'application/json',
		data : jsons,
		type : 'post',
		beforeSend : function() {
			// $('#main').disabled='true';
	},
	error : function(request) {
		alert('格式错误,' + info + '失败!');
	},
	success : function(data) {
		// alert(data.msg);
		// 登录提示
		if (data.msg == "error") {
			alert('系统出现问题,' + info + '失败!');
		}
		if (data.msg == "login_vcode_error") {
			$('#msg').html('验证码不正确,' + info + '失败!');
		}
		if (data.msg == "login_error") {
			$('#msg').html('账号或者密码不对,' + info + '失败!');
		}
		// 登录提示over
		if (data.msg == "rename") {
			alert('名称已经存在,请重新输入!');
		}
		if (data.msg == "retitle") {
			alert('标题已经存在,请重新输入!');
		}
		if (data.msg == "reuser") {
			alert('接收人不存在!');
		}
		if (data.msg == "error") {
			alert('系统错误,' + info + '失败!');
		}
		if (data.msg == "password_error") {
			alert('原始密码不对,' + info + '失败!');
		}
		//发布任务提示
		if (data.msg == "money_not_enough") {
			alert('亲,您的账户余额不足!');
		}
		if (data.msg == "virtual_not_enough") {
			alert('亲,您的发布点余额不足');
		}
		if (data.msg == "succ") {
			if(ispopup=='yes')
			{
				alert(info + '成功');
			}
			if(tree=='yes')
			{
				parent.frames["tree"].location.reload();
			}
			if(back!= 'no') {
				location.href = back;
			}
		}
	}
	});
}
/**
 * @author jemmyee
 * @des 根据ID修改单个字段
 * @param entity entity对应的小写
 * @param id entity对应的id
 * @param redirect 成功后跳转的url
 *
 */
function modify_item(id,attr,nval,rooturi,back){ 
    if (!confirm("确定操作？")) {
        return false;
    }
    var url = rooturi+ '/modify/'+id+'/'+ attr+'/'+nval;
    //alert(url);
    //alert(back);
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
            alert("系统出现问题,操作失败");
        },
        success: function(data){
            if (data.msg == "error") {
                alert('设置失败！');
            }
            if (data.msg == "succ") {
                alert('设置成功!');
                location.href = back;
            }
        }
    });
}


/**
 * @author jemmyee
 * @des 根据ID删除 彻底删除
 * @param entity entity对应的小写
 * @param id entity对应的id
 * @param redirect 成功后跳转的url
 *
 */
function delete_item(id,rooturi,back){
    if (!confirm("确定删除?删除后不可恢复!")) {
        return false;
    }
    var url =rooturi+ '/delete/' + id;
    //alert(back);
    $.ajax({
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        //data:jsons,
        type: 'post',
        beforeSend: function(){
        
        },
        error: function(request){
            alert("系统出现问题,删除失败");
        },
        success: function(data){
            if (data.msg == "error") {
                alert('删除失败！');
            }
            if (data.msg == "succ") {
                alert('删除成功!');
				location.href = back;
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
function delete_batch(rooturi,back){
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
        var url =rooturi+ '/deleteBatch/' + ids;
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
                alert("系统出现问题,删除失败");
            },
            success: function(data){
                if (data.msg == "error") {
                    alert('系统内部错误,删除失败!');
                }
                if (data.msg == "succ") {
                    alert('删除成功');
				location.href = back;
                }
            }
        });
        
        
    }
    
}
/**
 * @desc  load加载tabs内容
 * @param href
 * @param tabID
 * @return
 */
function load_tabs_html(href, tabID) {
	//alert(href);
	//alert(tabID);
	$('#tabs-' + tabID).load(href,
			function(responseText, textStatus, XMLHttpRequest) {
			//alert(responseText);
			$(this).html(responseText);
		});
}

/**
 * @author jemmyee
 * @des 下拉翻页
 * @param entity
 *            entity对应的小写
 */
function goto_select_page(pageuri, tabID) {
	var page = $('#to_page').val();
	var page_size = $('#page_size').val();
	if (page_size != parseInt(page_size)) {
		page_size = 0;
	}
	var url =pageuri + '/page/' + page_size + '/' + page;
	// alert(url);
	if (tabID == -1) {
		window.location.href = url;
	} else {
		load_tabs_html(url, tabID);
	}
}

/**
 * @author jemmyee
 * @des 上下直接翻页
 * @param entity
 *            entity对应的小写
 */
function goto_forward_page(pageuri, topage, tabID) {
	var page_size = $('#page_size').val();
	if (page_size != parseInt(page_size)) {
		page_size = 0;
	}
	var url =pageuri + '/page/' + page_size + '/' + topage;
	//alert(url);
	//alert(tabID);
	if (tabID == -1) {
		window.location.href = url;
	} else {
		load_tabs_html(url, tabID);
	}
}

function showWebContent(id,w,h){
	//alert(id);
	$("#"+id).colorbox({width:w+"%", height:h+"%", iframe:true});
}

//直接弹出本页面的内容
function showFloatContent(id,w,h,t){
	$("#"+id).dialog('destroy');//解决直接关闭一次后，没有刷新的情况下，无法第二次打开dialog的问题
	$("#"+id).dialog({
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
function closeDetail(){
	 $("#detailDiv").dialog('destroy');	
}
