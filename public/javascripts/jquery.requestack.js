(function($) {

	$.fn.requestack = function(options) {

		var settings = {
			name : "data",
			url : "",
			success : function(){},
			error : function(){},
			dataType : "text",
			ajax : true
		}

		if (options) {
			$.extend(settings, options);
		}

		return this.each(function(options) {
			$(this).on("submit", function() {
				try {
					//create form
					var param = $(this).serializeArray();
					var formvalue = {};
					for ( var i in param) {
						formvalue[param[i]["name"]] = param[i]["value"];
					}
					var formJ = {};
					formJ[settings.name] = JSON.stringify(formvalue);
					$.post(settings.url,formJ,settings.success,settings.dataType);
				} catch (e) {
					console.log(e);
				}
				return settings.ajax ? false:true;
			});
		});
		
	}
})(jQuery);