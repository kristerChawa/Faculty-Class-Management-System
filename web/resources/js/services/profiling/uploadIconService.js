(function(){
	angular.module("profileModule")
		.service("uploadIconService", uploadIconService);

	function uploadIconService($http){
		var self = this;
		self.upload = upload;


		function upload(formdata, url){

			var request = {
					method: "post",
					url: url,
					data: formdata,
					headers: {
						"Content-Type": undefined
					},
					transformRequest: angular.identity
				};
			return $http(request)
				.then(function(response){
					console.log(response);
					return response;
				})
				.catch(function(error){
					console.log(error);
					return error;
				})
		}
	}
}());