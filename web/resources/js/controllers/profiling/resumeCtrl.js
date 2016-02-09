(function(){
	angular.module("profileModule")
		.controller("resumeCtrl", resumeCtrl);

	function resumeCtrl($mdDialog){

		const TEMP_LOC = "resources/templates/";
		var self = this;
		self.hasFiles = false;

		self.showUploadDialog = showUploadDialog;
		self.showDeleteDialog = showDeleteDialog;

		function showDeleteDialog(event, research){
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
					templateUrl: TEMP_LOC + "profiling/resume.uploadResume.html",
					controller: uploadDialogController,
					controllerAs: "uploadDialogCtrl"
			   });
		}

		function uploadDialogController($mdDialog, $mdToast, $timeout){
			var self = this;

			self.filedata = {};
			

		}

		
	}
}());