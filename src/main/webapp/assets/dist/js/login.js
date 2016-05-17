// Função para validação dos campos do login
function validarlogin() {

	var login = $("#login").val();
	
	// converte Base64
	$("#senha").val(window.btoa($("#senha").val()));
	var senha = $("#senha").val();
	
	$.ajax({
		type : "POST",
		url : "Login",
		data : 'login=' + login + '&senha=' + senha,
		success : function(data) {
			if(data == "1"){
				window.location.assign("/fiescfrotas/resources/index.jsp");
			}
		},
		error : function(data) {
			$(".alert p").html(data.responseText);
			$('.alert').fadeIn('slow');
		}
	});
};