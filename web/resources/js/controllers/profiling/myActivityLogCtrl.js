(function(){
	angular.module("profileModule")
		.controller("myActivityLogCtrl", myActivityLogCtrl);

	function myActivityLogCtrl(activityLogService, $timeout){
		var self = this;
		self.activityLogList = [];

		self.paginate = {
			order: "timestamp",
			limit: 10,
			page: 1
		};

		$timeout(function(){
			viewActivityLogs();	
		}, 1500);

		function viewActivityLogs(){
			activityLogService.viewActivityLogs().then(function(response){
				console.log(response);
				self.activityLogList = response.auditLogList;
			});
		}
	}
})();