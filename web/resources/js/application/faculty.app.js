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
				controller: function(developerService, $timeout){
					var self = this;
					self.message = "Hello";
					self.uploadProfessors = uploadProfessors;
					self.loadDBUsers = loadDBUsers;
					self.updateAccountType = updateAccountType;
					self.selectedUsers = {};


					function uploadProfessors(){
						developerService.uploadProfessors().then(function(response){
							console.log(response);
						});
					}

					function loadDBUsers(){
						//Database Users
						self.displayTable = false;
						self.displayProgress = true;

						developerService.loadDBUsers().then(function(response){
							
							self.displayTable = true;
							self.displayProgress = false;
							self.list = response.data.users;
							

						});
					}
					

					function updateAccountType(){
						// console.log(self.list[self.selected.acad]);

						// var x = JSON.stringify(self.selected);
						console.log(self.selectedUsers);
						// console.log(x);
						
					}


				},
				controllerAs: "developer"
			});
	}

}());