package br.com.fiescfrotas.servicos;

import java.util.List;

import br.com.fiescfrotas.dao.UnidadeDAO;
import br.com.fiescfrotas.exception.FrotasException;
import br.com.fiescfrotas.interfaces.IServicos;
import br.com.fiescfrotas.modelo.Unidade;

public class UnidadeServicos implements IServicos<Unidade>{

	UnidadeDAO unidadeDAO = new UnidadeDAO();
	
	@Override
	public void inserir(Unidade entity) throws FrotasException {
		
	}

	@Override
	public List<Unidade> buscarTodos() throws FrotasException {
		List<Unidade> listaDeUnidades = unidadeDAO.buscarTodasUnidades();
		if(listaDeUnidades.isEmpty())
			throw new FrotasException("Não existem Unidades cadastradas.");
		else{
			return listaDeUnidades;
		}
	}

	@Override
	public Unidade buscarPorCodigo(long codigo) throws FrotasException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Unidade entity) throws FrotasException {
		// TODO Auto-generated method stub
		
	}

}
