var _model="rotate";//抽奖类型
var js = document.getElementsByTagName("script");  
for (var i = 0; i < js.length; i++) {  
    if (js[i].src.indexOf("lottery_apply_edit.js") >= 0) {  
        var arraytemp = new Array();  
        arraytemp = js[i].src.split('?');  
        arraytemp = arraytemp[1].split('&');
        for ( var index = 0; index < arraytemp.length; index++) {
			var kv=arraytemp[index];
			var p=kv.split('=');
			var _key=p[0];
			if(_key=="model"){
				_model=p[1];
			}
		}
    }  
}
var loading = '<div style="text-align:center"><img src="/res/front/ver2013/images/com/loading.gif" /></div>';
//获取editor内容
function getEditorHTMLContents(editorName) {
    return (FCKeditorAPI.GetInstance(editorName).GetXHTML(true));
}
//在内容最前面加html内容
function insertEditorHtmlFront(editorName,html){
	FCKeditorAPI.GetInstance(editorName).InsertHtml(html);
}
//获取焦点
function focusEditor(editorName){
	FCKeditorAPI.GetInstance(editorName).Focus();
}
//设置内容,所见即所得 
function setEditorHtml(editorName,html){
	FCKeditorAPI.GetInstance(editorName).SetHTML(html,true);
}
//隐藏由于iframe加载的modal加载层
function hideFrameModal(){
	var previewFrame=document.getElementById("previewFrame");
	if (previewFrame.attachEvent) {
		previewFrame.attachEvent("onload", function () {
			$.modal.close();
		});
	} else {
		previewFrame.onload = function () {
			$.modal.close();
		};
	}
}
function reloadPreview(){
	$(loading).modal();
	var params="?model="+$('input[name="model"]:checked').val()+"&pageStyle="+$('input[name="pageStyle"]:checked').val()+"&topBg="+$("#uploadImgPath1").val()+"&frameBg="+$("#uploadImgPath2").val()+"&guideBg="+$("#uploadImgPath3").val()+"&dialBg="+$("#uploadImgPath4").val()+"&r="+Math.random();
	$("#previewFrame")[0].src="v_preView.do"+params;
	hideFrameModal();
}
function openPreview(){
	if(!$("#jvForm").valid()){
		formValidFocus();
		return;
	}
	if(getEditorHTMLContents("rulesContent")==""){
		alert("活动介绍不能为空！");
		focusEditor("rulesContent");
		return;
	}
	$("#jvForm").attr("action","v_preView.do");
	$("#jvForm").attr("target","_blank");
	$("#jvForm").submit();
}
function anchorPreview(anchor){
	$(loading).modal();
	var params="?model="+$('input[name="model"]:checked').val()+"&pageStyle="+$('input[name="pageStyle"]:checked').val()+"&topBg="+$("#uploadImgPath1").val()+"&frameBg="+$("#uploadImgPath2").val()+"&guideBg="+$("#uploadImgPath3").val()+"&dialBg="+$("#uploadImgPath4").val()+"&anchor="+Math.random()+anchor;
	$("#previewFrame")[0].src="v_preView.do"+params;
	hideFrameModal();
}
function formValidFocus(){
	var iptError=$("input.error");
	if (iptError.length>0) {
		iptError[0].focus();
	}
}
function hoverDel(ob){
	$(ob).closest("tr").addClass("tr_hover");
}
function hoverOutDel(ob){
	$(ob).closest("tr").removeClass("tr_hover");
}
function clickDel(ob){
	$(ob).closest("tr").remove();
}
$(function() {
	$.validator.addMethod("decimal", function(value, element, param) {
		var reg = /^-?[0-9]+(\.[0-9]{1,2})?$/;
		return reg.test(value);
	}, "最多只能保留2小数");
	$.validator.addMethod("beint", function(value, element, param) {
		var reg = /^[0-9]+$/;
		if ($.trim(value) == '') {
			return true;
		}
		return reg.test(value);
	}, "必须是正整数");
	var validator =$("#jvForm").validate();
	
	var params="&topBg="+$("#uploadImgPath1").val()+"&frameBg="+$("#uploadImgPath2").val()+"&guideBg="+$("#uploadImgPath3").val()+"&dialBg="+$("#uploadImgPath4").val();
	$("#previewFrame")[0].src="v_preView.do?model="+$('input[name="model"]:checked').val()+"&pageStyle="+$('input[name="pageStyle"]:checked').val()+params;
	if ($.browser.msie) {
		$('input[type="radio"]').click(function (e) { this.blur(); this.focus(); });
	}
	$('input[name="model"]').change(function(){
		params="?model="+$('input[name="model"]:checked').val()+"&pageStyle="+$('input[name="pageStyle"]:checked').val()+"&topBg="+$("#uploadImgPath1").val()+"&frameBg="+$("#uploadImgPath2").val()+"&guideBg="+$("#uploadImgPath3").val()+"&dialBg="+$("#uploadImgPath4").val();
		$(loading).modal();
		$("#previewFrame")[0].src="v_preView.do"+params;
		hideFrameModal();
	});
	$('input[name="pageStyle"]').change(function(){
		params="?model="+$('input[name="model"]:checked').val()+"&pageStyle="+$('input[name="pageStyle"]:checked').val()+"&topBg="+$("#uploadImgPath1").val()+"&frameBg="+$("#uploadImgPath2").val()+"&guideBg="+$("#uploadImgPath3").val()+"&dialBg="+$("#uploadImgPath4").val();
		$("#previewFrame")[0].src="v_preView.do"+params;
		//reloadPreview();
	});
	$('input[name="lotteryServiceRD"]').change(function(){
		$('input[name="lotteryServiceRD"]:checked').val()=="2"?$("#div_lottery_service").show():$("#div_lottery_service").hide();
	});
	$(".insertRulesTitleCss").click(function(e){
		var rtt=$("#ruleTitleTag").val();
		if($.trim(rtt)==""){
			alert("请先输入活动介绍题头文字！");
			$("#ruleTitleTag").focus();
			return;
		}
		var rulesHtml=getEditorHTMLContents("rulesContent")+"<h3 class='"+$(this).attr("data-class")+"'>"+rtt+"</h3>";
		setEditorHtml("rulesContent",rulesHtml);
		anchorPreview("#arules");
	});
	$("#jvForm").validate();
	$("#a_new_item_line").click(function(e){
		var tbody=$(this).closest("tfoot").prev();
		var _del_a='<a href="javascript:;">删除</a>';
		var rdoVal=tbody.find("tr:first").find('input[type="radio"]:checked').val();
		tbody.find("tr").first().clone().appendTo(tbody);
		tbody.find("tr").last().find("td").last().html(_del_a);
		tbody.find("tr:last td a").unbind("hover").unbind("click");
		tbody.find("tr:last td a").hover(
				function () {
					$(this).closest("tr").addClass("tr_hover");
				},
				function () {
					$(this).closest("tr").removeClass("tr_hover");
				}
		).click(function(e){
			$(this).closest("tr").remove();
		});
		var _curIndex=parseInt($("#curIndex").val());
		_curIndex++;
		$("#curIndex").val(_curIndex);
		tbody.find("tr:last").find('input[type="text"]').each(function(index,val){
			var _input=$(this);
			var _lable=_input.attr("name")+_curIndex;
			_input.attr({"id":_lable,"name":_lable,"value":""});
			_input.removeClass("error");
			_input.next().remove();
		});
		var rdoVal=tbody.find("tr:last").find('input[type="radio"]:checked').val();
		if(rdoVal=="1"){
			tbody.find('tr:first').find('input[type="radio"]').trigger("click");
		}
		$("#jvForm").validate();
	});
	$("input[name='dateModel']").click(function(e){
		var chVal=$("input[name='dateModel']:checked").val();
		if(chVal==1){
			$(".date_range_item:eq(0)").siblings().hide();
			$("#div_date_more").hide();
		}else{
			$(".date_range_item").show();
			$("#div_date_more").show();
		}
	});
	$("#div_date_range").on("click",".date_range_item div span input",function(){
		WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});
	});
	$("#div_date_more").click(function(){
		var _date_range_item=$("#div_date_range .date_range_item").eq(0).clone();
		_date_range_item.append('<div><a href="javascript:;">删除此项</a></div>');
		_date_range_item.find("input").each(function(){
			$(this).val("");
		});
		$("#div_date_range").append(_date_range_item);
	});
	$("#div_date_range").on("click",".date_range_item div a",function(){
		$(this).closest(".date_range_item").remove();
	});
	$("#btnSave").click(function(e){
		if(!$("#jvForm").valid()){
			return;
		}
		var lotteryMaxCount=$.trim($("#lotteryMaxCount").val());
		if(parseInt(lotteryMaxCount)<0){
			alert("每人抽奖次数不能为负数！");
			return;
		}
		var trEls=$("#tblItem tbody tr");
		var itemSize=trEls.length;
		var inputItemSize='<input type="hidden" name="itemSize" value="'+itemSize+'" />'
		$("#hideTd").html('');
		$("#hideTd").append(inputItemSize);
		var _hideIpt=$('<input type="hidden" />');
		trEls.each(function(index,val){
			var curTr=$(this);
			
			var itemId="itemId"+index;
			var itemIdVal=curTr.find('input').eq(7).val();
			var _itemId=_hideIpt.clone();
			_itemId.attr({"name":itemId,"value":itemIdVal});
			$("#hideTd").append(_itemId);
			
			var prizeCount="prizeCount"+index;
			var prizeCountVal=1;
			var _prizeCount=_hideIpt.clone();
			_prizeCount.attr({"name":prizeCount,"value":prizeCountVal});
			$("#hideTd").append(_prizeCount);
			
			var prizeItemName="prizeItemName"+index;
			var prizeItemVal=curTr.find('input').eq(0).val();
			var _prizeItem=_hideIpt.clone();
			_prizeItem.attr({"name":prizeItemName,"value":prizeItemVal});
			$("#hideTd").append(_prizeItem);
			
			var prizeName="prizeName"+index;
			var prizeNameVal=curTr.find('input').eq(1).val();
			var _prizeName=_hideIpt.clone();
			_prizeName.attr({"name":prizeName,"value":prizeNameVal});
			$("#hideTd").append(_prizeName);

			var prizeSumCount="prizeSumCount"+index;
			var prizeSumCountVal=curTr.find('input').eq(2).val();
			var _prizeSumCount=_hideIpt.clone();
			_prizeSumCount.attr({"name":prizeSumCount,"value":prizeSumCountVal});
			$("#hideTd").append(_prizeSumCount);

			var prizeChance="prizeChance"+index;
			var prizeChanceVal=curTr.find('input').eq(3).val();
			var _prizeChance=_hideIpt.clone();
			_prizeChance.attr({"name":prizeChance,"value":prizeChanceVal});
			$("#hideTd").append(_prizeChance);

			var prizeType="prizeType"+index;
			var prizeTypeVal=curTr.find('select').eq(0).val();
			var _prizeType=_hideIpt.clone();
			_prizeType.attr({"name":prizeType,"value":prizeTypeVal});
			$("#hideTd").append(_prizeType);

			var prizeCode="prizeCode"+index;
			var prizeCodeVal=curTr.find('input').eq(4).val();
			var _prizeCode=_hideIpt.clone();
			_prizeCode.attr({"name":prizeCode,"value":prizeCodeVal});
			$("#hideTd").append(_prizeCode);

			var prizeValue="prizeValue"+index;
			var prizeValueVal=curTr.find('input').eq(5).val();
			var _prizeValue=_hideIpt.clone();
			_prizeValue.attr({"name":prizeValue,"value":prizeValueVal});
			$("#hideTd").append(_prizeValue);

			var prizeAngle="prizeAngle"+index;
			var prizeAngleVal=curTr.find('input').eq(6).val();
			var _prizeAngle=_hideIpt.clone();
			_prizeAngle.attr({"name":prizeAngle,"value":prizeAngleVal});
			$("#hideTd").append(_prizeAngle);
			
			var prizeDefaultName="prizeDefault"+index;
			var prizeDefaultVal=curTr.find('input[type="radio"]:checked').val()==null?0:1;
			var _prizeDefaultName=_hideIpt.clone();
			_prizeDefaultName.attr({"name":prizeDefaultName,"value":prizeDefaultVal});
			$("#hideTd").append(_prizeDefaultName);
		});
		$(".date_range_item").each(function(index,value){
			var _curEl=$(this);
			if(_curEl.is(":hidden")){
				_curEl.remove();
			}
		});
		var _rules=getEditorHTMLContents("rulesContent");
		$("#rules").html(_rules);
		$(loading).modal();
		$.post("o_edit.do",$("#jvForm").serialize(),function(data){
			$.modal.close();
			alert(data.m);
			if(data.c==1){
				var url="v_edit.do?id="+parseInt($('input[name="id"]').val())+"&random="+Math.random();
				window.location.href=url;
			}
		}).error(function(xhr,status,statusText){
			$.modal.close();
			if(xhr.status=="403"){
				alert("您的账号暂无权限！")
			}else{
				alert(statusText);
			}
		});
	});
});