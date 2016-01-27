(function(){
	angular.module("profileModule")
		.controller("projectsCtrl", projectsCtrl);

	function projectsCtrl($mdDialog){
		
		const TEMP_LOC = "resources/templates/";
		
		var self = this;
		self.message = "Hello";
		self.showUploadDialog = showUploadDialog;
		self.showDeleteDialog = showDeleteDialog;
		self.hasFiles = false;

		function getFiles(){
			var file = achievementService.file;
			self.hasFiles = Object.keys(file).length ? true : false;
			return file;
		}


		function showUploadDialog(event){
			$mdDialog.show({
					parent: angular.element(document.body),
					targetEvent: event,
					templateUrl: TEMP_LOC + "profiling/projects.upload.html",
					controller: dialogController,
					controllerAs: "modal"
			   });
		}

		function showDeleteDialog(event, project){
			$mdDialog.show({
					parent: angular.element(document.body),
					targetEvent: event,
					templateUrl: TEMP_LOC + "profiling/delete-upload.html",
					controller: function($mdDialog){
						var self = this;
						self.closeDialog = function(){
							$mdDialog.hide();
						}
					},
					controllerAs: "delete"
			   });
			
		}

		function dialogController($mdDialog, projectService){
			var self = this;
			self.message = "Hello";
			self.project = {};
			self.closeDialog = closeDialog;
			self.uploadProject = uploadProject;

			function uploadProject(){
				
				var d = new Date(self.project.date),
					projectDate = d.toLocaleDateString();
				
				var projectObj = {
					"pModel": {
						projectName : self.project.name,
						projectDate : projectDate
					}
				};
				
				projectService.uploadProject(projectObj).then(function(response){
					console.log(response);
					self.closeDialog();
				});
			}
			function closeDialog(){
				$mdDialog.hide();
			}
		}
	}

}());