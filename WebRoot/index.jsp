<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
// String a="";
// out.print(a.charAt(2));
%>


${pageContext.session.id}
<%=session.getId() %>
<h1 align="center">首页</h1>
<a href="user?m=logout">销毁</a>
欢迎${sessionScope.user.username}
<a href="addDept.jsp">添加部门</a>
<a href="deptAction?m=listDept">管理部门</a>
<a href="deptAction?m=listDeptByPager">分页管理部门</a>
</body>
</html>