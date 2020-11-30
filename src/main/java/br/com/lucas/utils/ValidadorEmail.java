package br.com.lucas.utils;

public class ValidadorEmail {
	
	public boolean valida(String email) {
		return email.matches("^[\\\\w\\\\.-]+@([\\\\w\\\\-]+\\\\.)+[A-Z]{2,4}$");
	}
	

}
