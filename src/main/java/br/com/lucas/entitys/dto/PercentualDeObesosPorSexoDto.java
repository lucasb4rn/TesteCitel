package br.com.lucas.entitys.dto;

public class PercentualDeObesosPorSexoDto {

	private String sexo;
	private double percentual;

	public PercentualDeObesosPorSexoDto() {
	}

	public PercentualDeObesosPorSexoDto(String sexo, double percentual) {
		this.sexo = sexo;
		this.percentual = percentual;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}

}
