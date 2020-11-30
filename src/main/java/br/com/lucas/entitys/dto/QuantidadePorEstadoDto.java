package br.com.lucas.entitys.dto;

public class QuantidadePorEstadoDto {

	private String nomeEstado;
	private Long quantidade;


	public QuantidadePorEstadoDto() {}
	
	public QuantidadePorEstadoDto(String nomeEstado, Long quantidade) {
		this.nomeEstado = nomeEstado;
		this.quantidade = quantidade;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}
