(function(){
	angular.module("profileModule", ["ui.router"]);
}());

(function(){
	angular.module("profileModule")
		.config(config);

	function config($stateProvider, $urlRouterProvider){
		const TEMP_LOC = "resources/templates/";

		$urlRouterProvider
		.otherwise("/");

		$stateProvider
			.state("dashboard.profile", {
				url: "/profile",
				templateUrl: TEMP_LOC + "profile.html",
				controller: function($state, $stateParams){
					// console.log($stateParams);
					$state.go("dashboard.profile.general");
					
				}
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
				controllerAs: "achieve"
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