package br.com.acai.exceptions;

public class SizeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SizeException(String msg) {
        super(msg);
    }

}
