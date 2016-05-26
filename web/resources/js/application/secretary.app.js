(function(){
	angular.module("secretaryApp", 
		["events", 
		"ngCookies"]);
})();

(function(){
	angular.module("secretaryApp")
		.config(secretaryAppConfig);

	function secretaryAppConfig($stateProvider, $urlRouterProvider, TEMP_LOC, USER_ROLES){

		$urlRouterProvider
			.otherwise("/dashboard");

		$stateProvider
			.state("dashboard.downloadProfiles", {
				url: "/downloadProfiles",
				data:{
				    authorizedRoles: [USER_ROLES.secretary],
                    stateName: "Download Profiles"
				},
				templateUrl: TEMP_LOC.PATH + "secretary/downloadProfiles.html",
				controller: "downloadProfilesCtrl",
				controllerAs: "dp"
			});
	}
}());