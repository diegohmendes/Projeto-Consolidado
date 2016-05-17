package br.com.fiescfrotas.servicos;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.fiescfrotas.constants.VeiculoConstants;
import br.com.fiescfrotas.dao.VeiculoDAO;
import br.com.fiescfrotas.exception.FrotasException;
import br.com.fiescfrotas.interfaces.IServicos;
import br.com.fiescfrotas.modelo.Veiculo;
import br.com.fiescfrotas.validacao.VeiculoValidar;

public class VeiculoServicos implements IServicos<Veiculo>{

	static Logger log = Logger.getLogger(VeiculoServicos.class);
	private VeiculoDAO veiculoDAO = new VeiculoDAO();
	VeiculoValidar validar = new VeiculoValidar();

	/**
	 * Método para inserir veículo.
	 * 
	 * @param veiculo
	 *            Veiculo - Objeto veiculo
	 * @return void
	 * @throws FrotasException 
	 */
	public void inserir(Veiculo veiculo) throws FrotasException {
		log.debug("Iniciado validação do veiculo");
		log.trace(veiculo.toString());
		
		validar.validarVeiculo(1,VeiculoValidar.ajustarCamposString(veiculo));
		
		List<Veiculo> listaVeiculos = veiculoDAO.buscarTodosUnicos(veiculo.getCodigo(), veiculo.getPlaca(), veiculo.getPatrimonio());
		log.trace("Lista contém " + listaVeiculos.size());
		
		
		if (listaVeiculos.isEmpty()) {
			log.debug("Iniciando persistência do veículo");
			veiculoDAO.inserir(veiculo);

		} else {
			VeiculoValidar.validarVeiculoRN(veiculo, listaVeiculos);
		}
	}

	public List<Veiculo> buscarTodos() throws FrotasException {
		log.debug("Iniciado busca dos veículos ativos");
		
		List<Veiculo> listaVeiculos = veiculoDAO.buscarPorVeiculosAtivos();
		
		if (listaVeiculos.isEmpty()) {
			throw new FrotasException("Ocorreu um erro ao buscar por todos os veículos ativos");
		} else {
			return listaVeiculos;			
		}
	}

	public Veiculo buscarPorCodigo(long codigo) throws FrotasException {
		log.debug("Iniciado busca do veiculo");
		
		Veiculo veiculo = veiculoDAO.buscarPorCodigo(codigo);
		if (veiculo == null) {
			throw new FrotasException("Não foi localizado um veículo com este código");
		}
		
		return veiculo;
	}

	/**
	 * @param codigo
	 * @return
	 * @throws FrotasException
	 */
	public String cancelar(long codigo) throws FrotasException {
		log.debug("Iniciado cancelamento do veiculo");
		
		String msg = null;
		
		AgendamentoServicos agendamentoServicos = new AgendamentoServicos();
		Veiculo veiculo = veiculoDAO.buscarPorCodigo(codigo);
		
		if (agendamentoServicos.buscarAgendamentoPorVeiculo2(codigo)) {
			veiculo.setStatus(VeiculoConstants.INATIVO);
			veiculoDAO.atualizar(veiculo);
			
			msg = "O veículo possuía vínculos e foi inativado com sucesso";
		} else {
			veiculoDAO.remover(codigo);
			
			msg = "O veículo não possuía vínculos e foi deletado com sucesso";
		}
		
		return msg;
	}

	public void atualizar(Veiculo veiculo) throws FrotasException {
		log.debug("Iniciado validação do veiculo");
		log.trace(veiculo.toString());
		
		validar.validarVeiculo(2,VeiculoValidar.ajustarCamposString(veiculo));
		
		List<Veiculo> listaVeiculos = veiculoDAO.buscarTodosUnicos(veiculo.getCodigo(), veiculo.getPlaca(), veiculo.getPatrimonio());
		log.trace("Lista contém " + listaVeiculos.size());
		
		if (listaVeiculos.isEmpty()) {
			log.debug("Iniciando persistência do veículo");
			veiculoDAO.atualizar(veiculo);

		} else {
			VeiculoValidar.validarVeiculoRN(veiculo, listaVeiculos);
		}
	}

	/**
	 * @return
	 * @throws FrotasException
	 */
	public List<Veiculo> buscarVeiculosDisponiveis() throws FrotasException {
		log.debug("Iniciado busca do veículos disponíveis");
		
		List<Veiculo> listaVeiculos = veiculoDAO.buscarVeiculosDisponiveis();
		
		if (listaVeiculos.isEmpty()) {
			throw new FrotasException("Ocorreu um erro ao buscar os veículos disponíveis");
		} else {
			return listaVeiculos;			
		}
	}

}