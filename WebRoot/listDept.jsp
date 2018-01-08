<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
</head>
<body>
<h1 align="center">部门管理</h1>
<table border="1" cellpadding="0" cellspacing="0" align="center" width="80%">
<tr>
<td>部门编号</td>
<td>部门名称</td>
<td>部门所在地</td>
<td>删除</td>
<td>删除</td>
<td>修改</td>
</tr>
<!-- items=""要遍历的集合 -->
<c:forEach var="dept" items="${requestScope.depts}">
<tr>
<td>${dept.deptno}</td>
<td>${dept.dname}</td>
<td>${dept.loc}</td>
<td><a href="deptAction?m=deleteDept&deptno=${dept.deptno}">删除</a></td>
<td><input type="button" value="删除" onclick="del(${dept.deptno})" /></td>
<td><a href="deptAction?m=findDeptById&deptno=${dept.deptno}">修改</a></td>
</tr>
</c:forEach>
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