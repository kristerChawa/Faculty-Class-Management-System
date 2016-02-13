(function(){
	angular.module("facultyApp")
		.factory("genSetService", genSetService);

	function genSetService($http){
		var genSetService = {
			updateUserProfile: updateUserProfile,
			updateUserPassword: updateUserPassword,
			generatePDF: generatePDF
		};
		return genSetService;

		function updateUserProfile(userObj){
			var request = {
				url: "updateUserProfile.action",
				method: "post",
				data: userObj,
				headers: {
					"Content-Type": "application/json",
					"dataType": "json"
				}
			};

			return $http(request)
				.then(function(response){
					console.log(response);
					return response.data;
				})
				.catch(function(error){
					return error;
				});
		}

		function updateUserPassword(userObj){
			var request = {
				url: "updateUserPassword.action",
				method: "post",
				data: userObj,
				headers: {
					"Content-Type": "application/json",
					"dataType": "json"
				}
			};

			console.log(userObj);

			return $http(request)
				.then(function(response){
					console.log(response);
					return response.data;
				})
				.catch(function(error){
					return error;
				});
		}

		function generatePDF(){
			var request = {
				url: "generatePDF.action",
				method: "post",
				headers: {
					"Content-Type": "application/json",
					"dataType": "json"
				}
			};

			return $http(request)
				.then(function(response){
					console.log(response);
					return response;
				});
		}
	}
}());