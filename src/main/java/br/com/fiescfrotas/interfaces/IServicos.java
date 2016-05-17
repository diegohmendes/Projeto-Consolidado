package br.com.fiescfrotas.interfaces;

import java.util.List;

import br.com.fiescfrotas.exception.FrotasException;

public interface IServicos<T> {
	
	public void inserir(T entity) throws FrotasException;
	
	public List<T> buscarTodos() throws FrotasException;
	
	public T buscarPorCodigo(long codigo) throws FrotasException;
	
	public void atualizar(T entity) throws FrotasException;

}
