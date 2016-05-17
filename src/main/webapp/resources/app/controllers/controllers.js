app.controller('PrincipalCtrl', ['$rootScope', '$scope', function($rootScope, $scope) {
	
	$scope.loginUser = "";
	$scope.idUser = "";
	$scope.perfilUser = "";
	
	$scope.$watch('loginUser', function (nv, ov) {
	  if (!nv) {
	    return;
	  }
	  
	  $rootScope.login 	= $scope.loginUser;
	  $rootScope.id 	= $scope.idUser;
	  $rootScope.perfil = $scope.perfilUser;
	  
	  console.log($scope.loginUser);
	  console.log($scope.idUser);
	  console.log($scope.perfilUser);
	  
	});
	
}]);