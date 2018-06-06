<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>皮鞋网</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" CONTENT="-1">           
<meta http-equiv="Cache-Control" CONTENT="no-cache">           
<meta http-equiv="Pragma" CONTENT="no-cache">       
<link rel="stylesheet" href="/res/admin/user/css/base.css">
<link rel="stylesheet" href="/res/admin/user/css/def.css">
<script src="/res/admin/user/js/jquery.js"></script>  

</head>

<body bgcolor="#fafafa">

<header class="header-box">
	<div class="wrap">
    	<a class="back" href="javascript:history.back(-1);"><i></i></a>
    	<h2>皮鞋网</h2>
    </div>
</header>

<section class="container-box">

<form method="post" action="/admin/user/login.do" id="formbegin">
	<div class="form-box">
    	<ul>
        	<li>
            	<label class="hd"><img src="/res/admin/user/images/icons/icon-tel.png"></label>
                <div class="bd">
                	<input type="text" name="userName" class="ipt" placeholder="请输入您的手机号码">
                </div>
            </li>
            <li>
            	<label class="hd"><img src="/res/admin/user/images/icons/icon-pwd.png"></label>
                <div class="bd">
                	<input type="text" name="password" class="ipt" placeholder="请输入您的密码">
                </div>
            </li>
        </ul>
    </div>
    
    <div class="btn-box">
    	<a class="a1" id="login">登录</a>   
    </div>    
</form>    
    <div class="login-tip clearfix">
    	<a class="fl" href="#">免费注册</a>
        <a class="fr" href="#">忘记密码?</a>
    </div>
    
    <div class="notice-box">温馨提示：有油网、中经油马APP使用中经汇通统一账户系统，一处注册多处登录，免除记忆多套账户密码的烦恼。</div>
    
</section>


<script type="text/javascript">
$(function(){
	$("#login").click(function(){
		$("#formbegin").submit();
	});
})
</script>
    
</body>
</html>
