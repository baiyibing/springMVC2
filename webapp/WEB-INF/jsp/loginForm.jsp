<%@ page pageEncoding="UTF-8"%>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<h1>用户登录</h1>
<form action="http://127.0.0.1:8081/springMVC2/user/login" method="post">
<table>
	<tr>
		<td>用户名：</td>
		<td><input name="loginname"/></td>
	</tr>
	<tr>
		<td>密码：</td>
		<td><input name="password"/></td>
	</tr>
	<tr>
		<td><input type="submit"/></td>
		<td></td>
	</tr>
</table>
</form>
<script>
function login(){
	$.ajax({
		type: 'post',
		url: 'http://127.0.0.1:8080/springMVC2/user/login',
		data: {
			loginname: $('#username').val(),
			password: $('#password').val()
		}/* ,
		success: function(res){
			console.dir(res)
		} */
	})		
}
</script>