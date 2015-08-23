<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class=" blank4"></div>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="page ">
	<tr>
		<td width="40%">
			共有
			<span class="red">${page.totalCount}</span>条记录，当前第
			<span class="red">${page.currentPage}</span>页，共
			<span class="red">${page.pageCount}</span>页
		</td>
		<td width="60%">
		 <div class="right">
		<div class="left">
				每页
				<input type="text" name="page_size" value="${page.pageSize}"
					size="5" id="page_size" />
				转到
				<select name="to_page" id="to_page"
					onchange="goto_select_page('${pageuri}','${tabID}');">
					<c:forEach var="ind" items="${page.pages}">
						<option value="${(ind-1)*page.pageSize}"
							<c:if test="${ind==page.currentPage}">selected="selected"</c:if>>
							${ind}
						</option>
					</c:forEach>
				</select>
				页
			</div>
		    <span class="button left"><a href="#"
				onclick="goto_forward_page('${pageuri}','${page.previousIndex}','${tabID}');">上一页</a>
			</span>
			<span class="button left"><a href="#"
				onclick="goto_forward_page('${pageuri}','${page.firstIndex}','${tabID}');">首页</a>
			</span>
			
				<c:forEach items="${page.showpages}" var="ind">
				<c:if test="${ind!=0}">
					<c:choose>
						<c:when test="${ind==page.currentPage}">
						<span class="left number">${ind}</span>
						</c:when>
						<c:otherwise>
						<span class="left number_on"><a href="#"
						onclick="goto_forward_page('${pageuri}','${(ind-1)*page.pageSize}','${tabID}');">${ind}</a>
					</span>
						</c:otherwise>
					</c:choose>
					
				</c:if>
			</c:forEach>
			
			<span class="button left"><a href="#"
				onclick="goto_forward_page('${pageuri}','${page.lastIndex}','${tabID}');">末页</a>
			</span>
			<span class="button left"
				onclick="goto_forward_page('${pageuri}','${page.nextIndex}','${tabID}');"><a
				href="#">下一页</a> </span>

		</div>	
		</td>
	</tr>
</table>
<div class=" blank12"></div>