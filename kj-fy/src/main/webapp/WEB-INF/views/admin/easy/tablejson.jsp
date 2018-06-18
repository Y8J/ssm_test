<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Insert title here</title>  
<link rel="stylesheet" type="text/css" href="/res/easyui/css/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="/res/easyui/css/themes/icon.css">   
<script type="text/javascript" src="/res/easyui/js/jquery.min.js"></script>   
<script type="text/javascript" src="/res/easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/res/easyui/js/easyui-lang-zh_CN.js"></script>
	
</head> 
<body>

<table id="datagridID" style="width:555px"></table>

<script type="text/javascript">
	$("#datagridID").datagrid({
		url : "/admin/easyui/userjson.do",
	    columns:[[    
	              {field:'id',title:'编号',width:100},    
	              {field:'userName',title:'用户姓名',width:100},    
	              {field:'userPwd',title:'用户密码',width:100},   
	              {field:'status',title:'用户状态',width:100},    
	              {field:'email',title:'用户邮箱',width:100},    
	              {field:'mobile',title:'用户手机号码',width:100}   
	          ]],
	    title: '用户信息查询结果',       //表格标题     
	    fitColumns:true,
	    pagination : true,
	    pageNumber : 1,
	    pageSize : 2,
	    pageList : [2,4],
	    singleSelect : true,
	    rownumbers:true
	});
</script>

</body>
</html>