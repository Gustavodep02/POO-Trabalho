package edu.curso;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class EquipamentoDAOImpl implements EquipamentoDAO{

	private final static String DB_CLASS = "org.mariadb.jdbc.Driver";
	private final static String DB_URL = "jdbc:mariadb://localhost:3307/bibliotecadb?allowPublicKeyRetrieval=true";
	private final static String DB_USER = "root";
	private final static String DB_PASS = "alunofatec";
	
	private Connection con = null;
	
	public EquipamentoDAOImpl() throws LivroException {
		try {
			Class.forName(DB_CLASS);
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (ClassNotFoundException | SQLException e) {
			throw new LivroException(e);
		}
	}
	
	@Override
	public void inserir(Equipamento eq) throws LivroException {
		try {
			String SQL = "INSERT INTO equipamento (nome, fabricante, numero_serie) VALUES (?, ?, ?)";
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, eq.getNome());
			stm.setString(2, eq.getFabricante());
			stm.setLong(3, eq.getNumeroSerie());
			int i = stm.executeUpdate();
		} catch (SQLException err) {
			throw new LivroException(err);
		}
	}
	
	@Override
	public void atualizar(Equipamento eq) throws LivroException {
		try {
			String SQL = "UPDATE equipamento SET nome = ?, fabricante = ?, numero_serie = ? WHERE id = ?";
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, eq.getNome());
			stm.setString(2, eq.getFabricante());
			stm.setLong(3, eq.getNumeroSerie());
			stm.setLong(4, eq.getId());
			stm.executeUpdate();
		} catch (SQLException err) {
			throw new LivroException(err);
		}
	}
	
	@Override
	public void remover(Equipamento eq) throws LivroException {
		try {
			String SQL = "DELETE FROM equipamento WHERE id = ?";
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setLong(1, eq.getId());
			int i = stm.executeUpdate();
		} catch (SQLException err) {
			throw new LivroException(err);
		}
	}
	
	@Override
	public List<Equipamento> pesquisarPorNome(String nome) throws LivroException {
		List<Equipamento> lista = new ArrayList<>();
		try {
			String SQL = "SELECT * FROM equipamento WHERE nome like ?";
			PreparedStatement stm = con.prepareStatement(SQL);
			stm.setString(1, "%" + nome + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Equipamento eq = new Equipamento();
				eq.setId(rs.getLong("id"));
				eq.setNome(rs.getString("nome"));
				eq.setFabricante(rs.getString("fabricante"));
				eq.setNumeroSerie(rs.getLong("numero_serie"));
				lista.add(eq);
			}
		} catch (SQLException err) {
			throw new LivroException(err);
		}
		return lista;
	}
	
}
