(function(){
	angular.module("profileModule")
		.controller("achievementsCtrl", achievementsCtrl);

	function achievementsCtrl($mdDialog, achievementService){
		
		const TEMP_LOC = "resources/templates/";

		var self = this;
		self.hasFiles = false;
		
		self.showUploadDialog = showUploadDialog;
		self.showDeleteDialog = showDeleteDialog;
		self.getFiles = getFiles;
		


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
											/** UploadDialog Controller **/
		
		function showUploadDialog(event){
			$mdDialog.show({
					parent: angular.element(document.body),
					targetEvent: event,
					templateUrl: TEMP_LOC + "profiling/achievements.uploadDialog.html",
					controller: dialogController,
					controllerAs: "uploadDialogCtrl"
			   });
		}
				
		function dialogController($mdDialog, $mdToast, $timeout, $http, achievementService){
			
			//DIALOG CONTROLLER - MODAL

			var self = this;
			self.achievementFileData = {};
			self.disableButton = false;
			self.cancelDialog = cancelDialog;
			self.upload_Achievement_Certificate = upload_Achievement_Certificate;

			function upload_Achievement_Certificate(){
				self.disableButton = true;

				var achievementObj = self.achievementFileData;
				
				achievementService.uploadFile(achievementObj)
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