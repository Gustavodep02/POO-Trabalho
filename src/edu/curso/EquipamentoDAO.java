package edu.curso;
import java.util.List;
public interface EquipamentoDAO {
	void inserir( Equipamento eq ) throws LivroException;
    void atualizar( Equipamento eq ) throws LivroException;
    void remover( Equipamento eq ) throws LivroException;
    List<Equipamento> pesquisarPorNome( String nome ) throws LivroException;
}
