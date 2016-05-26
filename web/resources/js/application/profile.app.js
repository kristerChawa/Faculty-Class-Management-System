(function(){
	angular.module("profileModule", [
			"lazyLoadingApp",
			"events",
			"schedulingModule",
			"attendanceModule"
		]);
}());

(function(){
	angular.module("profileModule")
		.config(config)
		.run(function($rootScope, $state, authService){
			$rootScope.$on("$stateChangeStart", function(event, next){
				if(next.hasOwnProperty("data")){
					var arrayOfRoles = next.data.authorizedRoles;
					authService.checkOnlineUser().then(function(response){
						if(response == true){
							if( !authService.isAuthorized(arrayOfRoles)){
								event.preventDefault();
							}
						}
					});
				}				
			});
		});

	function config($stateProvider, $urlRouterProvider, cfpLoadingBarProvider, TEMP_LOC, USER_ROLES){
		
		cfpLoadingBarProvider.includeSpinner = '<md-progress-circular md-mode="indeterminate"></md-progress-circular>';

		$urlRouterProvider
			.when("/dashboard/profile", "dashboard/profile/general")
			.otherwise("/dashboard");

		$stateProvider
			.state("dashboard.profile", {
				url: "/profile",
				templateUrl: TEMP_LOC.PATH + "profile.html",
				data: {
				    authorizedRoles: [USER_ROLES.professor, USER_ROLES.acadAdviser, USER_ROLES.chairperson],
                    stateName: "Profile"
				}
			})
			.state("dashboard.profile.general", {
				url: "/general",
				templateUrl: TEMP_LOC.PATH + "profiling/generalTab.html",
				data: {
				    authorizedRoles: [USER_ROLES.professor, USER_ROLES.acadAdviser, USER_ROLES.chairperson],
                    stateName: "Overview"
				},
				controller: "generalCtrl",
				controllerAs: "general"
			})
			.state("dashboard.profile.achievements", {
				url: "/achievements",
				templateUrl: TEMP_LOC.PATH + "profiling/achievementsTab.html",
				data:{
				    authorizedRoles: [USER_ROLES.professor, USER_ROLES.acadAdviser, USER_ROLES.chairperson],
				    stateName: "Achievements"
				},
				controller: "achievementsCtrl",
				controllerAs: "achieve"
			})
			.state("dashboard.profile.projects", {
				url: "/projects",
				templateUrl: TEMP_LOC.PATH + "profiling/projectsTab.html",
				data:{
				    authorizedRoles: [USER_ROLES.professor, USER_ROLES.acadAdviser, USER_ROLES.chairperson],
                    stateName: "Projects"
				},
				controller: "projectsCtrl",
				controllerAs: "project"
			})
			.state("dashboard.profile.research", {
				url: "/researches",
				templateUrl: TEMP_LOC.PATH + "profiling/researchTab.html",
				data: {
				    authorizedRoles: [USER_ROLES.professor, USER_ROLES.acadAdviser, USER_ROLES.chairperson],
                    stateName: "Researches"
				},
				controller: "researchCtrl",
				controllerAs: "research"
			})
			.state("dashboard.profile.resume", {
				url: "/resume",
				templateUrl: TEMP_LOC.PATH + "profiling/resumeTab.html",
				data: {
				    authorizedRoles: [USER_ROLES.professor, USER_ROLES.acadAdviser, USER_ROLES.chairperson],
                    stateName: "Resume"
				},
				controller: "resumeCtrl",
				controllerAs: "resume"
			})
			.state("dashboard.profile.schedule", { /** This controller is on the scheduling **/
				url: "/schedule",
				templateUrl: TEMP_LOC.PATH + "profiling/myScheduleTab.html",
				data: {
				    authorizedRoles: [USER_ROLES.professor, USER_ROLES.acadAdviser, USER_ROLES.chairperson],
                    stateName: "My Schedule"
				},
				controller: "scheduleCtrl",
				controllerAs: "sched"
			})
			.state("dashboard.profile.myActivityLog", {
				url: "/allActivity",
				templateUrl: TEMP_LOC.PATH + "/profiling/myActivityLog.html",
			    data: {
			        authorizedRoles: [USER_ROLES.professor, USER_ROLES.acadAdviser, USER_ROLES.chairperson],
                    stateName: "My Activity"
			    },
				controller: "myActivityLogCtrl",
				controllerAs: "act"
			});
	}
}());