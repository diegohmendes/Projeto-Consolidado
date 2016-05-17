var app = angular.module('app',['ngRoute', 'ngResource', 'ui.bootstrap']);
 
app.config(['$routeProvider','$resourceProvider', function($routeProvider, $resourceProvider) {
	$routeProvider
		.when('/dashboard', {
			templateUrl: '/fiescfrotas/resources/app/views/dashboard/dashboard.html',
			controller: 'DashboardCtrl'
		})
		.when('/agendamentos', {
			templateUrl: '/fiescfrotas/resources/app/views/agendamentos/agendamento.html',
			controller: 'AgendamentoCtrl'
		})
		.when('/manutencoes', {
			templateUrl: '/fiescfrotas/resources/app/views/manutencoes/manutencao.html',
			controller: 'ManutencoesCtrl'
		})
		.when('/usuarios', {
			templateUrl: '/fiescfrotas/resources/app/views/usuarios/usuario.html',
			controller: 'UsuariosCtrl'
		})
		.when('/veiculos', {
			templateUrl: '/fiescfrotas/resources/app/views/veiculos/veiculo.html',
			controller: 'VeiculosCtrl'
		})
		.when('/perfil', {
			templateUrl: '/fiescfrotas/resources/app/views/perfil/perfil.html',
			controller: 'PerfilCtrl'
		})
		.otherwise({
			redirectTo: '/dashboard'
		});
}]);