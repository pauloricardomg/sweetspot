package br.com.chaordic.sweetspot.core.exception;

public class RequestExistsException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RequestExistsException(String requestId) {
        super(String.format("Sweet spot request with id '%s' already exists.", requestId));
    }
}
