(function($){
	$.fn.jtimeline = function(options) {
		
		var settings = {
				name : "data",
				url : "",
				dataType : "json",
				styles : "default",
				success : function(data){styles[String(settings.styles)](data);},
				error : function(){},
				nest : 1,
				maxline : 100,
				updatetime : 900,
				updatelimit : 50
		}
		
		var styles = {
				"default" : function(data){
					console.log(data);
				} 
		}
		
		if (options) {
			$.extend(settings, options);
		}
		
		return this.each(function(options) {
			$.ajax(settings.url,{
				dataType : settings.dataType,
				data : {current : 0 , limit : settings.maxline},
				success : settings.success,
				error : settings.error
			});
			var timer = setInterval(function(){
				$.ajax(settings.url,{
					dataType : settings.dataType,
					success : settings.success,
					error : settings.error
				});
			},parseInt(settings.updatetime)*1000);
			
		});
	}
})(jQuery);