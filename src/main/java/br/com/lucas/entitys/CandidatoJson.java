package br.com.lucas.entitys;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;


@Table(name = "pes_candidato")
@Entity
public class CandidatoJson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "Campo não pode ser vazio")
	@NotNull(message = "Campo não pode ser nulo!")
	private String nome;
	@NotEmpty(message = "Campo não pode ser vazio")
	@NotNull(message = "Campo não pode ser nulo!")
	@Column(unique=true)
	private String cpf;
	private String rg;
	@NotNull
	@JsonProperty("data_nasc")
	@Column(name="data_nascimento")
	private String dataNascimento;
	@NotNull
	private String sexo;
	@NotNull
	private String mae;
	private String pai;
	private String email;
	@NotNull
	private String cep;
	@NotNull
	private String endereco;
	@NotNull
	private int numero;
	@NotNull
	private String bairro;
	@NotNull
	private String cidade;
	@NotNull
	private String estado;
	private String telefone_fixo;
	@NotNull
	private String celular;
	@NotNull
	private double altura;
	@NotNull
	private int peso;
	@NotNull
	@JsonProperty("tipo_sanguineo")
	@Column(name="tipo_sanguineo")
	private String tipoSanguineo;
	
	
	public CandidatoJson() {}

	public CandidatoJson(Integer id, String nome, String cpf, String rg, String data_nasc, String sexo, String mae,
			String pai, String email, String cep, String endereco, int numero, String bairro, String cidade,
			String estado, String telefone_fixo, String celular, double altura, int peso, String tipo_sanguineo) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = data_nasc;
		this.sexo = sexo;
		this.mae = mae;
		this.pai = pai;
		this.email = email;
		this.cep = cep;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.telefone_fixo = telefone_fixo;
		this.celular = celular;
		this.altura = altura;
		this.peso = peso;
		this.tipoSanguineo = tipo_sanguineo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getData_nasc() {
		return dataNascimento;
	}

	public void setData_nasc(String data_nasc) {
		this.dataNascimento = data_nasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefone_fixo() {
		return telefone_fixo;
	}

	public void setTelefone_fixo(String telefone_fixo) {
		this.telefone_fixo = telefone_fixo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getTipo_sanguineo() {
		return tipoSanguineo;
	}

	public void setTipo_sanguineo(String tipo_sanguineo) {
		this.tipoSanguineo = tipo_sanguineo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Integer getIdade() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(new Date(this.dataNascimento));
		Calendar dataAtual = Calendar.getInstance();

		Integer diferencaMes = dataAtual.get(Calendar.MONTH) - dataNascimento.get(Calendar.MONTH);
		Integer diferencaDia = dataAtual.get(Calendar.DAY_OF_MONTH) - dataNascimento.get(Calendar.DAY_OF_MONTH);
		Integer idade = (dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR));

		if (diferencaMes < 0 || (diferencaMes == 0 && diferencaDia < 0)) {
			idade--;
		}

		return idade;
	}

}