(function(){
	angular.module("profileModule")
		.service("projectService", projectService);
	
	function projectService($http, $q, $timeout){
	
		var self = this;
		self.listFile = [];
		self.uploadProject = uploadProject;
		
		
		function uploadProject(projectFile){
			
			var projectName = projectFile.name;
			
			var d = new Date(projectFile.date),
				projectDate = d.toLocaleDateString();
			
			
			var projectObj = {
				"pModel": {
					projectName : projectName,
					projectDate : projectDate
				}
			};
			
			var request = {
				method: "post",
				url: "uploadProject.action",
				data: projectObj,
				headers: {
					"Content-Type": "application/json",
					"dataType": "json"
				}
					
			};
			
			return $http(request)
				.then(function(response){
					console.log(response);
					addFile(response.data.pModel.projectName, response.data.pModel.projectDate);
					return response;
				})
				.catch(function(error){
					return error;
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