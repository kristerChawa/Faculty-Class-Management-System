(function(){
	angular.module("profileModule")
		.controller("researchCtrl", researchCtrl);

	function researchCtrl($mdDialog, researchService){
		
		const TEMP_LOC = "resources/templates/";
		var self = this;
		self.message = "Hello";
		self.showUploadDialog = showUploadDialog;
		
		
		self.getFiles = getFiles;
		self.hasFiles = false;

		function getFiles(){
			var file = researchService.listFile;

			self.hasFiles = file.length ? true : false;
			return file;
		}


		function showUploadDialog(event){
			$mdDialog.show({
					parent: angular.element(document.body),
					targetEvent: event,
					templateUrl: TEMP_LOC + "profiling/research.upload.html",
					controller: dialogController,
					controllerAs: "modal"
			   });
		}

		function dialogController($mdDialog, researchService){
			var self = this;
			self.message = "Hello";
			self.research = {};
			self.closeDialog = closeDialog;
			self.uploadResearch = uploadResearch;
			self.disableButton = false;
			self.displayProgress = false;

			
			function uploadResearch(){
				
				self.disableButton = true;
				self.displayProgress = true;
				
				var d = new Date(self.research.date),
					researchDate = d.toLocaleDateString();
				
				var researchObj = {
					"rModel": {
						researchName: self.research.name,
						researchDate: researchDate
					}
				};
				researchService.uploadResearch(researchObj).then(function(response){
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