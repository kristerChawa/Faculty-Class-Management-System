(function(){
	angular.module("developerApp")
		.controller("auditLogsCtrl", auditLogsCtrl);

	function auditLogsCtrl(developerService){
		var self = this;
		self.viewAuditLogs = viewAuditLogs;
		self.auditLogList = [];

		self.paginate = {
			order: "timestamp",
			limit: 10,
			page: 1
		};

		self.chartConfig = {
	        options: {
	            chart: {
	                type: 'column'
	            }
	        },
	        series: [],
	        title: {
	            text: "Audit"
	        },
			loading: false
		};

		viewAuditLogs();

		function viewAuditLogs(){
			developerService.viewAuditLogs().then(function(response){
				console.log(response);
				self.auditLogList = response.auditLogList;

				addSeries(self.auditLogList);

			});
		}

		function addSeries(auditList){
			var len = auditList.length;
			for(var i = 0; i <= len -1; i++){
				var obj = {};

				if(!hasDuplicateAuditTypeInChart(auditList[i].auditType)) { //walang duplicate - unique
					obj.data = [0];
					obj.name = auditList[i].auditType;
				}else{
					var chart = self.chartConfig.series;
					for(type in chart){
						if(chart[type].name == auditList[i].auditType){
							var temp = chart[type].data[0];
							// obj.name = chart[type].name;
							obj.data = [temp++];
							break;
						}else{
							continue;
						}
					}
				}

				console.log(obj);

				self.chartConfig.series.push(obj);
			}
		}

		function hasDuplicateAuditTypeInChart(auditType){
			var chart = self.chartConfig.series;
			for(type in chart){
				if(chart[type].name == auditType){
					return true;
				}else{
					continue;
				}
			}
			return false;
		}
	}
})();