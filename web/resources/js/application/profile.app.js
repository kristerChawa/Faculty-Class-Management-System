(function(){
	angular.module("profileModule", ["ui.router", "lazyLoadingApp", "angular-loading-bar"]);
}());

(function(){
	angular.module("profileModule")
		.config(config);

	function config($stateProvider, $urlRouterProvider, cfpLoadingBarProvider){
		const TEMP_LOC = "resources/templates/";
		cfpLoadingBarProvider.includeSpinner = false;
		// cfpLoadingBarProvider.loadingBarTemplate = '<div><md-progress-linear md-mode="determinate"></md-progress-linear></div>';
		// cfpLoadingBarProvider.spinnerTemplate = '<div><md-progress-circular md-mode="indeterminate"></md-progress-circular></div>';

		$urlRouterProvider
			.when("/dashboard/profile", "dashboard/profile/general")
			.otherwise("/dashboard/profile");

		$stateProvider
			.state("dashboard.profile", {
				url: "/profile",
				templateUrl: TEMP_LOC + "profile.html"
			})
			.state("dashboard.profile.general", {
				url: "/general",
				templateUrl: TEMP_LOC + "profiling/generalTab.html",
				controller: "generalCtrl",
				controllerAs: "general"
			})
			.state("dashboard.profile.achievements", {
				url: "/achievements",
				templateUrl: TEMP_LOC + "profiling/achievementsTab.html",
				controller: "achievementsCtrl",
				controllerAs: "achieve",
				resolve: {
					listFiles: function($q, $http, achievementService, cfpLoadingBar){
						// Check for the data in the database first
						cfpLoadingBar.start();
						var defered = $q.defer();
						
						$http.post("https://raw.githubusercontent.com/angular/material/v1.0.4/package.json")
							.then(function(){
								if(achievementService.listFile.length == 0){
									for(var i = 0; i <= 50; i++){
										achievementService.addFile("Jm", "https://www.gravatar.com/avatar/374f9fee76e78081f935fc4dd01c09eb?s=32&d=identicon&r=PG&f=1");	
									}
								}
								defered.resolve();
								cfpLoadingBar.complete();
							});

						return defered.promise;
					}
				}
			})
			.state("dashboard.profile.projects", {
				url: "/projects",
				templateUrl: TEMP_LOC + "profiling/projectsTab.html",
				controller: "projectsCtrl",
				controllerAs: "project"
			})
			.state("dashboard.profile.research", {
				url: "/researches",
				templateUrl: TEMP_LOC + "profiling/researchTab.html",
				controller: "researchCtrl",
				controllerAs: "research"
			})
			.state("dashboard.profile.schedule", {
				url: "/schedule",
				templateUrl: TEMP_LOC + "profiling/myScheduleTab.html",
				controller: "scheduleCtrl",
				controllerAs: "sched"
			});
	}
}());