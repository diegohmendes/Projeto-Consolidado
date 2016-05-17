app.controller('AgendamentoCtrl', ['$rootScope', '$scope', '$http', function($rootScope, $scope, $http) {
	
	$scope.title = "Agendamentos";
	
	$scope.login = $rootScope.login;
	$scope.perfil = $rootScope.perfil;
	$scope.id = $rootScope.id;

	$http.get('../rest/unidade/buscar').success(function(response) {
		$scope.listUnidade = response;
	});
	
	$http.get('../rest/veiculo/buscar').success(function(response) {
		$scope.listVeiculo = response;
	});

}]);