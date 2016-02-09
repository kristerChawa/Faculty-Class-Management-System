(function(){
	angular.module("profileModule")
		.controller("resumeCtrl", resumeCtrl);

	function resumeCtrl($scope, $mdDialog, resumeService){

		const TEMP_LOC = "resources/templates/";
		var self = this;
		self.hasFiles = false;
		// self.getFiles = getFiles;

		self.showUploadDialog = showUploadDialog;
		self.showDeleteDialog = showDeleteDialog;
		self.resumeFile = {};

		$scope.$watch(function(){
			return resumeService.listFile;
		}, function(newValue){
			console.log(newValue);
			self.hasFiles = Object.keys(newValue).length? true: false;
			if(self.hasFiles){
				self.resumeFile = newValue;
			}
		});

		// function getFiles(){
		// 	var file = null;
		// 	file = resumeService.listFile;
		// 	self.hasFiles = Object.keys(file).length? true: false;
		// 	console.log(file);
		// 	return file;
		// }

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

		function uploadDialogController($scope, $mdDialog, resumeService, $mdToast, $timeout){
			var self = this;

			self.filedata = {};
			
			$scope.$watch(function(){
				return self.filedata;
			}, function(newValue){
				var len = Object.keys(newValue).length;
				if(len != 0){
					resumeService.addFile(newValue);
				}
			});

		}

		
	}
}());