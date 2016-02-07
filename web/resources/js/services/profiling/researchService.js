(function(){
	angular.module("profileModule")
		.service("researchService", researchService);
	
	function researchService($http){

		var self = this;
		self.listFile = [];
		self.uploadResearch = uploadResearch;
		
		function uploadResearch(researchFile){
			
			var researchName = researchFile.name;

			var d = new Date(researchFile.date),
				researchDate = d.toLocaleDateString();
			
			var researchObj = {
				"rModel": {
					researchName: researchName,
					researchDate: researchDate
				}
			};


			var request = {
				method: "post",
				url: "uploadResearch.action",
				data: researchObj,
				headers: {
					"Content-Type" : "application/json",
					"dataType" : "json"
				}
			};
			
			return $http(request)
				.then(function(response){
					console.log(response);
					
					addFile(response.data.rModel.researchName, 
							response.data.rModel.researchDate);
					
					return response;
				})
				.catch(function(error){
					console.log(error);
					return error;
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