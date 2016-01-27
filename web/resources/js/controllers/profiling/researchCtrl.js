(function(){
	angular.module("profileModule")
		.controller("researchCtrl", researchCtrl);

	function researchCtrl($mdDialog){
		var self = this;
		self.message = "Hello";
		self.showUploadDialog = showUploadDialog;


		function showUploadDialog(event){
			$mdDialog.show({
					parent: angular.element(document.body),
					targetEvent: event,
					templateUrl: TEMP_LOC + "profiling/research.upload.html",
					controller: dialogController,
					controllerAs: "modal"
			   });
		}

		function dialogController($mdDialog){
			var self = this;
			self.message = "Hello";

			self.closeDialog = closeDialog;

			function closeDialog(){
				$mdDialog.hide();
			}
		}
	}
}());