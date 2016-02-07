(function(){
	
	angular.module("facultyApp")
		.directive("dashboardMenus", dashboardMenus);

	function dashboardMenus(userService, $q){
		return {
			restrict: "E",
			link: function(scope, elem, attrs){

				//Not the same as $scope
				
				scope.templateUrlLink = templateUrlLink;
				$q.when(userService.userInfo.userRole).then(function(response){
					scope.userType = response;
				});
				

				function templateUrlLink(){
					
					const TEMP_LOC = "resources/templates/";

					switch(scope.userType.toLowerCase()){
						case "professor":
							return TEMP_LOC + "professor/professorMenu.html";
						case "student":
							return TEMP_LOC + "student/studentMenu.html";
						case "academicadviser":
							return TEMP_LOC + "acadAdviser/acadAdviserMenu.html";
						case "secretary": 
							return TEMP_LOC + "secretary/secretaryMenu.html";
						case "chairperson":
							return TEMP_LOC + "chairperson/chairpersonMenu.html";
						default:
							return TEMP_LOC + "errorPage/error404.html";
					}	
				}
			},
			template: function(elem, attrs){
				return "<div ng-include='templateUrlLink()'></div>";
			}
		};
	}	

}());