package com.loopa.controller.cliente;

public class ClienteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException(String exception) {
		super(exception);
	}

}
