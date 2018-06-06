
//��չjq.fn
$.extend($.fn, {
	
	//ѡ� ***
	tab : function(opts){
		var def = {
			tabBd : "",
			  cls :"cur",
		   _event :"click"
		};
		var setting = $.extend(def,opts);	
		$(this).bind(setting._event ,fn).eq(0).trigger(setting._event);
		function fn(){
			var index = $(this).index();
			$(this).addClass(setting.cls).siblings().removeClass(setting.cls);
			$(setting.tabBd).eq(index).show().siblings().hide();			
		};
	},
	
	//�л� ***
	tabcur : function(opts){
		var def = {
			  cls :"cur",
		   _event :"click"
		};
		var setting = $.extend(def,opts);	
		$(this).bind(setting._event ,fn).eq(0).trigger(setting._event);
		function fn(){
			$(this).addClass(setting.cls).siblings().removeClass(setting.cls);		
		};
	}
});

$.common={
	
	//�����˵� ***
	fixedNav : function(){
		
		var oMenu = $('.fixed-menu');
	
		$('.header-box .nav').click(function(e){
			
			if(oMenu.is(":hidden")){
				 oMenu.show();
			}else{
				 oMenu.hide();
			}
			
			e.stopPropagation();
	
		});
		
		$("html,body").click(function(){
			oMenu.hide();
		});	
	}
}


$(function(){
	
	$.common.fixedNav();
	
})