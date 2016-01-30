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
			var file = achievementService.listFile;
			console.log(file);
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
			self.fileData = {};

			self.upload_Achievement_Certificate = upload_Achievement_Certificate;


			function upload_Achievement_Certificate(){
				
				self.disableButton = true;
				
				var formdata = new FormData();
				formdata.append("file", self.fileData.file);
				formdata.append("Achievement_Certificate_Name", self.fileData.achievementName);
				
				
				achievementService.uploadFile(formdata).then(function(response){
					self.closeDialog();
				});
				

				



				// achievementService.addFile();
				// self.closeDialog();
			}


			function closeDialog(){
				$mdDialog.hide();
			}
		}
	}
}());