(function(){
	angular.module("profileModule")
		.controller("generalCtrl", generalCtrl);

	function generalCtrl(){
		var self = this;
		self.message = "Hello";
		
		self.fileData = {};
		
		
	}
}());