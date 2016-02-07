(function(){
	angular.module("facultyApp")
		.factory("authService", authService);

	function authService($http, userService){
		var authService = {
			login: login,
			checkOnlineUser: checkOnlineUser,
			logoutUser: logoutUser
		};

		return authService;

		function login(credentials){
			
			var userObj = {
				"usersModel":{
					username: credentials.username,
					password: [{
						password: credentials.password
					}]
				}
			};

			var request = {
				method: "post",
				url: "userLogin.action",
				data: userObj,
				headers: {
					"Content-Type": "application/json",
					"dataType": "json"
				}
					
			};

			return $http(request)
				.then(function(response){
					console.log(response);
					var responseObj = response.data.usersModel;
					userService.createSession(responseObj);
					return response;
				})
				.catch(function(error){
					return error;
			});
		}

		function checkOnlineUser(){
			var request = {
				method: "post",
				url: "checkOnlineUser.action",
				headers: {
					"Content-Type": "application/json",
					"dataType": "json"
				}
					
			};

			return $http(request)
				.then(function(response){
					console.log(response);
					return response;
				})
				.catch(function(error){
					return error;
				});
		}

		function logoutUser(){
			var request = {
				method: "post",
				url: "logoutUser.action",
				headers: {
					"Content-Type": "application/json",
					"dataType": "json"
				}
					
			};

			return $http(request)
				.then(function(response){
					console.log(response);
					return response;
				})
				.catch(function(error){
					return error;
				});

		}
	}
}());