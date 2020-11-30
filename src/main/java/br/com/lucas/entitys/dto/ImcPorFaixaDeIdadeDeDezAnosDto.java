package br.com.lucas.entitys.dto;

public class ImcPorFaixaDeIdadeDeDezAnosDto {

	private String faixaDeIdade;
	private double icmMedio;

	
	public ImcPorFaixaDeIdadeDeDezAnosDto() {}
	
	public ImcPorFaixaDeIdadeDeDezAnosDto(String faixaDeIdade, double icmMedio) {
		this.faixaDeIdade = faixaDeIdade;
		this.icmMedio = icmMedio;
	}

	public String getFaixaDeIdade() {
		return faixaDeIdade;
	}

	public void setFaixaDeIdade(String faixaDeIdade) {
		this.faixaDeIdade = faixaDeIdade;
	}

	public double getIcmMedio() {
		return icmMedio;
	}

	public void setIcmMedio(double icmMedio) {
		this.icmMedio = icmMedio;
	}

}
