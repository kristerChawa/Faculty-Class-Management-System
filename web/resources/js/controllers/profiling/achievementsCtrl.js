(function(){
	angular.module("profileModule")
		.controller("achievementsCtrl", achievementsCtrl);

	function achievementsCtrl($mdDialog, achievementService){
		
		const TEMP_LOC = "resources/templates/";


		var self = this;
		
		self.showUploadDialog = showUploadDialog;
		self.showDeleteDialog = showDeleteDialog;
		
		self.getFiles = getFiles;
		self.hasFiles = false;

		function getFiles(){
			var file = achievementService.file;
			self.hasFiles = Object.keys(file).length ? true : false;
			return file;
		}

		function showDeleteDialog(event, achievement){
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

		function showUploadDialog(event){
			$mdDialog.show({
					parent: angular.element(document.body),
					targetEvent: event,
					templateUrl: TEMP_LOC + "profiling/achievements.upload.html",
					controller: dialogController,
					controllerAs: "modal"
			   });
		}

		function dialogController($scope, $mdDialog, achievementService){
			
			//DIALOG CONTROLLER - MODAL

			var self = this;
			self.message = "Hello";

			self.closeDialog = closeDialog;
			self.fileData = {};

			self.uploadFile = uploadFile;


			function uploadFile(){
				//fileData - would be coming in the params which consists of the data
				self.closeDialog();
				achievementService.addFile();
			}


			function closeDialog(){
				$mdDialog.hide();
			}
		}
	}
}());