package br.com.lucas.entitys.dto;

public class TipoSanguineoQuantidadeDoadorDto {

	private String tipo_sanguineo;
	private int quantidade;

	public TipoSanguineoQuantidadeDoadorDto(String tipo_sanguineo, int quantidade) {
		this.tipo_sanguineo = tipo_sanguineo;
		this.quantidade = quantidade;
	}

	public String getTipo_sanguineo() {
		return tipo_sanguineo;
	}

	public void setTipo_sanguineo(String tipo_sanguineo) {
		this.tipo_sanguineo = tipo_sanguineo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
