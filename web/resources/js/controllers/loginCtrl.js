(function(){
	angular.module("facultyApp")
		.controller("loginCtrl", loginCtrl);

	function loginCtrl(authService, $state, $timeout, cookieService){
		var self = this;
		self.hasError = false;
		self.disableButton = false;
		self.credentials = {};
		self.login = login;
		
		function login(credentials){
			if(credentials.username != '' || credentials.password != ''){
			    self.disableButton = true;
			    // $state.go("dashboard");
				authService.login(credentials).then(function(response){
					console.log(response);
					if(response.status == 200){
						$state.go("dashboard");
					}else{
						self.hasError = true;
						$timeout(function(){
							self.hasError = false;
							self.disableButton = false;
						}, 1200);
					}
				});
			}
		}
	}
}());