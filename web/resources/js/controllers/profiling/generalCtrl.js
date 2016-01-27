(function(){
	angular.module("profileModule")
		.controller("generalCtrl", generalCtrl);

	function generalCtrl(){
		var self = this;
		self.message = "Hello";
		self.showMenu = false;
		self.firstname = "Jm";

		self.openMenu = openMenu;
		self.edit = edit;
		function openMenu($mdOpenMenu, event){
			// $mdOpenMenu(event);
			$mdOpenMenu(event);
		}

		function edit(){
			self.showMenu = !self.showMenu;
		}
	}
}());