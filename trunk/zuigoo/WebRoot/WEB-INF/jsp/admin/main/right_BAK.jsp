<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <%@ include file="/WEB-INF/jsp/admin/inc/taglibs.jsp"%>
	<%@ include file="/WEB-INF/jsp/admin/inc/meta.jsp"%>
  </head>
<body>
<!--
 <div class="main">
 	<div class="title_0">您的位置：商品分类 &gt; 商品分类添加</div>
    <!--搜索框-->
   <div class="search">
   	 <fieldset>
     <legend>搜索：</legend>
        <form>
        <label><strong>关键字</strong>：
        <input type="text" /></label>
        <label> <span class="blue"> 开始时间：</span>
        <input class="date" type="text" /></label>
        <label> <span class="blue">结束时间：</span>
        <input class="date"  type="text" /></label>
     </form>
        
        <form>
        <label>类型：
        <input type="text" /></label>
        <label> 仓库：
        <input type="text" /></label>
        <label> 描述：
        <input type="text" /></label>
        </form>
  <div align="center">      
  <input class="button_0" name="" type="button" value="提 交" />  
  <input class="button_0" name="" type="button" value="删 除" />  
  <input class="button_0" name="" type="button" value="查 询" />  
  <input class="button_0" name="" type="button" value="批量删除" /> 
  </div>
     </fieldset>
   </div>
   
    <div class=" blank12"></div>
    <!--列表框  无边框-->
   <table class="table_01 " width="40%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th colspan="2">待处理事务</th>
    </tr>
  <tr>
    <td width="77%"><a href="#">等待发货订单</a> </td>
    <td  width="23%"><a href="#">100</a>条</td>
  </tr>
  <tr>
    <td><a href="#">申请退款订单</a> </td>
    <td><a href="#">200</a>条</td>
  </tr>
  <tr>
    <td><a href="#">未处理提现申请</a> </td>
    <td><a href="#">0</a>条</td>
  </tr>
  <tr>
    <td><a href="#">商品库存报警</a></td>
    <td><a href="#">0</a>条</td>
  </tr>
</table>

<div class="blank12"></div>
 <!--列表框  有边框-->
   <table class="table_02 " width="40%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th colspan="2">待处理事务</th>
    </tr>
  <tr>
    <td width="77%"><a href="#">等待发货订单</a> </td>
    <td width="23%"><a href="#">100</a>条</td>
  </tr>
  <tr>
    <td><a href="#">申请退款订单</a> </td>
    <td><a href="#">200</a>条</td>
  </tr>
  <tr>
    <td><a href="#">未处理提现申请</a> </td>
    <td><a href="#">0</a>条</td>
  </tr>
  <tr>
    <td><a href="#">商品库存报警</a></td>
    <td><a href="#">0</a>条</td>
  </tr>
</table>
<div class="blank12"></div>
<!--单双行表格-->
<table class="clear" width="68%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>  <div align="right">      
  <input class="button_0" name="" type="button" value="批量审核" />  
  <input class="button_0" name="" type="button" value="批量删除" /> 
  </div></td>
  </tr>
</table>
<div class=" blank4"></div>
<table width="68%" border="0" cellspacing="0" class="table_03 clear" id="ds">
  <tr>
    <th width="4%" ><input type="checkbox" name="checkbox" id="checkbox" /></th>
    <th width="7%" align="center">序号</th>
    <th width="38%">标题</th>
    <th width="24%" align="center">发布日期</th>
    <th width="20%"  align=center>操作</th>
 </tr>
  <tr align="center">
    <td><input type="checkbox" name="checkbox2" id="checkbox2" /></td>
    <td height="30">1</td>
    <td height="30" align="left"><a href="#">等待发货订单</a><a href='#'></a></td>
    <td height="30">2009-02-08 20:57:47</td>
    <td>[<a href="#">修改</a>] [<a href="#">查看</a>] [删除]</td>
  </tr>
  <tr align="center">
    <td><input type="checkbox" name="checkbox3" id="checkbox3" /></td>
    <td height="30">2</td>
    <td height="30" align="left"><a href="#">等待发货订单</a><a href='#'></a></td>
    <td height="30">2009-02-08 20:57:47</td>
    <td>[<a href="#">修改</a>] [<a href="#">查看</a>] [删除]</td>
  </tr>
  <tr align="center">
    <td><input type="checkbox" name="checkbox4" id="checkbox4" /></td>
    <td height="30">3</td>
    <td height="30" align="left"><a href="#">未处理提现申请</a><a href='#'></a></td>
    <td height="30">2009-02-08 20:57:47</td>
    <td>[<a href="#">修改</a>] [<a href="#">查看</a>] [删除]</td>
  </tr>
  <tr align="center">
    <td><input type="checkbox" name="checkbox5" id="checkbox5" /></td>
    <td height="30">4</td>
    <td height="30" align="left"><a href="#">未处理提现申请</a><a href='#' title="|发表人:管理员 |部门:系统管理员"></a></td>
    <td height="30">2009-02-08 20:57:47</td>
    <td>[<a href="#">修改</a>] [<a href="#">查看</a>] [删除]</td>
  </tr>
  <tr align="center">
    <td><input type="checkbox" name="checkbox6" id="checkbox6" /></td>
    <td height="30">5</td>
    <td height="30" align="left"><a href="#">未处理提现申请</a><a href='#' title="|发表人:管理员 |部门:系统管理员"></a></td>
    <td height="30">2009-02-08 20:57:47</td>
    <td>[<a href="#">修改</a>] [<a href="#">查看</a>] [删除]</td>
  </tr>   
</table>
<div class=" blank4"></div>
<table width="68%" border="0" cellspacing="0" cellpadding="0" class="page ">
  <tr>
    <td width="40%">共有<span class="red">31</span>条记录，当前第<span class="red">1</span>页，共<span class="red">1</span>页</td>
    <td width="60%">    <span class="button right"><a href="#">末页</a></span><span class="button right"><a href="#">下一页</a></span><span class="right number"> 2 </span> <span class="right number_on"> <a href="#">1</a> </span>  <span class="button right">上一页</span>  <span class="button right">首页</span>
    
    <div  class="right">每页
      <input type="text" name="textfield" id="textfield" /> 
      转到 
      <input type="text" name="textfield2" id="textfield2" /> 
      页 </div>  </td>
    </tr>
</table>
<div class=" blank12"></div>

<!--选项卡-->
<div id="tabs0"> 
 <ul class="menu0" id="menu0"> 
  <li onclick="setTab(0,0)" class="hover">新增订单</li> 
  <li onclick="setTab(0,1)">待发货订单</li> 
  <li onclick="setTab(0,2)">已发货订单</li> 
  <li onclick="setTab(0,3)">其他订单</li> 
 </ul> 
 <div class="main" id="main0"> 
  <ul class="block">
  <li>
  
  <table class="tab_table" width="100%" border="0" cellspacing="0" cellpadding="0" id="ds">
  <tr>
    <th width="14%">订单号</th>
    <th width="25%">下单时间</th>
    <th width="23%">订单金额</th>
    <th width="19%">状态</th>
    <th width="19%">操作</th>
  </tr>
  <tr>
    <td><a href="#">115426565</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td>查看</td>
  </tr>
  <tr>
    <td><a href="#">115426565</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td><a href="#">查看</a></td>
  </tr>
  <tr>
    <td><a href="#">115426565</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td><a href="#">查看</a></td>
  </tr>
  <tr>
    <td><a href="#">115426565</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td><a href="#">查看</a></td>
  </tr>
  <tr>
    <td>115426565</td>
    <td>20110210</td>
    <td>528.00</td>
    <td>未发货</td>
    <td>查看</td>
  </tr>
</table>

</li>
  </ul> 
  <ul><li>
    <table class="tab_table" width="100%" border="0" cellspacing="0" cellpadding="0" id="ds">
  <tr>
    <th width="14%">订单号</th>
    <th width="25%">下单时间</th>
    <th width="23%">订单金额</th>
    <th width="19%">状态</th>
    <th width="19%">操作</th>
  </tr>
  <tr>
    <td><a href="#">22222222</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td>查看</td>
  </tr>
  <tr>
    <td><a href="#">115426565</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td><a href="#">查看</a></td>
  </tr>
  <tr>
    <td><a href="#">115426565</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td><a href="#">查看</a></td>
  </tr>
  <tr>
    <td><a href="#">115426565</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td><a href="#">查看</a></td>
  </tr>
  <tr>
    <td>115426565</td>
    <td>20110210</td>
    <td>528.00</td>
    <td>未发货</td>
    <td>查看</td>
  </tr>
</table>
  
  </li></ul> 
  <ul><li>
    <table class="tab_table" width="100%" border="0" cellspacing="0" cellpadding="0" id="ds">
  <tr>
    <th width="14%">订单号</th>
    <th width="25%">下单时间</th>
    <th width="23%">订单金额</th>
    <th width="19%">状态</th>
    <th width="19%">操作</th>
  </tr>
  <tr>
    <td><a href="#">33333333</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td>查看</td>
  </tr>
  <tr>
    <td><a href="#">115426565</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td><a href="#">查看</a></td>
  </tr>
  <tr>
    <td><a href="#">115426565</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td><a href="#">查看</a></td>
  </tr>
  <tr>
    <td><a href="#">115426565</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td><a href="#">查看</a></td>
  </tr>
  <tr>
    <td>115426565</td>
    <td>20110210</td>
    <td>528.00</td>
    <td>未发货</td>
    <td>查看</td>
  </tr>
</table>
  </li></ul> 
  <ul><li>
    <table class="tab_table" width="100%" border="0" cellspacing="0" cellpadding="0" id="ds">
  <tr>
    <th width="14%">订单号</th>
    <th width="25%">下单时间</th>
    <th width="23%">订单金额</th>
    <th width="19%">状态</th>
    <th width="19%">操作</th>
  </tr>
  <tr>
    <td><a href="#">44444444</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td>查看</td>
  </tr>
  <tr>
    <td><a href="#">115426565</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td><a href="#">查看</a></td>
  </tr>
  <tr>
    <td><a href="#">115426565</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td><a href="#">查看</a></td>
  </tr>
  <tr>
    <td><a href="#">115426565</a></td>
    <td><a href="#">20110210</a></td>
    <td>528.00</td>
    <td>未发货</td>
    <td><a href="#">查看</a></td>
  </tr>
  <tr>
    <td>115426565</td>
    <td>20110210</td>
    <td>528.00</td>
    <td>未发货</td>
    <td>查看</td>
  </tr>
</table>
  </li></ul> 
 </div> 
</div> 

<!--选项卡结束-->
<div class="blank12"></div>
<!--常用提示-->
<div class="error"><img src="${approot}/resource/images/error.gif" width="17" height="15" /> 警告！</div>
<div class="blank6"></div>
<div class="ok"><img src="${approot}/resource/images/ok.gif" width="14" height="15" /> 操作成功！</div>
<div class="blank6"></div>
<div class="alert"><img src="${approot}/resource/images/alert.gif" width="14" height="15" /> 提示信息！</div>
<div class="blank6"></div>
<div class="wrong"><img src="${approot}/resource/images/wrong.gif" width="14" height="15" /> 错误！</div>
 <div class="blank6"></div>   
  <div class="button_0 left">提 交</div>  
  <div class="button_0 left">清 除</div>
</div>
<div class="blank12"></div>

<div class="add clear">
<table width="60%" border="0" cellpadding="0" cellspacing="0" class="add_table">
  <tr>
    <td width="21%"><span class="right">会员名</span></td>
    <td width="79%"><input type="text" name="textfield3" /> 
      <span class="red">*</span> </td>
    </tr>
  <tr>
    <td><span class="right">会员别名</span></td>
    <td><input type="text" name="textfield32" />
      <span class="red">*</span></td>
    </tr>
  <tr>
    <td><span class="right">登录密码</span></td>
    <td><input type="text" name="textfield33" />
      <span class="red">*</span></td>
    </tr>
  <tr>
    <td><span class="right">确认密码</span></td>
    <td><input type="text" name="textfield34" />
      <span class="red">*</span></td>
    </tr>
  <tr>
    <td><span class="right">邮箱</span></td>
    <td><input type="text" name="textfield35" /></td>
    </tr>
</table>
<div class="blank12"></div>
<table width="60%" border="0" cellpadding="0" cellspacing="0" class="add_table2">
  <tr>
    <td width="21%"><span class="right">会员名</span></td>
    <td width="79%"><input type="text" name="textfield36" />
        <span class="red">*</span> </td>
  </tr>
  <tr>
    <td><span class="right">会员别名</span></td>
    <td><input type="text" name="textfield322" />
        <span class="red">*</span></td>
  </tr>
  <tr>
    <td><span class="right">登录密码</span></td>
    <td><input type="text" name="textfield332" />
        <span class="red">*</span></td>
  </tr>
  <tr>
    <td><span class="right">确认密码</span></td>
    <td><input type="text" name="textfield342" />
        <span class="red">*</span></td>
  </tr>
  <tr>
    <td><span class="right">邮箱</span></td>
    <td><input type="text" name="textfield352" /></td>
  </tr>
</table>
<p>&nbsp;</p>
<div class="blank12"></div>
</div>

</body>
</html>
