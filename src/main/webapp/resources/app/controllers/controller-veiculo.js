app.controller('VeiculosCtrl', ['$rootScope', '$scope', '$http', function($rootScope, $scope, $http) {
	
	$scope.title = "Veículos";
	
	$scope.login = $rootScope.login;
	$scope.perfil = $rootScope.perfil;
	$scope.id = $rootScope.id;
	
}]);