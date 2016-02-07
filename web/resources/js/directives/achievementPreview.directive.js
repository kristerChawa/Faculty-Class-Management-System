(function(){
	angular.module("profileModule")
		.directive("achievementPreview", achievementPreview);

	function achievementPreview($timeout){

		const TEMP_LOC = "resources/templates/";

		return{
			restrict: "E",
			templateUrl: TEMP_LOC + "profiling/achievement.uploadPreview.html",
			scope: {
				file: "="
			},
			controller: directiveCtrl,
			link: linker
		}

		function directiveCtrl($scope){
			$scope.filename = "";
		}

		function linker(scope, elem, attrs){
			var button = angular.element(elem.find("button")[0]), //Raw
			fileUpload = elem.find("input"),
			imgPreview = elem.find("img"),
			imgContainer = elem.find("md-content");

			
			button.on("click", function(event){
				fileUpload[0].click();
			});

			fileUpload.on("change", function(event){
				var selectedFile = event.target.files[0],
				fReader = new FileReader();

				fReader.readAsDataURL(selectedFile);

				fReader.onloadend = function(event){
					
					imgContainer.attr("style", "display: block;");
					imgPreview.attr("src", fReader.result);
					var filename = selectedFile.name,
						filetype = selectedFile.name.substring(filename.indexOf("."), filename.length);

					
					if(filename.length > 20){
						filename = [filename.substring(0, 20),  
										"...",
										filetype].join("");
					}

					scope.filename = filename;
					$timeout(function(){
						//Share to the parent controller in the dialogCtrl
						scope.file = selectedFile;
					});
				}
			});

		}

		
	}
}());