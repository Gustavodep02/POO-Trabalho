package edu.curso;

import java.util.List;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EquipamentoControl {
	private ObservableList<Equipamento> lista = FXCollections.observableArrayList();
	private int contador = 2;
	
	private LongProperty id = new SimpleLongProperty();
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty fabricante = new SimpleStringProperty();
	private LongProperty numeroSerie = new SimpleLongProperty();

	private EquipamentoDAO equipamentoDAO = null;
	
	public EquipamentoControl() throws LivroException {
		equipamentoDAO = new EquipamentoDAOImpl();
	}
	public Equipamento telaParaEntidade() {
		Equipamento equipamento = new Equipamento();
		equipamento.setId(id.get());
		equipamento.setNome(nome.get());
		equipamento.setFabricante(fabricante.get());
		equipamento.setNumeroSerie(numeroSerie.get());
		return equipamento;
	}
	
	public void excluir(Equipamento eq) throws LivroException {
		equipamentoDAO.remover(eq);
		pesquisarTodos();
	}
	public void limparTudo() {
		id.set(0);
		nome.set("");
		fabricante.set("");
		numeroSerie.set(0);
	}
	
	public void gravar() throws LivroException{
		Equipamento eq = telaParaEntidade();
		eq.setNome(this.nome.get());
		eq.setFabricante(this.fabricante.get());
		eq.setNumeroSerie(this.numeroSerie.get());
		if (id.get() == 0) { 
            equipamentoDAO.inserir(eq);
        } else { 
            eq.setId( id.get() );
            equipamentoDAO.atualizar(eq);
        }
        pesquisarTodos();
        limparTudo();
	}
	
	public void pesquisar() throws LivroException {
		List<Equipamento> listaTemp = equipamentoDAO.pesquisarPorNome(nome.get());
		lista.clear();
		lista.addAll(listaTemp);
	}
	
	public void pesquisarTodos() throws LivroException {
		List<Equipamento> listaTemp = equipamentoDAO.pesquisarPorNome("");
		lista.clear();
		lista.addAll(listaTemp);
	}
	
	
	
	public void entidadeParaTela(Equipamento e) {
		id.set(e.getId());
		nome.set(e.getNome());
		fabricante.set(e.getFabricante());
		numeroSerie.set(e.getNumeroSerie());
		
	}
	
	
	
	public ObservableList<Equipamento> getLista() {
		return this.lista;
	}
	
	public LongProperty idProperty() {
		return this.id;
	}
	
	public StringProperty nomeProperty() {
		return this.nome;
	}
	
	public StringProperty fabricanteProperty() {
		return this.fabricante;
	}
	
	public LongProperty numeroSerieProperty() {
		return this.numeroSerie;
	}
}
