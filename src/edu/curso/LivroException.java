package edu.curso;

public class LivroException extends Exception{
	
	public LivroException(String message) {
		super(message);
	}

	public LivroException() {
		super();
	}
	
	public LivroException(Throwable t) {
		super(t);
	}

}
