(function(){
	angular.module("facultyApp")
		.directive("toggleNavItems", toggleNavItems);


	function toggleNavItems($mdSidenav){
		return{
			restrict: "A",
			link: function(scope, elem, attrs){
					
				//get the parent which is the container
				var childrenLen = elem[0].children.length; 
				for(var i = 0, len = childrenLen; i <= len -1; i++){
					
					//register the element as angular since md-* are angular elements

					var childElem = angular.element(elem[0].children[i]); 

					childElem.on("click", function(event){
						$mdSidenav("sidenav").close();
					});
				}
			}
		}
	}
}());

(function(){
	angular.module("facultyApp")
		.directive("toggleNav", toggleNav);

	function toggleNav($mdSidenav){
		return{
			restrict: "A",
			link: function(scope, elem, attrs){
				elem.on("click", function(event){
					$mdSidenav("sidenav").open();
				});
			}
		}
	}
}());