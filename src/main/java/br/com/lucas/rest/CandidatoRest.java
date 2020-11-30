package br.com.lucas.rest;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.customException.CandidatoNaoEncontradoException;
import br.com.lucas.customException.DatesExpection;
import br.com.lucas.customException.FormatoEmailIncorretoExpection;
import br.com.lucas.customException.ListaVaziaException;
import br.com.lucas.customException.ValidacaoCPFException;
import br.com.lucas.entitys.CandidatoJson;
import br.com.lucas.entitys.dto.ImcPorFaixaDeIdadeDeDezAnosDto;
import br.com.lucas.entitys.dto.PercentualDeObesosPorSexoDto;
import br.com.lucas.entitys.dto.QuantidadePorEstadoDto;
import br.com.lucas.entitys.dto.TipoSanguineoMediaDeIdadeDto;
import br.com.lucas.entitys.dto.TipoSanguineoQuantidadeDoadorDto;
import br.com.lucas.repository.CandidatoJsonRepository;
import br.com.lucas.services.CandidatoJsonService;

@RestController
public class CandidatoRest {
	
	@Autowired
	CandidatoJsonService candidatoJsonService;
	
	
	@Transactional(rollbackOn = { ValidacaoCPFException.class, DatesExpection.class, ListaVaziaException.class, FormatoEmailIncorretoExpection.class })
	@RequestMapping(value = "candidato/adicionarListaCandidatos", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void addCandidatoJson(@Valid @RequestBody List<CandidatoJson> listaCandidatosJson) throws ValidacaoCPFException, DatesExpection, ListaVaziaException, FormatoEmailIncorretoExpection {
		candidatoJsonService.adicionarListaCandidatoJson(listaCandidatosJson);
	}

	
	@RequestMapping(value = "candidato/quantidadePorEstado", method = RequestMethod.GET)
	public List<QuantidadePorEstadoDto> retornaQuantidadePorEstado() {
		return candidatoJsonService.retornaQuantidadePorEstado();
	}

	
	@RequestMapping(value = "candidato/imcMedioPorFaixaDeIdade", method = RequestMethod.GET)
	public List<ImcPorFaixaDeIdadeDeDezAnosDto> retornaImcMedioPorFaixaDeIdade() throws ListaVaziaException {
		return candidatoJsonService.retornaImcMedioPorFaixaDeIdadeDeDezAnos();
	}

	
	@RequestMapping(value = "candidato/mediaIdadePorTipoSanguineo", method = RequestMethod.GET)
	public List<TipoSanguineoMediaDeIdadeDto> retornaTipoSanguineoMediaDeIdade() {
		return candidatoJsonService.retornaTipoSanguineoMediaDeIdade();
	}

	
	@RequestMapping(value = "candidato/percentualObesos", method = RequestMethod.GET)
	public List<PercentualDeObesosPorSexoDto> retornaPercentoalDeHomenseMulheresObesos() throws ListaVaziaException {
		return candidatoJsonService.retornaPercentoalDeHomenseMulheresObesos();
	}

	
	@RequestMapping(value = "candidato/quantidadeDoadoresPorTipoSanguineo", method = RequestMethod.GET)
	public List<TipoSanguineoQuantidadeDoadorDto> quantidadeDeDoadoresParaCadaTipoSanguineoReceptor() throws ListaVaziaException {
		return candidatoJsonService.quantidadeDeDoadoresParaCadaTipoSanguineoReceptor();
	}
	
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "candidato/atualizar", method = RequestMethod.PUT)
	@Transactional
	public void atualizar(@Valid @RequestBody CandidatoJson candidatoJson, CandidatoJsonRepository repository) throws CandidatoNaoEncontradoException {
		candidatoJsonService.atualizar(candidatoJson);
	}
	
	@RequestMapping(value = "candidato/deletaCandidato/{idCandidato}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deletaPessoa(@PathVariable(value = "idCandidato") Integer idCandidato) {
		candidatoJsonService.deletaCandidato(idCandidato);
	}
	
	
	

}
