(function(){
	angular.module("profileModule")
		.controller("scheduleCtrl", scheduleCtrl);

	function scheduleCtrl(scheduleService){
		var self = this;
		self.schedule = [];
		self.selected = [];
		self.query = {
			order: "courseCode"
		};


		scheduleService.getSchedules().then(function(response){
			console.log(response);
			self.schedule = response.Schedule;
		});
	}
}());