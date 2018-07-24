<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>错误页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" CONTENT="-1">           
<meta http-equiv="Cache-Control" CONTENT="no-cache">           
<meta http-equiv="Pragma" CONTENT="no-cache">       
<link rel="stylesheet" href="/res/admin/uber/css/base.css">
<link rel="stylesheet" href="/res/admin/uber/css/css.css">
<style type="text/css">
.show-result p a{color: red; font-size: 16px;}
</style>
</head>

<body>

<section class="show-result">
	<img src="/res/admin/uber/images/icons/icon-error.png">
    <p>${msg} </p>
    <p><input type="button" style="width:100px; height:50px; background:#FFD700;color:#000000"  onclick="javascript:history.go(-1);tt(this.style.display='none')" value="返回"></p>
</section>
</body>
</html>
