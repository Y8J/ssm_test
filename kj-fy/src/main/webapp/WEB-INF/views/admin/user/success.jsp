<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>${title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Expires" CONTENT="-1">           
<meta http-equiv="Cache-Control" CONTENT="no-cache">           
<meta http-equiv="Pragma" CONTENT="no-cache">       
<style type="text/css">
/*支付结果*/
.pay-result{ padding: 30px 20px; text-align: center;}
.pay-result a{text-decoration:none;  font-size: 16px; color: #b3040e;}
.pay-result a.home{ background: #c4c4c4; border: 1px solid #bababa; color: #434343; margin-top: 15px;}

.liebiao{display: block;
    width: 45%;
    margin: .2rem auto 0;
    height: 45px;
    line-height: 45px;
    background: -webkit-linear-gradient(to bottom, #ffd600 , #ffa900);
    background: linear-gradient(to bottom, #ffd600 , #ffa900);
    border-radius: .35rem;
    font-size: 20px;
    color: #a13e00;
    text-align: center;
    font-weight: bold;
    box-shadow: 0 0.08rem 0 #f27a00;}
    
.fanhui{display: block; 
        width:45%; 
        height:45px; 
        line-height: 45px; 
        margin: 0 auto; 
        background: -webkit-linear-gradient(to bottom, #ffd600 , #ffa900);
        background: linear-gradient(to bottom, #ffd600 , #ffa900);
        color:#a13e00; font-size: 20px; border: 0; font-weight: bold;  border-radius: .35rem;}
</style>

</head>

<body bgcolor="#fafafa">

<section class="pay-result" style="margin-top: 70px;">
	<img src="/res/mobile/ver2014/uber/images/icons/icon-right.png">
    <h2>${msg}</h2>
    
    <p><input type="button" class="fanhui"  onclick="javascript:history.go(-1);tt(this.style.display='none')" value="返回"></p>
</section>
   
</body>
</html>
