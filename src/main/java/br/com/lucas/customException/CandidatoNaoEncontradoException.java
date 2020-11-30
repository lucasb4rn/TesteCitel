package br.com.lucas.customException;

public class CandidatoNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public CandidatoNaoEncontradoException(String message) {
		super(message);
	}

}
