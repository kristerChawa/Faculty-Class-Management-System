(function(){
	angular.module("profileModule")
		.service("activityLogService", activityLogService);

	function activityLogService($http){
		var self = this;
		self.viewActivityLogs = viewActivityLogs;

		function viewActivityLogs(){
			var request = {
				url: "MyActivityLog.action",
				method: "post"
			};

			return $http(request).then(function(response){
				return response.data;
			})
			.catch(function(error){
				return error;
			});
		}
	}
})();