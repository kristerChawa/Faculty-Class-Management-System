(function(){
	angular.module("facultyApp")
		.directive("cardHover", cardHover);

	function cardHover(){
		return {
			restrict: "A",
			scope: {},
			link: linker
		};

		function linker(scope, elem, attrs){

			var card = elem;

			card.hover(function(e){
				card.addClass("md-whiteframe-10dp pointerCursor");
			}, function(e){
				card.removeClass("md-whiteframe-10dp pointerCursor");
			});
		}
	}
})();