(function(){
	angular.module("facultyApp")
		.controller("genSetCtrl", genSetCtrl);

	function genSetCtrl($mdDialog, $state){

		const TEMP_LOC = "resources/templates/";
		var self = this;

		self.showDialogPassword = showDialogPassword;
		self.listOfSubjects = ["ISPROG1", "PROGAP1", "IS-EBIZ", "PROGAP2", "WEBDEVT"];
		self.selectedChips = [];
		self.user = {
			"firstname": "Jm",
			"lastname": "Santos",
			"username": "santosjm"
		};

		function showDialogPassword(event){
			$mdDialog.show({
				parent: angular.element(document.body),
				targetEvent: event,
				templateUrl: TEMP_LOC + "settings/settings.general.password.html",
				clickOutsideToClose: true
			   });
		}
		
	}
}());