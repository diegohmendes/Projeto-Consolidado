app.controller('PerfilCtrl', ['$rootScope', '$scope', '$http', function($rootScope, $scope, $http) {
	
	$scope.title = "Perfil";
	
	$scope.login = $rootScope.login;
	$scope.perfil = $rootScope.perfil;
	$scope.id = $rootScope.id;
	
}]);