package br.com.fiescfrotas.servicos;

import java.util.List;

import br.com.fiescfrotas.dao.ManutencaoDAO;
import br.com.fiescfrotas.exception.FrotasException;
import br.com.fiescfrotas.interfaces.IServicos;
import br.com.fiescfrotas.modelo.Manutencao;

public class ManutencaoServicos implements IServicos<Manutencao> {

	private ManutencaoDAO manutencaoDAO = new ManutencaoDAO();

	public Manutencao buscarPorCodigo(long codigo) throws FrotasException {
		Manutencao manutencao = null;

		manutencao = manutencaoDAO.buscarPorCodigo(codigo);

		if (manutencao == null) {
			throw new FrotasException("Manutenção não existente.");
		} else {
			return manutencao;
		}
	}

	@Override
	public void inserir(Manutencao manutencao) {
		manutencaoDAO.inserir(manutencao);
	}

	@Override
	public List<Manutencao> buscarTodos() throws FrotasException{
		List <Manutencao> listaManutencao =  manutencaoDAO.buscarTodos();

		if(listaManutencao.isEmpty() || listaManutencao == null){
			throw new FrotasException("Nenhuma manutenção cadastrado para a ~Manutenção~");
		}else {
			return listaManutencao;
		}
	}

	@Override
	public void atualizar(Manutencao manutencao) {
		manutencaoDAO.atualizar(manutencao);
	}

	public void remover(long codigo) {
		manutencaoDAO.remover(codigo);
	}
}