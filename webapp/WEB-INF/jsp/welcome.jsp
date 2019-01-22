<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.test.domain.User"%>
<h1>欢迎页面</h1>
<%
	User u = (User)request.getAttribute("user");
/* 	String data = u.toString();
	response.getWriter().write(data);
	response.getWriter().write("<br/><img src='"+u.getHeaderImg()+"'/>"); */
%>
<table>
	<tr>
		<td>欢迎你：</td>
		<td><%=u.getUsername() %></td>
	</tr>
	<tr>
		<td>用户名：</td>
		<td><%=u.getLoginname() %></td>
	</tr>
	<tr> 
		<td>密码：</td>
		<td><%=u.getPassword() %></td>
	</tr>
	<tr>
		<td>头像：</td>
		<td><%=u.getHeaderImg() %></td>
	</tr>
	<tr>
		<td>头像：</td>
		<td><img src="<%=u.getHeaderImg() %>"/></td>
	</tr>
</table>
<img>
<script>
	var a = "<%=u.getUsername()%>"
	alert(a)
</script>