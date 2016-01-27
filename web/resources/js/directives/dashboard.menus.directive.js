(function(){
	
	angular.module("facultyApp")
		.directive("dashboardMenus", dashboardMenus);

	function dashboardMenus(){
		return {
			restrict: "E",
			scope:{
				type: "="
			},
			link: function(scope, elem, attrs){

				//Not the same as $scope
				
				scope.templateUrlLink = templateUrlLink;
				console.log(scope.type);
				var userType = "professor";

				function templateUrlLink(){

					const TEMP_LOC = "resources/templates/";

					switch(userType.toLowerCase()){
						case "professor" :
							return TEMP_LOC + "secretary/downloadProfiles.html";
						case "student" :
							return TEMP_LOC + "studentMenu.html";
						case "academicadviser":
							return TEMP_LOC + "acadAdviserMenu.html";
						case "secretary" :
							return TEMP_LOC + "secretaryMenu.html";
						case "chairperson" :
							return TEMP_LOC + "chairProfessorMenu.html";
						default :
							return TEMP_LOC + "error404.html";
					}


					
				}
				
			},
			template: function(elem, attrs){
				return "<div ng-include='templateUrlLink()'></div>";
			}
		};
	}	

}());