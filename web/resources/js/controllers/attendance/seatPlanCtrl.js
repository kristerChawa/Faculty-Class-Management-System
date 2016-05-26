(function(){
	angular.module("attendanceModule")
		.controller("seatPlanCtrl", seatPlanCtrl);

	function seatPlanCtrl($scope, $stateParams, seatPlanService, $mdDialog, TEMP_LOC){
		var self = this;
		self.hasClasslist = false;
		self.showLoadDialog = showLoadDialog;
		self.attendanceObj = [];
		self.class = {
			"schedObj": {
				"section": $stateParams.s,
				"subjects": {
					"courseCode": $stateParams.c
				},
				"day": $stateParams.d
			},
			"date": seatPlanService.getDate_Helper().getDate
		};

		$scope.$watch(function(){
			return seatPlanService.date;
		}, function(newValue){
			if(typeof newValue === "string"){
				self.class.date = newValue;
			}
			
		});

		//init
		hasClasslist();

		function hasClasslist(){
			var schedObj = self.class;
			seatPlanService.viewClassList(schedObj).then(function(response){
				self.hasClasslist = (response.status == 200 
					&& response.data.classList.length > 0) ? true : false;
			});
		}

		function showLoadDialog(event){
			$mdDialog.show({
				parent: angular.element(document.body),
				targetEvent: event,
				templateUrl: TEMP_LOC.PATH + "attendance/seatPlan.loadAttendanceDialog.html",
				controller: loadDialogCtrl,
				controllerAs: "loadDialogCtrl",
				clickOutsideToClose: false
		   });
		}

		function loadDialogCtrl($mdDialog, seatPlanService, $stateParams){
			var self = this;
			self.loadAttendance = loadAttendance;
			self.displayAttendanceDates = displayAttendanceDates;
			self.cancelDialog =  cancelDialog;
			self.attendanceDates = [];
			self.date = new Date(); //Datepicker init
			self.class = {
				"schedObj": {
					"section": $stateParams.s,
					"subjects": {
						"courseCode": $stateParams.c
					},
					"day": $stateParams.d
				},
				"date": ""
			};

			displayAttendanceDates();

			function loadAttendance(){
				// self.class.date = seatPlanService.getDate_Helper().formatDate(self.date);
				self.class.date = self.date;
				var schedObj = self.class;
				seatPlanService.viewAttendance(schedObj).then(function(response){
					seatPlanService.date = schedObj.date;
					cancelDialog();
				});
			}

			function displayAttendanceDates(){
				var schedObj = self.class;
				seatPlanService.viewAttendanceDates(schedObj).then(function(response){
					console.log(response);
					self.attendanceDates = response.data.aList;
				});
			}

			function cancelDialog(){
				$mdDialog.hide();
			}
		}
	}
})();