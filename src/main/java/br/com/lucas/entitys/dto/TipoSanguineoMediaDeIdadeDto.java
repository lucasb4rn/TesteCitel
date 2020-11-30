package br.com.lucas.entitys.dto;

public class TipoSanguineoMediaDeIdadeDto {
	
	
	
	private String tipo_sanguineo;
	private double media;
	
	public TipoSanguineoMediaDeIdadeDto() {}
	
	public TipoSanguineoMediaDeIdadeDto(String tipo_sanguineo, double media) {
		this.tipo_sanguineo = tipo_sanguineo;
		this.media = media;
	}
	public String getTipo_sanguineo() {
		return tipo_sanguineo;
	}
	public void setTipo_sanguineo(String tipo_sanguineo) {
		this.tipo_sanguineo = tipo_sanguineo;
	}
	public double getMedia() {
		return media;
	}
	public void setMedia(double media) {
		this.media = media;
	}
	
	
	
	
	

}
