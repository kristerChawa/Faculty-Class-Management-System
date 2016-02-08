(function(){
	angular.module("profileModule")
		.directive("uploadFile", uploadFile);

	function uploadFile(){

		const TEMP_LOC = "resources/templates/";

		return{
			restrict: "E",
			templateUrl: TEMP_LOC + "profiling/achievements.upload.file.html",
			scope: {
				file: "=",
				filereader : "="
			},
			controller: uploadCtrl,
			controllerAs: "vm",
			link: linker
		}
		
		function uploadCtrl($mdDialog){
			var vm = this;
			vm.cancelDialog = cancelDialog;
			
			
			function cancelDialog(){
				$mdDialog.hide();
			}
		}



		function linker(scope, elem, attrs){
			var button = angular.element(elem.find("button")[0]), //Raw
			fileUpload = elem.find("input"),
			imgPreview = elem.find("img"),
			form = elem.find("form");

			
			button.on("click", function(event){
				fileUpload[0].click();
				console.log(scope);
			});

			fileUpload.on("change", function(event){
				var selectedFile = event.target.files[0],
				imagePath = event.target.result,
				fReader = new FileReader();

				fReader.readAsDataURL(selectedFile);
				
				fReader.onloadend = function(event){

					console.log(fReader.result);
					//Show the image
					imgPreview.css({
						display: "block"
					});
					imgPreview[0].src = fReader.result;
					
					scope.$apply(function(){
						scope.file = selectedFile;
						scope.filereader = fReader.result;
					});
				}
			});

		}

		
	}
}());