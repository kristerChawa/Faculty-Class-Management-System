(function(){
	angular.module("facultyApp")
		.service("feedbackService", feedbackService);

	function feedbackService($http){
		var self = this;
		self.sendFeedback = sendFeedback;

		function sendFeedback(feedbackMsg){

			var feedbackObj = {
				"feedback":{
					"message": feedbackMsg
				}
			};

			var request = {
				url: "sendFeedback.action",
				method: "post",
				data: feedbackObj,
				headers: {
					"Content-Type": "application/json",
					"dataType": "json"
				}
			};

			return $http(request)
				.then(function(response){
					console.log(response);
					return response;
				})
				.catch(function(error){
					return error;
				});
		}
	}
})();