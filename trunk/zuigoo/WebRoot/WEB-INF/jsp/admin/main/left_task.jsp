<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<li class="menuoff" id="m1"><p><span><a onclick="menuShow('m1')">任务管理</a></span></p>
			<ul>
			<li><a target="main" href="${approot}/task/admin/task/list/menu/0/0" hidefocus="true"><b class="icos"></b>任务管理</a></li>
			</ul>
		</li>
		<li class="menuoff" id="m2"><p><span><a onclick="menuShow('m2')">会员管理</a></span></p>
			<ul>
			<li><a target="main" href="${approot}/member/admin/user/list/menu/0/0" hidefocus="true"><b class="icos"></b>会员管理</a></li>
			<li><a target="main" href="${approot}/task/admin/userno/list/sale/-1/menu/0/0" hidefocus="true"><b class="icos"></b>掌柜账号</a></li>
			<li><a target="main" href="${approot}/task/admin/userno/list/buy/-1/menu/0/0" hidefocus="true"><b class="icos"></b>买家小号</a></li>
			<li><a target="main" href="${approot}/member/admin/accountlog/toTabs/-1/0" hidefocus="true"><b class="icos"></b>充值提现</a></li>
			<li><a target="main" href="${approot}/member/admin/pointslog/list/-1/menu/0/0" hidefocus="true"><b class="icos"></b>点数明细</a></li>
			<li><a target="main" href="${approot}/member/admin/message/list/menu/0/0" hidefocus="true"><b class="icos"></b>短消息</a></li>
			</ul>
		</li>

		<li class="menuoff" id="m3"><p><span><a onclick="menuShow('m3')">内容管理</a></span></p>
			<ul>
								<li><a target="main" href="${approot}/cms/admin/articlecategory/frame/articlecategory" hidefocus="true"><b class="icos"></b>栏目管理</a></li>
								<li><a target="main" href="${approot}/cms/admin/articlecategory/frame/article" hidefocus="true"><b class="icos"></b>文章管理</a></li>
			</ul>
        </li>
        <li class="menuoff" id="m4"><p><span><a onclick="menuShow('m4')">优化推广</a></span></p>
			<ul>
								<li><a target="main" href="${approot}/cms/admin/link/list/menu/0/0" hidefocus="true"><b class="icos"></b>友情链接</a></li>
							</ul>
		</li>
		<li class="menuoff" id="m5"><p><span><a onclick="menuShow('m5')">系统用户</a></span></p>
			<ul>
								<li><a target="main" href="${approot}/core/admin/admindept/frame/dept" hidefocus="true"><b class="icos"></b>部门管理</a></li>
								<li><a target="main" href="${approot}/core/admin/admindept/frame/user" hidefocus="true"><b class="icos"></b>用户管理</a></li>
								<li><a target="main" href="${approot}/core/admin/admindept/frame/role" hidefocus="true"><b class="icos"></b>角色管理</a></li>
								<li><a target="main" href="${approot}/core/admin/adminlog/list/1/menu/0/0" hidefocus="true"><b class="icos"></b>用户日志</a></li>
								<li><a target="main" href="${approot}/core/admin/adminlog/list/2/menu/0/0" hidefocus="true"><b class="icos"></b>会员日志</a></li>

							</ul>
		</li>

		<li class="menuoff" id="m6"><p><span><a onclick="menuShow('m6')">系统管理</a></span></p>
			<ul>
				<li><a target="main" href="${approot}/task/admin/main/toSet/0" hidefocus="true"><b class="icos"></b>系统设置</a></li>
				<li><a target="main" href="${approot}/core/admin/adminuser/toPersonal" hidefocus="true"><b class="icos"></b>账户修改</a></li>
		</ul>
		</li>
		
        	<li class="menuoff" id="m7"><p><span><a onclick="menuShow('m7')">回收站</a></span></p>
			<ul>
			<li><a target="main" href="${approot}/member/admin/user/list/menu/0/0" hidefocus="true"><b class="icos"></b>用户回收站</a></li>
			<li><a target="main" href="${approot}/task/admin/userno/list/1/-1/menu/0/0" hidefocus="true"><b class="icos"></b>会员回收站</a></li>
			</ul>
		</li>

