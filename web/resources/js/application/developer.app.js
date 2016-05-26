(function(){
	angular.module("developerApp", ["events"]);
}());

(function(){
    angular.module("developerApp")
		.config(developerAppConfig);

	function developerAppConfig($stateProvider, $urlRouterProvider, TEMP_LOC, USER_ROLES){

		$urlRouterProvider
			.otherwise("/dashboard");
		
		$stateProvider
			.state("dashboard.userManagement", {
				url: "/users_manage",
				data:{
					authorizedRoles: [USER_ROLES.developer]
				},
				templateUrl: TEMP_LOC.PATH + "developer/users_manage.html",
				controller: "usersManageCtrl",
				controllerAs: "um"
			})
			.state("dashboard.subjManagement", {
				url: "/subj_manage",
				data:{
					authorizedRoles: [USER_ROLES.developer]
				},
				templateUrl: TEMP_LOC.PATH + "developer/subj_manage.html",
				controller: "subjManageCtrl",
				controllerAs: "sm"
			})
			.state("dashboard.auditLogs", {
				url: "/auditLogs",
				data: {
					authorizedRoles: [USER_ROLES.developer]
				},
				templateUrl: TEMP_LOC.PATH + "developer/auditLogs.html",
				controller: "auditLogsCtrl",
				controllerAs: "al"
			});
	}	
}());