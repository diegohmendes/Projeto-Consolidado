package br.com.fiescfrotas.exception;

public class FrotasException extends Exception {

	private static final long serialVersionUID = 1L;
	
    public FrotasException(String message) {
        super(message);
    }

    public FrotasException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
