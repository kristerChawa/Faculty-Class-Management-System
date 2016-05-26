(function(){
	
    angular.module("facultyApp")
		.directive("formErrorWatcher", formErrorWatcher);

    function formErrorWatcher() {
        return {
            restrict: "A",
            scope: {
                "errorObject": "="
            },
            link: linker
        };

        function linker(scope, elem, attr) {
            var button = elem;

            console.log(scope);

            scope.$watch(function () {
                return Object.getOwnPropertyNames(scope.errorObject).length;
            }, function (newValue) {
                newValue !== 0 ? button.attr("disabled", "disabled")
                            : button.removeAttr("disabled");
            });
        }
    }
})();