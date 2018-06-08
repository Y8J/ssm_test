<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
	<link href="/res/admin/tree/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" />
	<script src="/res/admin/tree/jquery-ztree/3.5.12/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="/res/admin/tree/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
	<script src="/res/admin/tree/jquery-ztree/3.5.12/js/jquery.ztree.excheck-3.5.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var tree,setting = {  
	check:{enable:true},   
	data:{simpleData:{enable:true}},
	view:{
	},
	callback: {
		beforeClick: function(treeId, treeNode) {
		}, 
		onDblClick:function(){
			parent.$.jBox.getBox().find("button[value='ok']").trigger("click");
		}
	}
};

$(document).ready(function(){
	$.get("/admin/sysmenu/v_treemenu.do?extId=1&t="+new Date().getTime(), function(treeNodes){
		tree=$.fn.zTree.init($("#tree"), setting, treeNodes.treeData);
		//打开全部节点
		//tree.expandAll(true);
		
		// 默认展开一级节点   i遍历表示要展开到第几个节点
		var i=1;
		var nodes = tree.getNodesByParam("level", i);
		for(var i=0; i<nodes.length; i++) {
			tree.expandNode(nodes[i], true, false, false);
		}
		
		// 默认选择节点
		var ids = "${selectIds}".split(",");
		for(var i=0; i<ids.length; i++) {
			var node = tree.getNodeByParam("id", ids[i]);
			//tree.checkNode(node, true, true);
			try{tree.checkNode(node, true, false);}catch(e){}
		}
		
		
	});
	$("#jvForm").click(function(){
		var ids = [], names = [], nodes = [];
		nodes =tree.getCheckedNodes();
		if(nodes.length==0){
			$.jBox.alert("请先选择菜单","操作提示");
			return false;
		}
		for(var i=0; i<nodes.length; i++) {
			ids.push(nodes[i].id);
			names.push(nodes[i].name);
		}
		$("#mid").val(ids);
		alert(ids);
	});
});
</script>
</head>
<body>
<div class="body-box">
<div class="rhead">
<div class="box-positon">
	
	<div class="clear"></div>
</div>
</div>

		<tr>
			<td class="pn-flabel pn-flabel-h">权限菜单</td>
			<td class="pn-fcontent">
			<div id="tree" class="ztree" style="width:260px; overflow:auto;"></div>
			<input type="hidden" name="mid" id="mid" value="" class="{required:true}" />
			</td>
		</tr>
		
		<input type="button"  name="jvForm" id="jvForm" value="点击查看选择的菜单" />

</div>
</body>
</html>