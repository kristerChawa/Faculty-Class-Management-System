(function () {
    angular.module("facultyApp")
        .service("stateService", stateService);

    function stateService($state, $q) {

        var self = this;
        self.currentState = {};
        self.updateState = updateState;
        

        function getState() {
            var defer = $q.defer(),
            currentState = $state.$current;

            console.log(currentState);

            defer.resolve(currentState);

            return defer.promise;
        }

        function updateState() {
            getState().then(function (resp) {
                self.currentState = resp;
            });
        }
        
    }
})();