app.controller('UsuariosCtrl', ['$rootScope', '$scope', '$http', function($rootScope, $scope, $http, modals) {
	
	$scope.title = 'Usuários';
	
	$scope.login = $rootScope.login;
	$scope.perfil = $rootScope.perfil;
	$scope.id = $rootScope.id;
	
	$http.get('../rest/usuario/buscar').success(function(response) {
	
		$scope.listUsuario = response;
	
		$scope.currentPage = 1;
		$scope.totalItems = $scope.listUsuario.length; 
		$scope.numPerPage = 10;  
		$scope.paginate = function (value) {  
			var begin, end, index;  
			
			begin = ($scope.currentPage - 1) * $scope.numPerPage;  
			end = begin + $scope.numPerPage;  
			index = $scope.listUsuario.indexOf(value);  
			
			return (begin <= index && index < end);  
		};
	});
}]);