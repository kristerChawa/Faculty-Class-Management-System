(function(){
	angular.module("facultyApp")
		.controller("forgotpwdCtrl", forgotpwdCtrl);

	function forgotpwdCtrl(forgotpwdService, $mdToast){
		var self = this;
		self.changePassword = changePassword;

		function changePassword(username){
			forgotpwdService.changePassword(username).then(function(response){
				if(response.status == 200){
					displayToast("Email has been sent to your account.");
				}else{
					displayToast("An error has occured. Please try again.");
				}
				self.username = "";
			});
		}

		function displayToast(message){
			$mdToast.show(
				$mdToast.simple()
					.textContent(message)
					.position("top")
					.hideDelay(2000)
			);
		}
	}
})();