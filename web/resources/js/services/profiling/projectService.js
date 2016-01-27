(function(){
	angular.module("profileModule")
		.factory("projectService", projectService);
	
	function projectService($http){
		var service = {
			uploadProject : uploadProject
		};
		return service;
		
		function uploadProject(projectFile){
			
			JSON.stringify(projectFile);
			var request = {
				method: "post",
				url: "uploadProject.action",
				data: projectFile,
				headers: {
					"Content-Type" : "application/json",
					"dataType" : "json"
				}
					
			};
			
			
			return $http(request)
				.then(function(response){
					console.log(response);
					return response.data;
				});
		}
	}
}());