<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
   	<form  action="/webapp/image/upload.do"  method="post"  enctype="multipart/form-data">
		上传人：<input type="text" name="username" value="哈哈"/><br/>
		上传文件：<input type="file" name="uploadFile"/>
		<input type="submit" value="上传单个文件"/>
	</form>
</body>
</html>