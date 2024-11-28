package edu.curso;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LivroControl {
	private ObservableList<Livro> lista = FXCollections.observableArrayList();
	private long contador = 2;
	
	private LongProperty id = new SimpleLongProperty();
	private StringProperty titulo = new SimpleStringProperty();
	private StringProperty autor = new SimpleStringProperty();
	private StringProperty genero = new SimpleStringProperty();
	
	private LivroDAO livroDAO = null;
	
	public LivroControl() throws LivroException {
		livroDAO = new LivroDAOImpl();
	}
	
	public Livro paraEntidade() {
		Livro l = new Livro();
        l.setId(id.get());
        l.setTitulo(titulo.get());
        l.setAutor(autor.get());
        l.setGenero(genero.get());
        return l;
    }
	
	public void excluir(Livro l) throws LivroException {
		livroDAO.remover(l);
		pesquisarTodos();
	}
	
	public void limparTudo() {
		id.set(0);
		titulo.set("");
		autor.set("");
		genero.set("");
	}
	
	public void paraTela(Livro l) {
		if (l != null) {
			id.set(l.getId());
			titulo.set(l.getTitulo());
			autor.set(l.getAutor());
			genero.set(l.getGenero());
    }
	}
	
	public void gravar() throws LivroException{
		Livro l = new Livro();
        l.setTitulo( this.titulo.get() );
        l.setAutor( this.autor.get() );
        l.setGenero( this.genero.get() );

        if (id.get() == 0) { 
            contador += 1;
            l.setId(contador);   
            livroDAO.inserir(l);
        } else { 
            l.setId( id.get() );
            livroDAO.atualizar(l);
        }
        pesquisarTodos();
        limparTudo();

	}
	
	public void pesquisar() throws LivroException {
		lista.clear();
		lista.addAll(livroDAO.pesquisarPorTitulo(titulo.get()));
	}
	
	public void pesquisarTodos() throws LivroException {
		lista.clear();
		lista.addAll(livroDAO.pesquisarPorTitulo(""));
	}
	
	public ObservableList<Livro> getLista() {
		return this.lista;
	}

	public LongProperty idProperty() {
		return this.id;
	}
	
	public StringProperty tituloProperty() {
		return this.titulo;
	}
	
	public StringProperty autorProperty() {
		return this.autor;
	}
	
	public StringProperty generoProperty() {
		return this.genero;
	}
	

}
