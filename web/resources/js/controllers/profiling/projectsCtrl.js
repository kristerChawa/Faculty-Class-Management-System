(function(){
	angular.module("profileModule")
		.controller("projectsCtrl", projectsCtrl);

	function projectsCtrl($mdDialog, $timeout, projectService){
		
		const TEMP_LOC = "resources/templates/";
		
		var self = this;
		self.hasFiles = false;
		
		self.showUploadDialog = showUploadDialog;
		self.showDeleteDialog = showDeleteDialog;
		self.getFiles = getFiles;
		


		function getFiles(){
			var file = null;
			file = projectService.listFile;
			self.hasFiles = file.length ? true : false;
			return file;
			
		}

		function showDeleteDialog(event, project){
			$mdDialog.show({
					parent: angular.element(document.body),
					targetEvent: event,
					templateUrl: TEMP_LOC + "profiling/global-delete-upload.html",
					controller: deleteDialogController,
					controllerAs: "deleteDialogCtrl"
			   });
		}
									/** DeleteDialog Controller **/
		
		function deleteDialogController($mdDialog){
			var self = this;
			self.closeDialog = closeDialog;
			
			function closeDialog(){
				$mdDialog.hide();
			}
		}
		
		function showUploadDialog(event){
			$mdDialog.show({
					parent: angular.element(document.body),
					targetEvent: event,
					templateUrl: TEMP_LOC + "profiling/projects.uploadProject.html",
					controller: uploadDialogController,
					controllerAs: "uploadDialogCtrl"
			   });
		}
		
									/** UploadDialog Controller **/

		function uploadDialogController($mdDialog, $mdToast, projectService){
			var self = this;
			self.project = {};
			self.disableButton = false;
			self.cancelDialog = cancelDialog;
			self.uploadProject = uploadProject;
			

			function uploadProject(){
				
				/** Disable uploadButton to avoid double submission */
				self.disableButton = true;
				
				var projectObj = self.project;
				
				projectService.uploadProject(projectObj)
					.then(function(response){
						if(response.status == 200){
							self.cancelDialog();	
						}else{
							errorToast($mdToast);
							$timeout(function(){
								self.disableButton = false;	
							}, 1500);
						}
					});
			}

			function errorToast($mdToast){
				$mdToast.show(
					$mdToast.simple()
						.textContent("An error has occured. Please try again.")
						.position("top")
						.hideDelay(2000)
				);
			}

			function cancelDialog(){
				$mdDialog.hide();
			}
		}
	}

}());