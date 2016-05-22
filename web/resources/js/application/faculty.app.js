(function(){
	angular.module("facultyApp", [
			"ui.router", 
			"ngMaterial", 
			"md.data.table", 
			"angular-loading-bar",
			"ngCookies",
			"events",
			"profileModule",
			"developerApp",
			"secretaryApp"
		]);
}());

(function(){

	angular.module("facultyApp")
		.config(facultyAppConfig)
		.run(function($rootScope, $state){
			$rootScope.$on("$stateChangeError", function(event, toState, toParams, fromState, fromParams, error){
				$state.go("index");
			});
		});

	function facultyAppConfig($stateProvider, $urlRouterProvider, $mdThemingProvider, $cookiesProvider, USER_ROLES){

		const TEMP_LOC = "resources/templates/";

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
				templateUrl: TEMP_LOC + "loginPage.html",
				controller: "loginCtrl",
				controllerAs: "login"
			})
			.state("dashboard", {
				url: "/dashboard",
				templateUrl: TEMP_LOC + "dashboard.html",
				controller: "dashboardCtrl",
				controllerAs: "dash",
				resolve:{
					"userObj": function(authService, userService, $q, $state, cookieService){
						var deferred = $q.defer();
						console.log(userService.userInfo);

						//This one solves for refreshing the page
						// if(cookieService.getCookie("user"))
						console.log(cookieService.getCookie("user"));
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
						return deferred.promise;
					}
				}
			})
			.state("dashboard.settings", {
				url: "/settings",
				templateUrl: TEMP_LOC + "/settings/settings.html",
				data:{
					authorizedRoles: [USER_ROLES.professor, USER_ROLES.acadAdviser, USER_ROLES.chairperson, USER_ROLES.student]
				},
				controller: function($state){
					$state.go("dashboard.settings.general");
				}
			})
			.state("dashboard.settings.general", {
				url: "/general",
				templateUrl: TEMP_LOC + "settings/settings.general.html",
				controller: "genSetCtrl",
				controllerAs: "genSet"
			})
			.state("dashboard.settings.miscellaneous", {
				url: "/miscellaneous",
				templateUrl: TEMP_LOC + "settings/settings.miscellaneous.html",
				controller: "miscCtrl",
				controllerAs: "misc"
			})
			.state("forgotPwd", {
				url: "/forgotPwd",
				templateUrl: TEMP_LOC + "forgotpwd.html",
				controller: "forgotpwdCtrl",
				controllerAs: "forgotpwdCtrl"
			})
			.state("logout",{
				url: "/",
				controller: function(authService){
					authService.logoutUser();
				}
			});
	}
}());