(function(){
	
	angular.module("facultyApp", [
			"ui.router", 
			"ngMaterial", 
			"md.data.table", 
			"angular-loading-bar",
			"profileModule", 
			"developerApp"
			]);
}());

(function(){

	angular.module("facultyApp")
		.config(facultyAppConfig)
		.run(function($rootScope, $state){
			$rootScope.$on("$stateChangeError", function(event, toState, toParams, fromState, fromParams, error){
				// event.preventDefault();
				return $state.go("index");
			});
		});

	function facultyAppConfig($stateProvider, $urlRouterProvider){

		const TEMP_LOC = "resources/templates/";
		
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
					"userObj": function(authService, userService, $q, $state, AUTH_EVENTS){
						var deferred = $q.defer();
						console.log(userService.userInfo.username);

						//This one solves for refreshing the page
						if(userService.userInfo.username == undefined){
							authService.checkOnlineUser().then(function(response){
								var responseObj = response.data.usersModel;
								console.log(responseObj);
								if(responseObj.username == undefined){
									//Paano kung wala talagang sessionUser..
									deferred.reject(AUTH_EVENTS.notAuthenticated);
								}
								userService.createSession(response.data.usersModel);
								deferred.resolve(userService.userInfo);
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
			.state("logout",{
				url: "/",
				controller: function($scope, authService){
					
					$scope.init = function(){
						authService.logoutUser();
					}();
					
				}
			});
			
	}

}());