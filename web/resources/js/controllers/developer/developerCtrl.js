(function(){
	angular.module("developerApp")
		.controller("developerCtrl", developerCtrl);

	function developerCtrl(developerService, $timeout){
		var self = this;
		self.uploadProfessors = uploadProfessors;
		self.loadDBUsers = loadDBUsers;
		self.updateAccountType = updateAccountType;
		self.selectedUsers = {};

		function uploadProfessors(){
			developerService.uploadProfessors().then(function(response){
				console.log(response);
			});
		}

		function loadDBUsers(){
			//Database Users
			self.displayTable = false;

			developerService.loadDBUsers().then(function(response){
				
				self.displayTable = true;
				self.list = response.data.users;
				console.log(response);
				
			});
		}
		

		function updateAccountType(){
			
			var usersObjList = self.selectedUsers;
			developerService.addAccountType(usersObjList).then(function(response){
				console.log(response);
				self.selectedUsers = {};
			});
			
		
			
		}
		
		
	}
}());