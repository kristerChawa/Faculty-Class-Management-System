(function(){
	angular.module("facultyApp")
		.controller("dashboardCtrl", dashboardCtrl);

	function dashboardCtrl($state, $scope, userObj){
		var self = this;

		self.user = userObj;
		console.log(self.user);
	}
}());