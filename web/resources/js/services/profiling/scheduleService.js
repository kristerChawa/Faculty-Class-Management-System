(function(){
	angular.module("profileModule")
		.factory("scheduleService", scheduleService);

	function scheduleService($http){
		var scheduleService = {
			getSchedules : getSchedules
		};

		return scheduleService;

		function getSchedules(){
			var request = {
				url: "resources/extras/schedules.json",
				method: "post"
			};
			return $http(request)
				.then(function(response){
					return response.data;
				})
				.catch(function(error){
					return error;
				});
			
		}
	}
}());