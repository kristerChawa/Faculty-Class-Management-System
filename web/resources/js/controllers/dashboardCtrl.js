(function(){
	angular.module("facultyApp")
		.controller("dashboardCtrl", dashboardCtrl);

	function dashboardCtrl($state, $scope, $mdDialog, userObj, userService, stateService, TEMP_LOC){
		var self = this;
		self.user = userObj;
		self.currentState = {};
		self.userRole = userService.userInfo.userRole;
		self.openFeedbackDialog = openFeedbackDialog;

		getAccountType();

		$scope.$watch(function(){
		    return stateService.currentState;
		}, function(newValue){
		    console.log(newValue);
		    self.currentState = newValue;
		});
        
		function getAccountType() {
			var ac = self.userRole;
			self.hasAuth = (ac == 'Student' || ac == 'Developer') ? true : false;
		}

		function openFeedbackDialog(event) {
		    $mdDialog.show({
		        parent: angular.element(document.body),
		        targetEvent: event,
		        templateUrl: TEMP_LOC.PATH + "sendFeedback.html",
		        controller: feedbackCtrl,
                controllerAs: "vm"
		    });

		    function feedbackCtrl($scope, $mdToast, feedbackService) {
		        var self = this;
		        self.sendFeedback = sendFeedback;
		        self.cancelDialog = cancelDialog;
		        self.isProcessing = false;
		        
		        function sendFeedback() {
		        	var feedbackMsg = self.feedbackMsg;
		        	self.isProcessing = true;
		            feedbackService.sendFeedback(feedbackMsg).then(function(resp){
		            	if(resp.status == 200){
		            		displayToast("Thank you for your feedback.");
		            		self.cancelDialog();
		            	}else{
		            		displayToast("Something went wrong. Please try again.");
		            	}
		            });
		        }

		        function cancelDialog() {
		            $mdDialog.hide();
		        }

		        function displayToast(message){
		        	$mdToast.show(
		        		$mdToast.simple()
		        			.textContent(message)
		        			.position("top")
		        			.hideDelay(3000)
		        	)
		        }
		    }
		}
	}
}());