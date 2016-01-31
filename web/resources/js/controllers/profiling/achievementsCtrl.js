(function(){
	angular.module("profileModule")
		.controller("achievementsCtrl", achievementsCtrl);

	function achievementsCtrl($mdDialog, achievementService, listFiles , cfpLoadingBar){
		
		const TEMP_LOC = "resources/templates/";

		var self = this;
		
		self.showUploadDialog = showUploadDialog;
		self.showDeleteDialog = showDeleteDialog;
		
		self.getFiles = getFiles;
		self.hasFiles = false;


		function getFiles(){
			var file = null;
			
			file = achievementService.listFile;
			self.hasFiles = file.length ? true : false;
			
		
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
					templateUrl: TEMP_LOC + "profiling/achievements.uploadDialog.html",
					controller: dialogController,
					controllerAs: "modal"
			   });
		}

		function dialogController($scope, $mdDialog, achievementService, $http){
			
			//DIALOG CONTROLLER - MODAL

			var self = this;
			self.closeDialog = closeDialog;
			self.disableButton = false;
			self.displayProgress = false;
			self.fileData = {};

			self.upload_Achievement_Certificate = upload_Achievement_Certificate;

			function upload_Achievement_Certificate(){
				cfpLoadingBar.start();
				self.disableButton = true;
				// self.displayProgress = true;

				achievementService.uploadFile(self.fileData).then(function(response){
					// self.displayProgress = false;
					cfpLoadingBar.complete();
					self.closeDialog();
				});				
			}


			function closeDialog(){
				$mdDialog.hide();
			}
		}
	}
}());