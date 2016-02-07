(function(){
	angular.module("profileModule")
		.service("achievementService", achievementService);

	function achievementService($http){
		var self = this;

		self.listFile = [];
		self.uploadFile = uploadFile;
		self.addFile = addFile;

		function uploadFile(achievementFile){

			var achievementFormDataObj = new FormData();
			achievementFormDataObj.append("file", achievementFile.file);
			achievementFormDataObj.append("Achievement_Certificate_Name", achievementFile.achievementName);
			
			console.log(achievementFile);
			var request = {
					method: "post",
					url: "upload_Achievement_Certificate.action",
					data: achievementFormDataObj,
					headers: {
						"Content-Type": undefined
					},
					transformRequest: angular.identity
				};
			
			
			return $http(request)
				.then(function(response){
					console.log(response);
				
					addFile(response.data.achievement_Certificate_Name, 
						response.data.achievement_Certificate_Url);
				
					return response;
				})
				.catch(function(error){
					console.log(error);
					return error;
				});
		}

		function addFile(name, url){

			var obj = {
				Achievement_Certificate_Name: name,
				Achievement_Certificate_Url: url
			};
			self.listFile.push(obj);
		}
	}
}());