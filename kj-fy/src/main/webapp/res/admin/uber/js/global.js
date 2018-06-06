
$.global= {
	
	toggle : function(){
		
		$('.meal-details dt').click(function(){
				
				var nextEle = $(this).next('dd');
				
				if(nextEle.is(":hidden")){
					$(this).addClass('cur');
					nextEle.slideDown();	
				}else{
					$(this).removeClass('cur');
					nextEle.slideUp();	
				}	
			}).eq(0).trigger('click');	
	}
	
}

function addNum(name,value,callback){
	$(name).click(function(){
		var val = $(value).val();
		$(value).val(parseInt(val) + 1);	
		$(value).keydown(function(e){
			if(e.keyCode!=8&&(e.keyCode<48||e.keyCode>57)&&(e.keyCode<96||e.keyCode>105)){
				return false;
			}
		});
		if($.isFunction(callback)){
			callback(name,value);
		}
	});
};
		
function lessNum(name,value,callback){
	$(name).click(function(){
		var val = $(value).val();
		val >= 1 ? $(value).val(parseInt(val) - 1) : $(value).val(0);
		$(value).keydown(function(e){
			if(e.keyCode!=8&&(e.keyCode<48||e.keyCode>57)&&(e.keyCode<96||e.keyCode>105)){
				return false;
			}
		});
		if($.isFunction(callback)){
			callback(name,value);
		}
	})
};

