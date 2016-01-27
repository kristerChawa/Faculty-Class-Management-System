(function(){
	angular.module("profileModule")
		.factory("scheduleService", scheduleService);

	function scheduleService($http){
		var scheduleService = {
			getSchedules : getSchedules
		};

		return scheduleService;

		function getSchedules(){
			return $http.get("uploadFile")
				.then(function(response){
					return response.data;
				});
		}
	}
}());