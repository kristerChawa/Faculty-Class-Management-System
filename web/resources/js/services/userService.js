(function(){
	angular.module("facultyApp")
		.service("userService", userService);

	function userService($http, $timeout, $q, cookieService){
		var self = this;

		self.createSession = createSession;
		self.destroySession = destroySession;
		self.getAccountType = getAccountType;
		self.userInfo = {};

		function createSession(response){

			var defer = $q.defer();
			var responseObj = response.data.usersModel;

			self.userInfo.username = responseObj.username? responseObj.username : "";
			self.userInfo.firstName = responseObj.firstName? responseObj.firstName : "";
			self.userInfo.lastName = responseObj.lastName? responseObj.lastName : "";

			var ac = responseObj.accountType.length == 0? "" : responseObj.accountType.length == 1? 
						[responseObj.accountType[0].accountType] : 
								[responseObj.accountType[0].accountType, responseObj.accountType[1].accountType];
			ac = ac.join(", ");
			
			self.userInfo.userRole = ac;
			self.userInfo.pictureUrl = {
				url : ""
			};
			self.userInfo.pictureUrl.url = responseObj.pictureUrl? responseObj.pictureUrl : ""; 
			

			cookieService.setCookie("user", response.data.JSESSIONID);

			defer.resolve();
			return defer.promise;
		}

		function getAccountType(){
			var accountType = "";

			if(Object.getOwnPropertyNames(self.userInfo).length !== 0){
				var acTemp = self.userInfo.userRole.toLowerCase().trim().split(",");
				if(acTemp.length > 1){
					if(acTemp[0] == 'professor')
						accountType = acTemp[1];
					else
						accountType = acTemp[0];
				}else{
					accountType = acTemp[0];
				}
			}
			return accountType.trim();
		}
		
		function destroySession(){
			self.userInfo = {};
		}
	}
}());