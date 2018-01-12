<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">分页部门管理</h1>
<%-- 当前页：${requestScope.pager.currentPage}<hr/>
下一页：${requestScope.pager.nextPage}<hr/>
上一页：${requestScope.pager.previousPage}<hr/>
最后一页：${requestScope.pager.totalPage}<hr/>
总条数：${requestScope.pager.totalCount}<hr/>
每页显示条数：${requestScope.pager.pageSize}<hr/>
起始页码：${requestScope.pager.startPage}<hr/>
结束页码：${requestScope.pager.endPage}<hr/>
当前页数据：${requestScope.pager.datas}<hr/> --%>

<table border="1" cellpadding="0" cellspacing="0" align="center" width="80%">
<tr>
<td>部门编号</td>
<td>部门名称</td>
<td>部门所在地</td>
<td>删除</td>
<td>删除</td>
<td>修改</td>
</tr>
<c:forEach var="dept" items="${requestScope.pager.datas}">
<tr>
<td>${dept.deptno}</td>
<td>${dept.dname}</td>
<td>${dept.loc}</td>
<td><a href="deptAction?m=deleteDept&deptno=${dept.deptno}">删除</a></td>
<td><input type="button" value="删除" onclick="del(${dept.deptno})" /></td>
<td><a href="deptAction?m=findDeptById&deptno=${dept.deptno}">修改</a></td>
</tr>
</c:forEach>
<tr>
<td colspan="6" align="right">
<a href="deptAction?m=listDeptByPager&currentPage=1">第一页</a>
<a href="deptAction?m=listDeptByPager&currentPage=${requestScope.pager.previousPage}">上一页</a>
<c:forEach var="i" begin="${requestScope.pager.startPage}" end="${requestScope.pager.endPage}">
<c:if test="${requestScope.pager.currentPage eq i}">
<a href="deptAction?m=listDeptByPager&currentPage=${i}"><font color='red'>${i}</font></a>
</c:if>
<c:if test="${requestScope.pager.currentPage ne i}">
<a href="deptAction?m=listDeptByPager&currentPage=${i}">${i}</a>
</c:if>
</c:forEach>
<a href="deptAction?m=listDeptByPager&currentPage=${requestScope.pager.nextPage}">下一页</a>
<a href="deptAction?m=listDeptByPager&currentPage=${requestScope.pager.totalPage}">最后一页</a>


<font color='red'>${requestScope.pager.currentPage}</font>/${requestScope.pager.totalPage}页
</td>
</tr>
</table>




<script type="text/javascript">
function del(deptno){
	var a=window.confirm("是否要删除？");
	if(a){
		location.href="deptAction?m=deleteDept&deptno="+deptno;	
	}
}
</script>
</body>
</html>