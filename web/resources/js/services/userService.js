(function(){
	angular.module("facultyApp")
		.service("userService", userService);

	function userService(){
		var self = this;

		self.createSession = createSession;
		self.destroySession = destroySession;
		self.userInfo = {};

		function createSession(responseObj){
			
			self.userInfo.username = responseObj.username;
			self.userInfo.firstName = responseObj.firstName;
			self.userInfo.lastName = responseObj.lastName;

			var ac = [responseObj.accountType[0].accountType, responseObj.accountType[1].accountType];
			ac = ac.join(", ");
			console.log(ac);
			self.userInfo.userRole = responseObj.accountType[0].accountType;
			self.userInfo.userImage = "resources/img/avatar.png"; //responseObj.userImage;
			
			console.log(self.userInfo);
		}

		function destroySession(){
			self.userInfo = {};
		}

	}
}());