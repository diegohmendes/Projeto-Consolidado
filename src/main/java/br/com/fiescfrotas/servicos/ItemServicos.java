package br.com.fiescfrotas.servicos;

import java.util.List;

import br.com.fiescfrotas.dao.ItemDAO;
import br.com.fiescfrotas.exception.FrotasException;
import br.com.fiescfrotas.modelo.Item;

public class ItemServicos {
	private ItemDAO itemDAO = new ItemDAO();
	
	public void inserir(Item item) {	
		itemDAO.inserir(item);
	}

	public List<Item> buscarTodos(long codManutencao) throws FrotasException{		
		List<Item> listaItem = itemDAO.buscarTodosPelaManutencao(codManutencao);
		
		if(listaItem.isEmpty()){
			throw new FrotasException("Nenhum item cadastrado para a ~Manutenção~");
		}else{
			return listaItem;
		}		
	}

	public void cancelar(long codItem) {
		itemDAO.remover(codItem);
	}

	public void atualizar(Item item) {
		itemDAO.atualizar(item);
	}
}