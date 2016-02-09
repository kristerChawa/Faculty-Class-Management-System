(function(){
	angular.module("profileModule")
		.factory("subjectService", subjectService);	

	function subjectService($http){

		var subjectService = {
			loadSubjects: loadSubjects
		};
		return subjectService;

		function loadSubjects(){

			var request = {
				url: "loadSubjects.action",
				method: "post"
			};

			return $http(request)
				.then(function(response){
					console.log(response);
					return response.data;
				})
				.catch(function(error){
					return error;
				});
		}
	}
}());