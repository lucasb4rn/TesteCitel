package br.com.lucas.xpto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lucas.entitys.CandidatoJson;
import br.com.lucas.repository.CandidatoJsonRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestaCondidatoJsonRepository {

	private Validator validator;
	
	@Autowired
	CandidatoJsonRepository candidatoJsonRepository;
	
	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	
	@Test
	public void deveCarregarUmCandidatoAoBuscarPeloId() {
		Optional<CandidatoJson> canditatoNumeroUm = candidatoJsonRepository.findById(1);
		CandidatoJson candidato = canditatoNumeroUm.get();
		assertNotNull(candidato);
	}
	
	@Test
	public void deveValidarCandidatoSemDarErro() {
		CandidatoJson candidato = new CandidatoJson(null, "Milena Analu Pires", "775.256.099-50", "44.084.541-5", "23/05/1964", "Feminino", "Isadora Marli", "Noah Severino César Pires","mmilenaanalupires@keffin.com.br", "39801-678", "Rua Kurt W. Rothe", 675, "Castro Pires",  "Teófilo Otoni",  "MG", "(33) 3611-4613", "(33) 98481-0191", 1.53, 80, "O-");
		Set<ConstraintViolation<CandidatoJson>> violations = validator.validate(candidato);
		assertTrue(violations.isEmpty());

	}
	
	@Test
	public void deveRetornaUmaListaDeCondidatosPaginadaComDoisElementos() {
		Pageable paging = PageRequest.of(0, 2);
		Page<CandidatoJson> paginado = candidatoJsonRepository.findAll(paging);
		assertEquals(2, paginado.getSize());
	}

	
	
	
	@Test
	public void deveValidarCampoNomeVazio() {
		CandidatoJson candidato = new CandidatoJson(null, "", "775.256.099-50", "44.084.541-5", "23/05/1964", "Feminino", "Isadora Marli", "Noah Severino César Pires","mmilenaanalupires@keffin.com.br", "39801-678", "Rua Kurt W. Rothe", 675, "Castro Pires",  "Teófilo Otoni",  "MG", "(33) 3611-4613", "(33) 98481-0191", 1.53, 80, "O-");
		Set<ConstraintViolation<CandidatoJson>> violations = validator.validate(candidato);
		assertFalse(violations.isEmpty());

	}
	
	
	
}
