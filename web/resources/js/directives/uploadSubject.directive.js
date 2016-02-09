(function(){
	angular.module("facultyApp")
		.directive("uploadSubjectsGrid", uploadSubjectsGrid);

		function uploadSubjectsGrid(){
			
			function linker(scope, elem, attrs){

			}

			return{
				restict: "E",
				link: linker
			}
		}
}());