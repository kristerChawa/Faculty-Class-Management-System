(function(){
	angular.module("facultyApp")
		.service("userService", userService);

	function userService($http, $timeout, $rootScope){
		var self = this;

		self.createSession = createSession;
		self.destroySession = destroySession;
		self.userInfo = {};

		function createSession(responseObj){
			
			self.userInfo.idNo = responseObj.idNo? responseObj.idNo : "";
			self.userInfo.username = responseObj.username? responseObj.username : "";
			self.userInfo.firstName = responseObj.firstName? responseObj.firstName : "";
			self.userInfo.lastName = responseObj.lastName? responseObj.lastName : "";

			var ac = responseObj.accountType.length == 0? "" : responseObj.accountType.length == 1? 
						[responseObj.accountType[0].accountType] : 
								[responseObj.accountType[0].accountType, responseObj.accountType[1].accountType];

			ac = ac.join(", ");
			console.log(ac);
			self.userInfo.userRole = responseObj.accountType[0].accountType;
			self.userInfo.userImage = responseObj.pictureUrl? responseObj.pictureUrl : ""; //resources/img/avatar.png
			
			console.log(self.userInfo);
		}
		
		function destroySession(){
			self.userInfo = {};
		}

	}
}());