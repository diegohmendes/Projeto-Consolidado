package br.com.fiescfrotas.validacao;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.fiescfrotas.exception.CampoEmBrancoException;
import br.com.fiescfrotas.exception.CodigoException;
import br.com.fiescfrotas.exception.DataException;
import br.com.fiescfrotas.exception.FrotasException;
import br.com.fiescfrotas.exception.KmException;
import br.com.fiescfrotas.exception.MotorizacaoException;
import br.com.fiescfrotas.exception.PlacaException;
import br.com.fiescfrotas.exception.VeiculoException;
import br.com.fiescfrotas.modelo.Veiculo;
import br.com.fiescfrotas.util.MensagemUtil;

public class VeiculoValidar {
	
	static Logger log = Logger.getLogger(VeiculoValidar.class);
	private static MensagemUtil msg = new MensagemUtil();
	
	public void validarVeiculo(int codigo, Veiculo veiculo) throws FrotasException {
		
		// Trocar por constant
		if (codigo == 1) {
			if (veiculo.getCodigo() == 0) {
				throw new CodigoException(msg.codigoInvalido("codigo"));
			}
		} else if (codigo == 2) {
			if (veiculo.getCodigo() != 0) {
				throw new CodigoException(msg.codigoInvalido("codigo"));
			}
		} else if (veiculo.getModelo() == null || ValidarObjeto.validarString(veiculo.getModelo())) {
			throw new CampoEmBrancoException(msg.campoVazio("modelo"));
		} else if (veiculo.getMarca() == null || ValidarObjeto.validarString(veiculo.getMarca())) {
			throw new CampoEmBrancoException(msg.campoVazio("marca"));
		} else if (ValidarObjeto.validarCodigo(veiculo.getTipo()) || !validarTipo(veiculo.getTipo())) {
			throw new CodigoException(msg.codigoInvalido("tipo"));
		} else if (veiculo.getPlaca() == null || ValidarObjeto.validarString(veiculo.getPlaca())) {
			throw new CampoEmBrancoException(msg.campoVazio("placa"));
		} else if (veiculo.getPlaca().length() != 8) {
			throw new PlacaException(msg.placaInvalida(veiculo.getPlaca()));
		} else if (veiculo.getKmAtual() < 0) {
			throw new KmException(msg.kmInvalido(veiculo.getKmAtual()));
		} else if (veiculo.getCodigo() != 1 && veiculo.getCodigo() != 2) {
			throw new CodigoException(msg.codigoInvalido("status"));
		} else if (!validarMotorizacao(veiculo.getMotorizacao())) {
			throw new MotorizacaoException(msg.motorizacaoInvalido(veiculo.getMotorizacao()));
		} else if (!validarAnoVeiculo(veiculo.getAno())) {
			throw new DataException(msg.anoInvalido(veiculo.getAno()));
		} else if (veiculo.getPatrimonio() < 1) {
			throw new CodigoException(msg.patrimonioInvalido(veiculo.getPatrimonio()));
		} else if (!validarCombustivel(veiculo.getCombustivel())) {
			throw new CodigoException(msg.codigoInvalido("combustível"));
		} else if (veiculo.getCodUnidade() < 1) {
			throw new CodigoException(msg.codigoInvalido("unidade"));
		}
	}
	
	public static void validarVeiculoRN (Veiculo veiculo, List<Veiculo> listaVeiculos) throws FrotasException {
		for (Veiculo veiculoCadastrado : listaVeiculos) {

			log.trace("Iniciando validação dos campos obrigatórios");
			
			log.trace("veículo código: " + veiculo.getCodigo() + " veículo cadastrado código: " + veiculoCadastrado.getCodigo());
			log.trace("veículo placa: " + veiculo.getPlaca() + " veículo cadastrado placa: " + veiculoCadastrado.getPlaca());
			log.trace("veículo patrimonio: " + veiculo.getPatrimonio() + " veículo cadastrado patrimonio: " + veiculoCadastrado.getPatrimonio());
			
			if (veiculo.getPlaca().equals(veiculoCadastrado.getPlaca())) {
				throw new VeiculoException(msg.placaDuplicada(veiculo.getPlaca()));
			} else if (veiculo.getPatrimonio() == veiculoCadastrado.getPatrimonio()) {
				throw new VeiculoException(msg.patrimonioDuplicado(veiculo.getPatrimonio()));
			}
		}
	}

	public static Veiculo ajustarCamposString(Veiculo veiculo) {
		veiculo.setMarca(veiculo.getMarca().toUpperCase());
		veiculo.setModelo(veiculo.getModelo().toUpperCase());
		veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
		
		return veiculo;
	}
	
	private boolean validarAnoVeiculo(int ano) {
		Calendar anoatual = Calendar.getInstance();
		
		return ((ano >= 1986 && ano < (anoatual.get(Calendar.YEAR) + 2)) ? true : false);
	}

	private boolean validarTipo(int tipo) {
		switch (tipo) {
		case 6:
			return true;
		case 7:
			return true;
		case 8:
			return true;
		case 13:
			return true;
		case 14:
			return true;
		default:
			return false;
		}
	}

	private boolean validarCombustivel (int combustivel) {
		switch (combustivel) {
		case 1:
			return true;
		case 2:
			return true;
		case 3:
			return true;
		case 8:
			return true;
		case 9:
			return true;
		case 10:
			return true;
		case 12:
			return true;
		case 13:
			return true;
		default:
			return false;
		}
	}

	private boolean validarMotorizacao (float motorizacao) {
		return ((motorizacao > 0 && motorizacao < 5) ? true : false);
	}
	
}