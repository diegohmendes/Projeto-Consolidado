package br.com.fiescfrotas.servicos;

import java.util.List;

import br.com.fiescfrotas.constants.AgendamentoConstants;
import br.com.fiescfrotas.constants.UsoConstants;
import br.com.fiescfrotas.dao.UsoDAO;
import br.com.fiescfrotas.exception.FrotasException;
import br.com.fiescfrotas.interfaces.IServicos;
import br.com.fiescfrotas.modelo.Uso;

public class UsoServicos implements IServicos<Uso>{

	private UsoDAO usoDAO = new UsoDAO();

	/**
	 * Método para inserir um uso.
	 * 
	 * @param Uso
	 * Uso - Objeto uso
	 * @return void
	 * @throws FrotasException
	 */
	@Override
	public void inserir(Uso uso) throws FrotasException {
		usoDAO.inserir(uso);
	}
	
	/**
	 * Método para buscar todos os usos
	 * 
	 * @return List<Agendamento> - Uma lista de uso.
	 * @throws FrotasException
	 */
	public List<Uso> buscarTodos() {
		List<Uso> lista = usoDAO.buscarTodos();
		return lista;
	}
	
	/**
	 * Método para buscar um uso específico.
	 * 
	 * @param uso
	 * Uso - Objeto uso
	 * @return Objeto uso
	 * @throws FrotasException
	 */
	@Override
	public Uso buscarPorCodigo(long codigo) throws FrotasException {
		Uso buscar = usoDAO.buscarPorCodigo(codigo);
		return buscar;
	}
	
	/**
	 * Método para atualizar um uso.
	 * 
	 * @param uso
	 * Uso - Objeto uso
	 * @return void
	 * @throws FrotasException
	 */
	public void atualizar(Uso uso) throws FrotasException {
		usoDAO.atualizar(uso);
	}
	
	/**
	 * Método para cancelar um uso.
	 * 
	 * @param uso
	 * Uso - Objeto uso
	 * @return void
	 * @throws FrotasException
	 */
	public void cancelar(long codigo) throws FrotasException {
		Uso uso = usoDAO.buscarPorCodigo(codigo);
		uso.setStatus(UsoConstants.INATIVO);
		usoDAO.atualizar(uso);
	}
}