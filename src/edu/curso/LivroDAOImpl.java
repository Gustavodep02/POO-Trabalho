package edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAOImpl implements LivroDAO{
private final static String DB_CLASS = "org.mariadb.jdbc.Driver";
private final static String DB_URL = "jdbc:mariadb://localhost:3307/bibliotecadb?allowPublicKeyRetrieval=true";
private final static String DB_USER = "root";
private final static String DB_PASS = "alunofatec";

private Connection con = null;

public LivroDAOImpl() throws LivroException{
	    try{
        Class.forName(DB_CLASS);
        con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }catch(ClassNotFoundException | SQLException e){
    	throw new LivroException(e);
    }
}

@Override
public void inserir(Livro l) throws LivroException {
	try {
		String SQL = "INSERT INTO livro (titulo, autor, genero) VALUES (?, ?, ?)";
		PreparedStatement stm = con.prepareStatement(SQL);
		stm.setString(1, l.getTitulo());
		stm.setString(2, l.getAutor());
		stm.setString(3, l.getGenero());
		int i = stm.executeUpdate();
	} catch (SQLException e) {
		throw new LivroException(e);
	}
}

@Override
public void atualizar(Livro l) throws LivroException {
	try {
		String SQL = "UPDATE livro SET titulo = ?, autor = ?, genero = ? WHERE id = ?";
		PreparedStatement stm = con.prepareStatement(SQL);
		
		stm.setString(1, l.getTitulo());
		stm.setString(2, l.getAutor());
		stm.setString(3, l.getGenero());
		stm.setLong(4, l.getId());
		int i = stm.executeUpdate();
	} catch (SQLException e) {
		throw new LivroException(e);
	}
}

@Override
public void remover(Livro l) throws LivroException {
	try {
		String SQL = "DELETE FROM livro WHERE id = ?";
		PreparedStatement stm = con.prepareStatement(SQL);
		stm.setLong(1, l.getId());
		int i = stm.executeUpdate();
	} catch (SQLException e) {
		throw new LivroException(e);
	}
}

@Override
public List<Livro> pesquisarPorTitulo(String titulo)throws LivroException{
	List<Livro> lista = new ArrayList<>();
	try {
		String SQL = "SELECT * FROM livro WHERE titulo LIKE ?";
		PreparedStatement stm = con.prepareStatement(SQL);
		stm.setString(1, "%" + titulo + "%");
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			Livro l = new Livro();
			l.setId(rs.getLong("id"));
			l.setTitulo(rs.getString("titulo"));
			l.setAutor(rs.getString("autor"));
			l.setGenero(rs.getString("genero"));
			lista.add(l);
		}
	} catch (SQLException e) {
		throw new LivroException(e);
	}
	return lista;
}
}

