package edu.curso;

public class Equipamento {
	
	private long id;
	private String nome;
	private String fabricante;
	private long numeroSerie;
	
	public Equipamento() {
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public long getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(long numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	
	
}
