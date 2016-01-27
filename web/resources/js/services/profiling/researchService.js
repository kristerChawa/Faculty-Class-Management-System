(function(){
	angular.module("profileModule")
		.factory("researchService", researchService);
	
	function researchService($http){
		var service = {
			uploadResearch: uploadResearch
		};
		return service;
		
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
				return response.data;
			});
			
		}
	}
}());