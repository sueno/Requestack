(function($) {

	$.fn.requestack = function(options) {

		var settings = {
			"name" : "uploadform",
			"url" : "",
			"success" : null
		}

		if (options) {
			$.extend(settings, options);
		}
		console.log("hoge");

		return this.each(function(options) {
			$(this).on("submit", function() {
				try {
					//create form
					var param = $(this).serializeArray();
					var formvalue = {};
					for ( var i in param) {
						formvalue[param[i]["name"]] = param[i]["value"];
					}
					var formJ = JSON.stringify(formvalue);
					console.log(settings);
					console.log(formJ);
				} catch (e) {
					console.log(e);
				}
				return false;
			});
		});
	}
})(jQuery);