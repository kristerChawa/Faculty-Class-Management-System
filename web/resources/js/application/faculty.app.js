(function(){
	
	angular.module("facultyApp", ["ui.router", "ngMaterial", "md.data.table", "profileModule"]);

}());

(function(){

	angular.module("facultyApp")
		.config(facultyAppConfig);

	function facultyAppConfig($stateProvider, $urlRouterProvider){

		const TEMP_LOC = "resources/templates/";


		
		$urlRouterProvider
			.otherwise("/");
		

		$stateProvider
			.state("index", {
				url: "/",
				templateUrl: TEMP_LOC + "loginPage.html",
				controller: function($state, userService, $timeout){
					var vm = this;
					
					vm.login = function(){
						// userService.setUser(vm.user);
						
						vm.progressNumber = 80;
						$timeout(function(){
							vm.displayProgress = 100;
							$state.go("dashboard", {type: "student"});	
						}, 1500);
					}
				},
				controllerAs: "vm"
			})
			.state("dashboard", {
				url: "/dashboard",
				templateUrl: TEMP_LOC + "dashboard.html",
				params:{
					type: null
				},
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
				templateUrl: TEMP_LOC + "settings.html",
				controller: "genSetCtrl",
				controllerAs: "genSet"
			});
	}

}());