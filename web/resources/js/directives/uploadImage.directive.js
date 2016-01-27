(function(){
	angular.module("profileModule")
		.directive("uploadImage", uploadImage);

	function uploadImage(){


		function linker(scope, elem, attrs){
			var button = elem.find("button"),
			fileUpload = elem.find("input");

			button.on("click", function(event){
				fileUpload[0].click();
			});

			fileUpload.on("change", function(event){
				var selectedFile = event.target.files[0],
				formData = new FormData(),
				xhr = new XMLHttpRequest();
				console.log(selectedFile);

				formData.append("file", selectedFile);
				xhr.open("post", "imageUpload.php", true); //true means asynchronous

				
				xhr.send(formData);


			});

		}


		return{
			restrict: "E",
			link: linker
		}
	}
}());