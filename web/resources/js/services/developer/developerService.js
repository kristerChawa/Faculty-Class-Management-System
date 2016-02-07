(function(){
	angular.module("facultyApp")
		.factory("developerService", developerService);

	function developerService($http){
		var service = {
			uploadProfessors: uploadProfessors,
			loadDBUsers: loadDBUsers,
			addAccountType: addAccountType
		};

		return service;

		function uploadProfessors(){
			return $http.post("resources/extras/professors.json")
				.then(function(response){
					return saveProfessors(response.data);
				})
		}

		function saveProfessors(data){

			JSON.stringify(data);
			var request = {
				method: "post",
				url: "saveProfessors.action",
				data: data,
				headers: {
					"Content-Type": "application/json",
					"dataType": "json"
				}
			};
			
			return $http(request)
				.then(function(response){
					console.log(response);
					return response;
				});
		}

		function loadDBUsers(){
			var request = {
				method: "post",
				url: "viewProfessors.action",
				headers: {
					"Content-Type": "application/json",
					"dataType": "json"
				}
			};

			return $http(request)
				.then(function(response){
					console.log(response);
					return response;
				});
		}

		function addAccountType(usersObjList){
			
			var usersObj = {
					"users": []
			};
			
			for(var ids in usersObjList){
				//Get all the ids of the selectedUsers
				var obj = {
					"userID": ids,
					"accountType": [{
						"accountType": usersObjList[ids]
					}]
				};
				usersObj.users.push(obj);
			}
			console.log(usersObj);
			

			var request = {
					method: "post",
					url: "addAccountType.action",
					data: usersObj,
					headers: {
						"Content-Type": "application/json",
						"dataType": "json"
					}
			};
			
			//Should I stringify it or let the header parse it to json? hmm
			//Answer is no need.
			
			return $http(request)
				.then(function(response){
					console.log(response);
					return response;
				});
		}
	}
}());