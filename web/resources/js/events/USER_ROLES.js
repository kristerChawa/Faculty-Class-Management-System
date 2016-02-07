(function(){
	angular.module("facultyApp")
		.constant("USER_ROLES",{
			student: "student",
			professor: "professor",
			acadAdviser: "acadAdviser",
			chairperson: "chairperson",
			secretary: "secretary"
		});
}());