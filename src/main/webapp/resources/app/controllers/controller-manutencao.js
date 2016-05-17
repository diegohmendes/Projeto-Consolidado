app.controller('ManutencoesCtrl', ['$rootScope', '$scope', '$http', function($rootScope, $scope, $http) {
	
	$scope.title = "Manutenções";
	
	$scope.login = $rootScope.login;
	$scope.perfil = $rootScope.perfil;
	$scope.id = $rootScope.id;
	
}]);