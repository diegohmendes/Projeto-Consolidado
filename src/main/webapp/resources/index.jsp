<%@ page import="java.io.*,java.util.*"%>
<%
	Object login = session.getAttribute("login");
	Object id = session.getAttribute("id");
	Object perfil = session.getAttribute("perfil");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>FIESCFrotas</title>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
		<link rel="stylesheet" href="../assets/dist/css/AdminLTE.min.css">
		<link rel="stylesheet" href="../assets/dist/css/skins/_all-skins.min.css">
		<link rel="stylesheet" href="../assets/plugins/iCheck/flat/blue.css">
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
	</head>
	<body ng-app="app" class="hold-transition skin-green sidebar-mini">
		<div class="wrapper" ng-controller="PrincipalCtrl">
				
			<header class="main-header">
				<!-- Logo -->
				<a href="index2.html" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
					<span class="logo-mini"><b>FF</b>SC</span> <!-- logo for regular state and mobile devices -->
					<span class="logo-lg"><b>FIESC</b> Frotas SC</span>
				</a>
				<!-- Header Navbar: style can be found in header.less -->
				<nav class="navbar navbar-static-top" role="navigation">
					
					<!-- Sidebar toggle button-->
					<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
						role="button"> <span class="sr-only">Toggle navigation</span>
					</a>
					<div class="navbar-custom-menu">
						
						<ul class="nav navbar-nav">
							<!-- User Account: style can be found in dropdown.less -->
							<li class="dropdown user user-menu">
								<a class="dropdown-toggle" data-toggle="dropdown">
									<span class="hidden-xs">{{ login }}</span>
								</a>
								<ul class="dropdown-menu">
									
									<!-- User image -->
									<li class="user-header">
										<p>{{ login }} <small>Alguma informação</small></p>
									</li>
									
									<!-- Menu Body -->
									<li class="user-body">
										
									</li>
									<!-- Menu Footer-->
									<li class="user-footer">
										<div class="pull-left">
											<a href="#/perfil" class="btn btn-default btn-flat">Perfil</a>
										</div>
										<div class="pull-right">
											<a href="#/logout" class="btn btn-default btn-flat">Sair</a>
										</div>
									</li>
								</ul>
							</li>
						</ul><!-- /.nav .navbar-nav -->
						
					</div>
				</nav>
			</header>
	
			<!-- Left side column. contains the logo and sidebar -->
			<aside class="main-sidebar">
				<!-- sidebar: style can be found in sidebar.less -->
				<section class="sidebar">
					<!-- sidebar menu: : style can be found in sidebar.less -->
					<ul class="sidebar-menu">
						<li class="header">MENU</li>
						<li><a href="#/dashboard"><i class="fa fa-dashboard"></i><span>Dashboard</span></a></li>
						<li><a href="#/agendamentos"><i class="fa fa-calendar"></i><span>Agendamentos</span></a></li>
						<li><a href="#/veiculos"><i class="fa fa-car"></i><span>Veículos</span></a></li>
						<li><a href="#/usuarios"><i class="fa fa-users"></i><span>Usuários</span></a></li>
						<li><a href="#/manutencoes"><i class="fa fa-wrench"></i><span>Manutenção</span></a></li>
					</ul>
				</section>
				<!-- /.sidebar -->
			</aside>
	
			<div ng-view></div>
			
			<footer class="main-footer">
				<strong>Copyright &copy; 2016</strong> Todos os direitos reservados.	
			</footer>
			
			<input type="hidden" ng-init="loginUser = '<%= login %>'">
			<input type="hidden" ng-init="idUser = '<%= id %>'">
			<input type="hidden" ng-init="perfilUser = '<%= perfil %>'">
		
		</div><!-- ./wrapper -->
	
		<!-- jQuery 2.1.4 -->
		<script type="text/javascript" src="../assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>
		<!-- jQuery UI 1.11.4 -->
		<script type="text/javascript" src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	
<!-- 		<script src="//cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.2/angular.js"></script> -->
		
		
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.min.js"></script>
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-resource.min.js"></script>
		
	
		<!-- Bootstrap 3.3.5 -->
		<script type="text/javascript" src="../assets/bootstrap/js/bootstrap.min.js"></script>
		<!-- FastClick -->
		<script type="text/javascript" src="../assets/plugins/fastclick/fastclick.min.js"></script>
	
		<script type="text/javascript" charset="UTF-8" src="app/app.js"></script>
		<script type="text/javascript" charset="UTF-8" src="app/controllers/controllers.js"></script>
		<script type="text/javascript" charset="UTF-8" src="app/controllers/controller-agendamento.js"></script>
		<script type="text/javascript" charset="UTF-8" src="app/controllers/controller-dashboard.js"></script>
		<script type="text/javascript" charset="UTF-8" src="app/controllers/controller-usuario.js"></script>
		<script type="text/javascript" charset="UTF-8" src="app/controllers/controller-veiculo.js"></script>
		<script type="text/javascript" charset="UTF-8" src="app/controllers/controller-manutencao.js"></script>
		<script type="text/javascript" charset="UTF-8" src="app/controllers/controller-perfil.js"></script>
	
		<!-- AdminLTE App -->
		<script type="text/javascript" charset="UTF-8" src="../assets/dist/js/appteste.min.js"></script>
		<script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.11.0.js"></script>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">  
	</body>
</html>
