package br.com.lucas.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lucas.customException.DatesExpection;
import br.com.lucas.customException.FormatoEmailIncorretoExpection;
import br.com.lucas.customException.ListaVaziaException;
import br.com.lucas.customException.CandidatoNaoEncontradoException;
import br.com.lucas.customException.ValidacaoCPFException;
import br.com.lucas.entitys.CandidatoJson;
import br.com.lucas.entitys.dto.ImcPorFaixaDeIdadeDeDezAnosDto;
import br.com.lucas.entitys.dto.PercentualDeObesosPorSexoDto;
import br.com.lucas.entitys.dto.QuantidadePorEstadoDto;
import br.com.lucas.entitys.dto.TipoSanguineoMediaDeIdadeDto;
import br.com.lucas.entitys.dto.TipoSanguineoQuantidadeDoadorDto;
import br.com.lucas.repository.CandidatoJsonRepository;
import br.com.lucas.utils.ValidaDocumentos;
import br.com.lucas.utils.ValidadorEmail;

@Service
public class CandidatoJsonService {

	@Autowired
	CandidatoJsonRepository candidatoJsonRepository;

	public CandidatoJson salvar(CandidatoJson candidato) {
		return candidatoJsonRepository.save(candidato);
	}

	public List<TipoSanguineoMediaDeIdadeDto> retornaTipoSanguineoMediaDeIdade() {
		return candidatoJsonRepository.tipoSanguineoMediaDeIdade();
	}

	public List<TipoSanguineoQuantidadeDoadorDto> quantidadeDeDoadoresParaCadaTipoSanguineoReceptor() throws ListaVaziaException {

		List<CandidatoJson> listaCandidatosDoadores = candidatoJsonRepository.findAll();
		
		if(listaCandidatosDoadores.isEmpty()) throw new ListaVaziaException("Não foram encontrados candidatos");
		
		List<TipoSanguineoQuantidadeDoadorDto> listaDoadorPorTipoSanguineo = new ArrayList();
		
		List<CandidatoJson> listaCandidatosDoadoresFiltrada = listaCandidatosDoadores.stream().filter(e -> e.getPeso() > 50 && e.getIdade()  > 15 && e.getIdade() < 70).collect(Collectors.toList());

		List<CandidatoJson> doadoresParaTipoAmais = listaCandidatosDoadoresFiltrada.stream()
				.filter(e -> e.getTipo_sanguineo().equals("A+") || e.getTipo_sanguineo().equals("A-")
						|| e.getTipo_sanguineo().equals("O+") || e.getTipo_sanguineo().equals("O-"))
				.collect(Collectors.toList());
		listaDoadorPorTipoSanguineo.add(new TipoSanguineoQuantidadeDoadorDto("A+", doadoresParaTipoAmais.size()));
		
		
		List<CandidatoJson> doadoresParaTipoAMenos = listaCandidatosDoadoresFiltrada.stream()
				.filter(e -> e.getTipo_sanguineo().equals("A+") || e.getTipo_sanguineo().equals("O-"))
				.collect(Collectors.toList());
		listaDoadorPorTipoSanguineo.add(new TipoSanguineoQuantidadeDoadorDto("A-", doadoresParaTipoAMenos.size()));

		List<CandidatoJson> doadoresParaTipoBMais = listaCandidatosDoadoresFiltrada.stream()
				.filter(e -> e.getTipo_sanguineo().equals("B+") || e.getTipo_sanguineo().equals("B-")
						|| e.getTipo_sanguineo().equals("O+") || e.getTipo_sanguineo().equals("O-"))
				.collect(Collectors.toList());
		listaDoadorPorTipoSanguineo.add(new TipoSanguineoQuantidadeDoadorDto("B+", doadoresParaTipoBMais.size()));

		List<CandidatoJson> doadoresParaTipoBMenos = listaCandidatosDoadoresFiltrada.stream()
				.filter(e -> e.getTipo_sanguineo().equals("B-") || e.getTipo_sanguineo().equals("O-")
						|| e.getTipo_sanguineo().equals("O+") || e.getTipo_sanguineo().equals("O-"))
				.collect(Collectors.toList());
		listaDoadorPorTipoSanguineo.add(new TipoSanguineoQuantidadeDoadorDto("B-", doadoresParaTipoBMenos.size()));

		List<CandidatoJson> doadoresParaTipoABMais = listaCandidatosDoadoresFiltrada.stream()
				.filter(e -> e.getTipo_sanguineo().equals("A+") || e.getTipo_sanguineo().equals("B+")
						|| e.getTipo_sanguineo().equals("O+") || e.getTipo_sanguineo().equals("AB+")
						|| e.getTipo_sanguineo().equals("A-") || e.getTipo_sanguineo().equals("B-")
						|| e.getTipo_sanguineo().equals("O-") || e.getTipo_sanguineo().equals("AB-"))
				.collect(Collectors.toList());
		listaDoadorPorTipoSanguineo.add(new TipoSanguineoQuantidadeDoadorDto("AB+", doadoresParaTipoABMais.size()));

		List<CandidatoJson> doadoresParaTipoABMenos = listaCandidatosDoadoresFiltrada.stream()
				.filter(e -> e.getTipo_sanguineo().equals("A-") || e.getTipo_sanguineo().equals("B-")
						|| e.getTipo_sanguineo().equals("O-") || e.getTipo_sanguineo().equals("AB-"))
				.collect(Collectors.toList());
		listaDoadorPorTipoSanguineo.add(new TipoSanguineoQuantidadeDoadorDto("AB-", doadoresParaTipoABMenos.size()));

		List<CandidatoJson> doadoresParaTipoOmais = listaCandidatosDoadoresFiltrada.stream()
				.filter(e -> e.getTipo_sanguineo().equals("O+") || e.getTipo_sanguineo().equals("O-"))
				.collect(Collectors.toList());
		
		listaDoadorPorTipoSanguineo.add(new TipoSanguineoQuantidadeDoadorDto("O+", doadoresParaTipoOmais.size()));

		List<CandidatoJson> doadoresParaTipoOmenos = listaCandidatosDoadoresFiltrada.stream()
				.filter(e -> e.getTipo_sanguineo().equals("O-")).collect(Collectors.toList());
		
		listaDoadorPorTipoSanguineo.add(new TipoSanguineoQuantidadeDoadorDto("O-", doadoresParaTipoOmenos.size()));

		return listaDoadorPorTipoSanguineo;
	}

	public List<PercentualDeObesosPorSexoDto> retornaPercentoalDeHomenseMulheresObesos() throws ListaVaziaException {

		List<CandidatoJson> listaCandidadosDB = candidatoJsonRepository.findAll();
		if (listaCandidadosDB.isEmpty())
			throw new ListaVaziaException("Não foi encontrado nenhum candidato");
		List<PercentualDeObesosPorSexoDto> listaPercentualDeObesosPorSexo = new ArrayList();
		List<CandidatoJson> listaFiltradaHomens = listaCandidadosDB.stream()
				.filter(c -> c.getSexo().equals("Masculino")).collect(Collectors.toList());
		if (listaFiltradaHomens.size() == 0)
			throw new ArithmeticException(
					"Não é possivel continuar, pois não foram encontrados homens na base de dados");

		List<CandidatoJson> listaFiltradaHomensAcimaDe30IMC = listaFiltradaHomens.stream()
				.filter(e -> (e.getPeso() / (e.getAltura() * e.getAltura())) > 30).collect(Collectors.toList());

		double percentualHomens = ((double) listaFiltradaHomensAcimaDe30IMC.size() / listaFiltradaHomens.size()) * 100;
		PercentualDeObesosPorSexoDto homens = new PercentualDeObesosPorSexoDto("Masculino", Math.round(percentualHomens));
		listaPercentualDeObesosPorSexo.add(homens);

		List<CandidatoJson> listaFiltradaMulheres = listaCandidadosDB.stream()
				.filter(c -> c.getSexo().equals("Feminino")).collect(Collectors.toList());
		if (listaFiltradaMulheres.size() == 0)
			throw new ArithmeticException(
					"Não é possivel continuar, pois não foram encontrados mulheres na base de dados");

		List<CandidatoJson> listaFiltradeMulheresAcimaDe30IMC = listaFiltradaMulheres.stream()
				.filter(e -> (e.getPeso() / (e.getAltura() * e.getAltura())) > 30).collect(Collectors.toList());
		double percentualMulheres = ((double) listaFiltradeMulheresAcimaDe30IMC.size() / listaFiltradaMulheres.size())
				* 100;
		PercentualDeObesosPorSexoDto mulheres = new PercentualDeObesosPorSexoDto("Feminino", Math.round(percentualMulheres));
		listaPercentualDeObesosPorSexo.add(mulheres);

		return listaPercentualDeObesosPorSexo;

	}

	public List<ImcPorFaixaDeIdadeDeDezAnosDto> retornaImcMedioPorFaixaDeIdadeDeDezAnos() throws ListaVaziaException {

		List<CandidatoJson> listaCandidadosDB = candidatoJsonRepository.findAll();

		if (listaCandidadosDB.isEmpty())
			throw new ListaVaziaException("Não foi encontrado nenhum candidato");

		List<ImcPorFaixaDeIdadeDeDezAnosDto> listaIcmPorFaixa = new ArrayList();

		int idadeInicial = 0;
		int idadeFinal = 11;

		Integer idadeDoMaisVelho = listaCandidadosDB.stream().max(Comparator.comparingInt(CandidatoJson::getIdade))
				.get().getIdade();

		int loop = Math.round(idadeDoMaisVelho / 10) + 1;

		for (int i = 0; i < loop + 1; i++) {

			int vInicial = idadeInicial;
			int vFinal = idadeFinal;

			List<CandidatoJson> listaEntreFaixaDeIdadeDoLoop = listaCandidadosDB.stream()
					.filter(p -> p.getIdade() >= vInicial && p.getIdade() < vFinal).collect(Collectors.toList());

			ImcPorFaixaDeIdadeDeDezAnosDto imcPorFaixaDeIdadeDeDezAnosDto = new ImcPorFaixaDeIdadeDeDezAnosDto();

			double valorMedioImc = listaEntreFaixaDeIdadeDoLoop.stream()
					.mapToDouble(e -> e.getPeso() / (e.getAltura() * e.getAltura())).sum();

			if (i == 0) {

				int idadeF = idadeFinal - 1;
				imcPorFaixaDeIdadeDeDezAnosDto.setFaixaDeIdade(idadeInicial + " a " + idadeF + " anos");
				imcPorFaixaDeIdadeDeDezAnosDto
						.setIcmMedio(Math.round(valorMedioImc / listaEntreFaixaDeIdadeDoLoop.size()));
			} else {
				imcPorFaixaDeIdadeDeDezAnosDto.setFaixaDeIdade(idadeInicial + " a " + idadeFinal + " anos");
				imcPorFaixaDeIdadeDeDezAnosDto
						.setIcmMedio(Math.round(valorMedioImc / listaEntreFaixaDeIdadeDoLoop.size()));
			}

			listaIcmPorFaixa.add(imcPorFaixaDeIdadeDeDezAnosDto);

			idadeInicial += 10;
			idadeFinal += 10;

		}

		return listaIcmPorFaixa;

	}

	@Transactional
	public void deletaCandidato(Integer idcandidato) {
		candidatoJsonRepository.deleteById(idcandidato);
	}

	public Optional<CandidatoJson> findByIdcandidato(Integer id) {
		return candidatoJsonRepository.findById(id);
	}

	public Page<CandidatoJson> buscaTodosCandidatos(Pageable pageable) {
		return candidatoJsonRepository.findAll(pageable);
	}

	public void adicionarListaCandidatoJson(List<CandidatoJson> listaCandidato)
			throws ValidacaoCPFException, DatesExpection, ListaVaziaException, FormatoEmailIncorretoExpection {

		if (listaCandidato.isEmpty())
			throw new ListaVaziaException("Lista Vazia");

		for (CandidatoJson candidato : listaCandidato) {

			boolean cpfValido = ValidaDocumentos.isValidoCPF(candidato.getCpf());
			
//			ValidadorEmail validadorEmail = new ValidadorEmail();
//			boolean emailValidado = validadorEmail.valida(candidato.getEmail());
//			if(emailValidado == false)  throw new FormatoEmailIncorretoExpection("Formato do Email está inválido! " + candidato.getEmail());

			if (cpfValido == false)
				throw new ValidacaoCPFException("Cpf com formato inválido!");

			if (new Date(candidato.getData_nasc()).getTime() > new Date().getTime())
				throw new DatesExpection("Data de nascimento não pode ser uma data Futura!.");

			// trocar para exists
			CandidatoJson candidatoEncontradaNoBanco = candidatoJsonRepository.findByCpf(candidato.getCpf());
			if (candidatoEncontradaNoBanco != null) {
				if (candidatoEncontradaNoBanco.getId() != candidato.getId())
					throw new ValidacaoCPFException("Cpf já cadastrado para outra candidato.");
			}

			CandidatoJson ultimoCandidatoAdicionado = candidatoJsonRepository.findFirstByOrderByIdDesc();

			if (ultimoCandidatoAdicionado != null) {
				Integer idcandidato = ultimoCandidatoAdicionado.getId();
				candidato.setId(++idcandidato);
			} else {
				candidato.setId(1);
			}

			candidatoJsonRepository.save(candidato);

		}

	}

	public CandidatoJson atualizar(CandidatoJson candidato) throws CandidatoNaoEncontradoException {

		boolean cpfValido = ValidaDocumentos.isValidoCPF(candidato.getCpf());
		if (cpfValido == false)
			throw new ValidacaoCPFException("Cpf com formato inválido!");

		CandidatoJson candidatoEncontradaNoBanco = candidatoJsonRepository.findByCpf(candidato.getCpf());
		Optional<CandidatoJson> candidatoASerAtualizada = candidatoJsonRepository.findById(candidato.getId());
		if (candidatoEncontradaNoBanco != null) {
			if (candidatoEncontradaNoBanco.getId() != candidato.getId())
				throw new ValidacaoCPFException("Cpf já cadastrado para outro candidato, não é possivel atualizar!");
		}

		if (candidatoASerAtualizada == null)
			throw new CandidatoNaoEncontradoException("O Candidato fornecido não existe para atualizar!");

		return candidatoJsonRepository.save(candidato);

	}

	public List<QuantidadePorEstadoDto> retornaQuantidadePorEstado() {
		return candidatoJsonRepository.totalCanditosPorEstado();
	}

}
