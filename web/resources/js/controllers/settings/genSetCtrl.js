(function(){
	angular.module("facultyApp")
		.controller("genSetCtrl", genSetCtrl);

	function genSetCtrl($mdDialog, $state, userService, authService, subjectService, genSetService){

		const TEMP_LOC = "resources/templates/";
		var self = this;
		self.listOfSubjects = [];
		self.selectedSubjects = [];
		self.showDialogPassword = showDialogPassword;
		self.saveChanges = saveChanges;
		self.loadSubjects = loadSubjects;

		self.user = userService.userInfo;

		//Init 
		loadSubjects();

		function loadSubjects(){
			subjectService.loadSubjects().then(function(response){
				self.listOfSubjects = response.subjects;
			});
		}

		function saveChanges(){
		

			console.log(self.user);
			var userObj = {
				"uModel": {
					"userID": self.user.userID,
					"idNo": self.user.idNo,
					"lastName": self.user.lastName,
					"firstName": self.user.firstName
				},
				"subjects": self.selectedSubjects
			};
			console.log(userObj);
			

			genSetService.updateUserProfile(userObj).then(function(response){
				console.log(response);
				authService.updateSession();
			});

			// userService.updateSession();


			/** 	Ganito para malagyan ko if meron na existing	*/
			// var obj = 
			// 	{
			// 		"courseCode": "ISPROG1",
			// 		"description": "JM",
			// 		"units": "3.0"
			// 	};
			
			// self.selectedSubjects.push(obj);

		
			// self.user = userService.userInfo;
			// console.log(self.user);
		}

		function showDialogPassword(event){
			$mdDialog.show({
				parent: angular.element(document.body),
				targetEvent: event,
				templateUrl: TEMP_LOC + "settings/settings.general.password.html",
				clickOutsideToClose: true,
				controller: dialogPasswordCtrl,
				controllerAs: "dialogPasswordCtrl"
			});
		}

		function dialogPasswordCtrl(){
			var self = this;
			self.message = "Hello";

			self.updateUserPassword = updateUserPassword;

			function updateUserPassword(){
				var userObj = {
					"pModel": self.pass
				};
				genSetService.updateUserPassword(userObj).then(function(response){
					console.log(response);
				});
			}

		}
	}
}());