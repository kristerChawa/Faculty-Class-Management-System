(function(){
	angular.module("attendanceModule")
		.controller("attendanceRptCtrl", attendanceRptCtrl);

	function attendanceRptCtrl($stateParams, $timeout, attendanceRptService){
		var self = this;
		self.hasClasslist = false;
		self.class = {
			"schedObj": {
				"section": $stateParams.s,
				"subjects": {
					"courseCode": $stateParams.c
				},
				"day": $stateParams.d
			}
		};
		self.view_highCharts = view_highCharts;
		self.downloadChart = downloadChart;
		self.chartConfig = {
	        options: {
	            chart: {
	                type:  "column"
	            }
	        },
	        series: [],
	        title: {
	            text: [self.class.schedObj.subjects.courseCode, self.class.schedObj.section].join(" ")
	        },
			loading: false,
		    yAxis: {
			  currentMin: 0,
			  currentMax: 5.5,
		    },
		    credits: {
		        enabled: false
		    }
		};

	    view_highCharts();

		function downloadChart(){
			var chart = this.chartConfig.getHighcharts();
			chart.print();
		}

		function view_highCharts(){
			var schedObj = self.class;
			attendanceRptService.view_highCharts(schedObj).then(function(response){
				console.log(response);
				if(response.data.aList.length == 0){
					self.hasClasslist = false;
				}else{
					self.hasClasslist = true;
					pushAttendance(response.data.aList);	
				}
			});
		}

		function pushAttendance(attendanceList){
			var len = attendanceList.length;
			for(var i = 0; i <= len -1; i++){
				var obj = {
					name: [attendanceList[i].classlist.users.firstName, attendanceList[i].classlist.users.lastName].join(" "),
					data: [attendanceList[i].noOfLives]
				};
				self.chartConfig.series.push(obj);
			}
		}
	}
})();