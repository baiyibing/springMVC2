<%@ page pageEncoding="UTF-8"%>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script src="js/picdiet.js"></script>
<h1>文件上传</h1>
<form id="uploadForm" enctype="multipart/form-data">
	<table>
		<tr>
			<td>文件上传不压缩：</td>
			<td><input id="testFile" name="testFile" type="file" onchange="console.dir(1)"/></td>
		</tr>
	</table>
</form>
<form id="uploadForm2" enctype="multipart/form-data">
	<table>
		<tr>
			<td>文件上传压缩：</td>
			<td><input id="testFile2" name="testFile2" type="file" onchange="console.dir(1)"/></td>
		</tr>
	</table>
</form>
<script>
function getRootPath(){
	//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath=window.document.location.href;
	//获取主机地址之后的目录，如： /uimcardprj/share/meun.jsp
	var pathName=window.document.location.pathname;
	var pos=curWwwPath.indexOf(pathName);
	//获取主机地址，如： http://localhost:8083
	var localhostPaht=curWwwPath.substring(0,pos);
	//获取带"/"的项目名，如：/uimcardprj
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	return(localhostPaht+projectName);
}
function test(){
	$.ajax({
		type: 'get',
		url: 'http://127.0.0.1:8081/springMVC2/file/hello',
		success: function(res){
			console.dir(res)
		}
	})		
}
function upload(){
	$.ajax({
		type: 'post',
		url: 'http://127.0.0.1:8081/springMVC2/file/upload',
        data: new FormData($("#uploadForm")[0]),//表单数据
        processData: false,
        contentType: false,
		success: function(res){
			console.dir(res)
		}
	})		
}
function upload2(){
	console.dir($('#testFile2')[0].files[0])
	console.dir(new FormData($("#uploadForm2")[0]))
	console.dir(compress)
	console.dir(compress(new FormData($('#testFile2')[0].files[0]), 'png', 20))
	$.ajax({
		type: 'post',
		url: 'http://127.0.0.1:8081/springMVC2/file/upload2',
        data: compress(new FormData($("#uploadForm2")[0]), 'png', 20),//表单数据
        processData: false,
        contentType: false,
		success: function(res){
			console.dir(res)
		}
	})		
}
$(document).ready(function(){
	$('#testFile').change(function(){
		upload()
	})
	$('#testFile2').change(function(){
		upload2()
	})
})
</script>