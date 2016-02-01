(function(){
	angular.module("facultyApp")
		.factory("developerService", developerService);

	function developerService($http){
		var service = {
			uploadProfessors: uploadProfessors,
			loadDBUsers: loadDBUsers
		};

		return service;

		function uploadProfessors(){
			return $http.post("resources/extras/professors.json")
				.then(function(response){
					return saveProfessors(response.data);
				})
		}

		function saveProfessors(data){

			JSON.stringify(data);
			var request = {
				method: "post",
				url: "saveProfessors.action",
				data: data,
				headers: {
					"Content-Type" : "application/json",
					"dataType" : "json"
				}
			};
			
			return $http(request)
				.then(function(response){
					console.log(response);
					return response;
				});
		}

		function loadDBUsers(){
			var request = {
				method: "post",
				url: "resources/extras/professors.json",
				headers: {
					"Content-Type" : "application/json",
					"dataType" : "json"
				}
			};

			return $http(request)
				.then(function(response){
					return response;
				});
		}

		function updateAccountType(){
			
		}
	}
}());