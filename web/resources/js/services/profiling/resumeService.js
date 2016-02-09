(function(){
	angular.module("profileModule")
		.service("resumeService", resumeService);

	function resumeService($http){
		var self = this;
		self.listFile = {};

		self.addFile = addFile;


		function addFile(resumeObj){
			var len = Object.keys(resumeObj).length;
			self.listFile = resumeObj;
		}
	}
})();