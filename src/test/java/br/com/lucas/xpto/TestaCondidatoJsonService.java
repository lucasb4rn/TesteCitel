package br.com.lucas.xpto;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lucas.customException.DatesExpection;
import br.com.lucas.customException.FormatoEmailIncorretoExpection;
import br.com.lucas.customException.ListaVaziaException;
import br.com.lucas.customException.ValidacaoCPFException;
import br.com.lucas.entitys.CandidatoJson;
import br.com.lucas.services.CandidatoJsonService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestaCondidatoJsonService {

	@Autowired
	CandidatoJsonService candidatoJsonService;

	@Test
	public void devoRetornarErroDeFormatoDeEmail() throws ValidacaoCPFException, DatesExpection, ListaVaziaException, FormatoEmailIncorretoExpection {
		
		List<CandidatoJson> lista = new ArrayList();
		CandidatoJson contato = new CandidatoJson(null, "Milena Analu Pires", "775.256.099-50", "44.084.541-5", "23/05/1964", "Feminino", "Isadora Marli", "Noah Severino César Pires","mmilenaanalupireskeffin.com.br", "39801-678", "Rua Kurt W. Rothe", 675, "Castro Pires",  "Teófilo Otoni",  "MG", "(33) 3611-4613", "(33) 98481-0191", 1.53, 80, "O-");
		lista.add(contato);
		try {
			candidatoJsonService.adicionarListaCandidatoJson(lista);
		} catch (FormatoEmailIncorretoExpection e) {
			assertTrue(true);
		}

	}
	
	@Test
	public void devoRetornarErroDeValidacaoDeCPF() throws ValidacaoCPFException, DatesExpection, ListaVaziaException, FormatoEmailIncorretoExpection {
		
		List<CandidatoJson> lista = new ArrayList();
		CandidatoJson contato = new CandidatoJson(null, "Milena Analu Pires", "775.256.099-99", "44.084.541-5", "23/05/1964", "Feminino", "Isadora Marli", "Noah Severino César Pires","mmilenaanalupi@reskeffin.com.br", "39801-678", "Rua Kurt W. Rothe", 675, "Castro Pires",  "Teófilo Otoni",  "MG", "(33) 3611-4613", "(33) 98481-0191", 1.53, 80, "O-");
		lista.add(contato);
		try {
			candidatoJsonService.adicionarListaCandidatoJson(lista);
		} catch (ValidacaoCPFException e) {
			assertTrue(true);
		}

	}
	
	@Test
	public void devoRetornarErroPorDataNascimentoMaiorQueAtual() throws ValidacaoCPFException, DatesExpection, ListaVaziaException, FormatoEmailIncorretoExpection {
		
		List<CandidatoJson> lista = new ArrayList();
		CandidatoJson contato = new CandidatoJson(null, "Milena Analu Pires", "775.256.099-50", "44.084.541-5", "23/05/2021", "Feminino", "Isadora Marli", "Noah Severino César Pires","mmilenaanalupi@reskeffin.com.br", "39801-678", "Rua Kurt W. Rothe", 675, "Castro Pires",  "Teófilo Otoni",  "MG", "(33) 3611-4613", "(33) 98481-0191", 1.53, 80, "O-");
		lista.add(contato);
		try {
			candidatoJsonService.adicionarListaCandidatoJson(lista);
		} catch (DatesExpection e) {
			assertTrue(true);
		}

	}
	
	
	
	
	

}
