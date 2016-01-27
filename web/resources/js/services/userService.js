(function(){
	angular.module("facultyApp")
		.factory("userService", userService);

	function userService($q){

		var result = ["Professor", "Student", "Chairperson", "AcademicAdviser", "Secretary"];
		var user = {};

		return {
			getUser: function(){
				var deferred = $q.defer();
				deferred.resolve(user);
				return deferred.promise;
			},
			setUser: function(userForm){
				user.username = userForm.username;
				user.password = userForm.password;
			}
		};
	}

}());