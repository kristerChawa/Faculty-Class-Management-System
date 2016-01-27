(function(){
	angular.module("profileModule")
		.service("achievementService", achievementService);

	function achievementService($http){
		var self = this;

		self.file = [];
		self.addFile = addFile;
		self.getFile = getFile;


		function addFile(){
			
			


			// var request = {
			// 	method: "POST",
			// 	url: "uploadFile",
			// 	headers: {
			// 		"Content-Type": 
			// 	}
			// };
			
			var formData = new FormData(),
			xhr = new XMLHttpRequest();
			
			formData.append("file", fileData.file);
			console.log(fileData.file);
			xhr.open("get", "/uploadFile", true); //true means asynchronous
		
			xhr.onload = function(event){
				console.log(event);
			}
			
			xhr.send(formData);
			

			
		}

	
		function getFile(){
			return self.file;
		}


	}
}());