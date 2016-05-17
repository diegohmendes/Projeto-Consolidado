app.controller('DashboardCtrl', ['$rootScope', '$scope', '$http', function($rootScope, $scope, $http) {
	
	$scope.title = "Dashboard";
	$scope.nomeUsuario = $rootScope.login;
	$scope.perfil = $rootScope.perfil;
	$scope.id = $rootScope.id;
	
	$http.get('../rest/usuario/aprovacao').success(function(response) {
		$scope.listUsuario = response;
	});
	
	$http.get('../rest/agendamento/aprovacao').success(function(response) {
		$scope.listAgendamentoAprovacao = response;
	});

	$http.get('../rest/veiculo/disponiveis').success(function(response) {
		$scope.listVeiculos = response;
	});

	$http.get('../rest/agendamento/buscarUsuario', {params: {'codUsuario': 1}}).success(function(response) {
		$scope.listAgendamentoUsuario = response;
	});

	
	$scope.aprovarAgendamento = function(validacao, agendamento) {
		$http({
			url: '../rest/agendamento/aprovar/' + agendamento.codigo + '/' + validacao,
			method: 'post'
		}).then(function success(response) {	
			$scope.listAgendamentoAprovacao = response.data;
		},
		function error(response) {
			console.log(response);
		});
	};
	
	$scope.aprovarUsuario = function(validacao, usuario) {
		//var object = {codUsuario: parseFloat(usuario.codigo), validacao: validacao};
		$http({
			url: '../rest/usuario/aprovar/' + usuario.codigo + '/' + validacao,
			method: 'post'
		}).then(function success(response) {	
			$scope.listUsuario = response.data;
		},
		function error(response) {
			console.log(response);
		});
	};
	
}]);