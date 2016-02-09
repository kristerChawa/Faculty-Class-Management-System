(function(){
	angular.module("profileModule")
		.controller("researchCtrl", researchCtrl);

	function researchCtrl($mdDialog, researchService){
		
		const TEMP_LOC = "resources/templates/";
		var self = this;
		self.hasFiles = false;
		
		self.showUploadDialog = showUploadDialog;
		self.showDeleteDialog = showDeleteDialog;
		
		self.getFiles = getFiles;
		

		function getFiles(){
			var file = researchService.listFile;

			self.hasFiles = file.length ? true : false;
			return file;
		}

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

									/** UploadDialog Controller **/

		function showUploadDialog(event){
			$mdDialog.show({
					parent: angular.element(document.body),
					targetEvent: event,
					templateUrl: TEMP_LOC + "profiling/research.uploadResearch.html",
					controller: uploadDialogController,
					controllerAs: "uploadDialogCtrl"
			   });
		}

		function uploadDialogController($mdDialog, $mdToast, $timeout, researchService){
			var self = this;
			self.research = {};
			self.cancelDialog = cancelDialog;
			self.uploadResearch = uploadResearch;
			self.disableButton = false;

			
			function uploadResearch(){
				
				/** Disable uploadButton to avoid double submission */
				self.disableButton = true;
				
				var researchObj = self.research;
				
				researchService.uploadResearch(researchObj)
					.then(function(response){
						console.log(response);
						//200 status is OK!
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