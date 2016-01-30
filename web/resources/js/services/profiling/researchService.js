(function(){
	angular.module("profileModule")
		.service("researchService", researchService);
	
	function researchService($http){

		var self = this;
		self.uploadResearch = uploadResearch;
		self.listFile = [];
		
		function uploadResearch(researchFile){
			JSON.stringify(researchFile);
			var request = {
				method: "post",
				url: "uploadResearch.action",
				data: researchFile,
				headers: {
					"Content-Type" : "application/json",
					"dataType" : "json"
				}
			};
			
			return $http(request)
			.then(function(response){
				console.log(response);
				addFile(response.data.rModel.researchName, response.data.rModel.researchDate);
				return response.data;
			});
			
		}
		
		function addFile(researchName, researchDate){
			var obj = {
				researchName: researchName,
				researchDate: researchDate
			};
			
			self.listFile.push(obj);
		}
	}
}());