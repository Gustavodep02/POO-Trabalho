package edu.curso;

import java.util.List;

public interface LivroDAO {
	
	void inserir(Livro l) throws LivroException;
	void atualizar(Livro l) throws LivroException;
	void remover(Livro l) throws LivroException;
	List<Livro> pesquisarPorTitulo(String titulo) throws LivroException;
}
