(function(){
	
	angular.module("facultyApp", ["ui.router", "ngMaterial", "md.data.table", "profileModule", "angular-loading-bar"]);


}());

(function(){

	angular.module("facultyApp")
		.config(facultyAppConfig);

	function facultyAppConfig($stateProvider, $urlRouterProvider){

		const TEMP_LOC = "resources/templates/";


		
		// $urlRouterProvider
		// 	.when("/dashboard/", "/dashboard")
			// .otherwise("/");
		

		$stateProvider
			.state("index", {
				url: "/",
				templateUrl: TEMP_LOC + "loginPage.html",
				controller: function($state, userService, $timeout, cfpLoadingBar){
					var vm = this;
					
					vm.login = function(){
						// userService.setUser(vm.user);
						cfpLoadingBar.start();
						
						$timeout(function(){
							cfpLoadingBar.complete();
							$state.go("dashboard");	
						}, 1500);
					}
				},
				controllerAs: "vm"
			})
			.state("dashboard", {
				url: "/dashboard",
				templateUrl: TEMP_LOC + "dashboard.html",
				controller: function(userService, $stateParams){
					var vm = this;
					
			
					userService.getUser().then(onComplete, onError);

					function onComplete(data){
						vm.data = data;
						console.log(vm.data);
					}

					function onError(reason){
						console.log(reason);
					}
					
				
				},
				controllerAs: "dash"
				
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
			.state("developer", {
				url: "/developer",
				templateUrl: TEMP_LOC + "developer/developer.html",
				controller: function(developerService){
					var self = this;
					self.message = "Hello";
					self.loadProfessors = loadProfessors;
					self.saveProfessors = saveProfessors;

					function loadProfessors(){
						developerService.loadProfessors().then(function(response){
							console.log(response);
							self.list = response;
							saveProfessors(self.list);
						});
					}

					function saveProfessors(data){
						developerService.saveProfessors(data).then(function(response){
							console.log(response);
						});
					}


				},
				controllerAs: "developer"
			});
	}

}());