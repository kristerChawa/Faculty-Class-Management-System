(function(){
	angular.module("facultyApp")
		.service("cookieService", cookieService);

	function cookieService($cookies){
		var self = this;
		self.hasCookie = false;
		self.getCookie = getCookie;
		self.setCookie = setCookie;

		function setCookie(key, value){
			$cookies.put(key, value, {
				path: "/"
			});
		}

		function getCookie(key){
			$cookies.get(key);
		}

		function destroyCookie(key){
			$cookies.remove(key);
		}


	}
})();