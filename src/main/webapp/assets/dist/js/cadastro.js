function cadastro(){
	
	//metodo de validação
	validacao();
	
	//Declaração variáveis.
	var nome;
	var	email;
	var	login;
	var	senha;
	var	matricula;
	var	setor;
	var	cnh;
	var	tipoCNH;
	var	validadeCNH;
		
	//Atribuição de valores.
	nome = $('#nome').val();
	email = $('#email').val();
	login = $('#login').val();
	senha = $('#senha').val();
	matricula = $('#matricula').val();
	setor = $('#setor').val();
	cnh = $('#cnh').val();
	tipoCNH = $('#tipoCNH option:selected').val();
	validadeCNH = $('#validadeCNH').val();
	
	//base64
	senha = converterBase64(senha);
	
	validadeCNH = "2016-07-13T00:00:00.000Z"
	
	//objeto json
	var data = '{"nome":"' + nome + '",' +
				'"email":"' + email + '",' +
				'"login":"' + login + '",' +
				'"senha":"' + senha + '",' +
				'"cnh":' + cnh + ',' + 
				'"matricula":' + matricula + ',' +
				'"setor":' + setor + ',' +
				'"tipoCNH":' + tipoCNH + ',' +
				'"validadeCNH":"' + validadeCNH + '"' +
				'}';

	//var data = JSON.stringify(json);
	
	console.log(data);
	//request
	$.ajax({
		type : "POST",
		url : "/fiescfrotas/rest/cadastro/inserir",
		dataType: 'json',
		data : data,
		success : function(results) {
			debugger;
		},
		error : function(err) {
			debugger;
		}
	});
}

function validacao(){
	$('form').each(function(){
		//validar campos vazio
		$(this).find(':input, select').each(function(){
			if($(this).val() == ""){
				$(this).parent().addClass('has-error');
				$('.mensagemErro').html('Os campos em destaque devem ser preenchidos!');
				$('.mensagemErro').show();
			}
		});
		
		/*
		 * Outra validações
		 */
	});
	
}

function converterBase64(senha){
	var pwd = window.btoa(senha);
	return pwd;
}


