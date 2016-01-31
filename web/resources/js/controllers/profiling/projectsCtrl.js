(function(){
	angular.module("profileModule")
		.controller("projectsCtrl", projectsCtrl);

	function projectsCtrl($mdDialog, $timeout, projectService){
		
		const TEMP_LOC = "resources/templates/";
		
		var self = this;
		
		self.showUploadDialog = showUploadDialog;
		self.showDeleteDialog = showDeleteDialog;
		
		self.getFiles = getFiles;
		self.hasFiles = false;


		function getFiles(){
			var file = projectService.listFile;
			self.hasFiles = file.length ? true : false;
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
			self.disableButton = false;
			self.displayProgress = false;

			function uploadProject(){
				self.disableButton = true;
				self.displayProgress = true;
				
				var d = new Date(self.project.date),
					projectDate = d.toLocaleDateString();
				
				var projectObj = {
					"pModel": {
						projectName : self.project.name,
						projectDate : projectDate
					}
				};
				
				/** Disable uploadButton to avoid double submission */
				self.disableButton = true;
				
				projectService.uploadProject(projectObj).then(function(response){
					self.displayProgress = false;
					self.closeDialog();
				});
			}
			function closeDialog(){
				$mdDialog.hide();
			}
		}
	}

}());