(function(){
	angular.module("developerApp", ["angular-loading-bar"]);
}());

(function(){
	angular.module("developerApp")
		.config(developerAppConfig)

	function developerAppConfig($stateProvider, $urlRouterProvider){

		const TEMP_LOC = "resources/templates/";

		$urlRouterProvider
			.otherwise("/developer/");
		
		$stateProvider
			.state("developer", {
				url: "/developer",
				templateUrl: TEMP_LOC + "developer/developer.html",
				controller: "developerCtrl",
				controllerAs: "developer"
			});
	}	
}());