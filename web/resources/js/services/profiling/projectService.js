(function(){
	angular.module("profileModule")
		.service("projectService", projectService);
	
	function projectService($http){
	
		var self = this;
		self.uploadProject = uploadProject;
		self.listFile = [];
		
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
					addFile(response.data.pModel.projectName, response.data.pModel.projectDate);
					return response.data;
				});
		}
		
		function addFile(projectName, projectDate){
			var obj = {
					projectName: projectName,
					projectDate: projectDate
			};
			
			self.listFile.push(obj);
		}
	}
}());