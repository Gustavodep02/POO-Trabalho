package edu.curso;

public class Livro {
    private long id =0;
    private String titulo = "";
    private String autor ="";
    private String genero = "";

    public Livro(){
    }

    public Livro(long id, String titulo, String autor, String genero){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
