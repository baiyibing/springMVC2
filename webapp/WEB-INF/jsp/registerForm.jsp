<%@ page pageEncoding="UTF-8"%>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<h1>用户注册</h1>
<form action="http://127.0.0.1:8081/springMVC2/user/register" method="POST" enctype="multipart/form-data">
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
		<td>真是姓名：</td>
		<td><input name="username"/></td>
	</tr>
	<tr>
		<td>头像：</td>
		<td><input name="headerImg" type="file"/></td>
	</tr>
	<tr>
		<td>头像2：</td>
		<td onclick="chooseFile()">
			<input id="fileSelect" name="headerImg" type="file" accept="image/*" style="display:none"/>
			<div id="fileName"></div>
		</td>
	</tr>
	<tr>
		<td><a onclick="window.open('http://127.0.0.1/1.jpg')">查看图片</a></td>
		<td><input type="submit"></td>
	</tr>
</table>
</form>
<script>
function chooseFile(){
	var f = document.getElementById("fileSelect");
	f.click();
}
$(document).ready(function(){
	$('#fileSelect').change(function(){
		$('#fileName').html($(this).val())
	})
});
</script>