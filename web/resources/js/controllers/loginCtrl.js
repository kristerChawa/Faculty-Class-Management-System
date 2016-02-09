(function(){
	angular.module("facultyApp")
		.controller("loginCtrl", loginCtrl);

	function loginCtrl(authService, $state, $timeout){
		var self = this;
		self.hasError = false;
		self.credentials = {};
		self.login = login;
		
		function login(credentials){
			if(credentials.username != '' || credentials.password != ''){
				authService.login(credentials)
				.then(function(response){
					console.log(response);
					if(response.status == 200){
						$state.go("dashboard");
					}else{
						self.hasError = true;
						$timeout(function(){
							self.hasError = false;
						}, 1200);
					}
				});
			}
		}
	}
}());