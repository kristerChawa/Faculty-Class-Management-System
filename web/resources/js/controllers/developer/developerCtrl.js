(function(){
	angular.module("developerApp")
		.controller("developerCtrl", developerCtrl);

	function developerCtrl(developerService, $timeout){
		var self = this;
		self.uploadProfessors = uploadProfessors;
		self.loadProfessors = loadProfessors;
		self.updateAccountType = updateAccountType;
		self.uploadSubjects = uploadSubjects;
		self.loadSubjects = loadSubjects;
		self.selectedUsers = {};
		
		self.displaySubjectsTable = false;
		self.displayUsersTable = false;

		function uploadProfessors(){
			developerService.uploadProfessors().then(function(response){
				console.log(response);
			});
		}

		function loadProfessors(){
			//Database Users

			developerService.loadProfessors().then(function(response){
				
				self.displaySubjectsTable = false;
				self.displayUsersTable = true;
				
				self.list = response.data.users;
				console.log(response);
				
			});
		}
		

		function updateAccountType(){
			
			var usersObjList = self.selectedUsers;
			developerService.addAccountType(usersObjList).then(function(response){
				console.log(response);
				self.selectedUsers = {};
				self.loadProfessors();
			});
		}

		function uploadSubjects(){

			developerService.uploadSubjects().then(function(response){
				self.displaySubjectsTable = true;
				self.displayUsersTable = false;
				self.listOfSubjects = response.subjects;
			});
		}

		function loadSubjects(){
			developerService.loadSubjects().then(function(response){
				self.displaySubjectsTable = true;
				self.displayUsersTable = false;
				self.listOfSubjects = response.subjects;
			});
		}	
	}
}());