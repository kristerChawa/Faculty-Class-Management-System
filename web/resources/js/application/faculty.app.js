(function(){
	angular.module("facultyApp", [
			"ui.router", 
			"ngMaterial", 
			"md.data.table", 
			"angular-loading-bar",
			"ngCookies",
            "ngMessages",
			"events",
			"profileModule",
			"developerApp",
			"secretaryApp",
            "attendanceModule"
		]);
}());

(function(){

	angular.module("facultyApp")
		.config(facultyAppConfig)
		.run(function ($rootScope, $state, stateService) {

		    $rootScope.$on("$stateChangeSuccess", function (event, toState, toParams, fromState, fromParams, error) {
		        stateService.updateState();
		    });

			$rootScope.$on("$stateChangeError", function(event, toState, toParams, fromState, fromParams, error){
				$state.go("index");
			});
		});

	function facultyAppConfig($stateProvider, $urlRouterProvider, $mdThemingProvider, $cookiesProvider, TEMP_LOC, USER_ROLES){

		$mdThemingProvider.theme("default")
			.primaryPalette("indigo")
			.accentPalette("pink")
			.warnPalette("red");		

		
		$urlRouterProvider
		 	.when("/dashboard/", "/dashboard")
			 .otherwise("/");
		
		$stateProvider
			.state("index", {
				url: "/",
				templateUrl: TEMP_LOC.PATH + "loginPage.html",
				controller: "loginCtrl",
				controllerAs: "login"
			})
			.state("dashboard", {
			    url: "/dashboard",
			    templateUrl: TEMP_LOC.PATH + "dashboard.html",
			    data:{
			        stateName: "Dashboard"
			    },
				controller: "dashboardCtrl",
				controllerAs: "dash",
				resolve:{
					"userObj": function(authService, userService, $q, $state, cookieService){
						var deferred = $q.defer();

						if(Object.getOwnPropertyNames(userService.userInfo).length === 0){
							authService.updateSession().then(function(response){
								console.log(response);
								if(response.status == 200)
									deferred.resolve(userService.userInfo);
								else
									deferred.reject();
							});
						}else{
							deferred.resolve(userService.userInfo);
					    }
						// deferred.resolve(userService.userInfo);
						return deferred.promise;
					}
				}
			})
			.state("dashboard.settings", {
				url: "/settings",
				templateUrl: TEMP_LOC.PATH + "/settings/settings.html",
				data:{
					authorizedRoles: [USER_ROLES.professor, USER_ROLES.acadAdviser, USER_ROLES.chairperson, USER_ROLES.student]
				},
				controller: function($state){
					$state.go("dashboard.settings.general");
				}
			})
			.state("dashboard.settings.general", {
				url: "/general",
				templateUrl: TEMP_LOC.PATH + "settings/settings.general.html",
				data: {
				    stateName: "General Settings"
				},
				controller: "genSetCtrl",
				controllerAs: "genSet"
			})
			.state("dashboard.settings.miscellaneous", {
				url: "/miscellaneous",
				templateUrl: TEMP_LOC.PATH + "settings/settings.miscellaneous.html",
				data: {
				    stateName: "Miscellaneous Settings"
				},
				controller: "miscCtrl",
				controllerAs: "misc"
			})
			.state("forgotPwd", {
				url: "/forgotPwd",
				templateUrl: TEMP_LOC.PATH + "forgotpwd.html",
				data: {
				    stateName: "Forgot Password"
				},
				controller: "forgotpwdCtrl",
				controllerAs: "forgotpwdCtrl"
			})
			.state("logout",{
				url: "/", 
				controller: function (authService) { //Separate this one. Be consistent
					authService.logoutUser();
				}
			});
	}
}());