(function(){
	angular.module("facultyApp")
		.factory("developerService", developerService);

	function developerService($http){
		var service = {
			loadProfessors: loadProfessors,
			saveProfessors: saveProfessors
		};

		return service;

		function loadProfessors(){
			return $http.post("resources/extras/professors.json")
				.then(function(response){
					return response.data;
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
	}
}());