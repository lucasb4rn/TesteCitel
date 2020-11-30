package br.com.lucas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.lucas.entitys.CandidatoJson;
import br.com.lucas.entitys.dto.QuantidadePorEstadoDto;
import br.com.lucas.entitys.dto.TipoSanguineoMediaDeIdadeDto;
import br.com.lucas.entitys.dto.TipoSanguineoQuantidadeDoadorDto;


@Repository
public interface CandidatoJsonRepository extends JpaRepository<CandidatoJson, Integer> {

	@Query("Select new br.com.lucas.entitys.dto.QuantidadePorEstadoDto (c.estado, count(*) as quantidade) from CandidatoJson c group by c.estado")
	List<QuantidadePorEstadoDto> totalCanditosPorEstado();
	
	@Query("Select new br.com.lucas.entitys.dto.TipoSanguineoMediaDeIdadeDto (c.tipoSanguineo, round(AVG(c.idade))) from CandidatoJson c group by c.tipoSanguineo")
	List<TipoSanguineoMediaDeIdadeDto> tipoSanguineoMediaDeIdade();
	
	CandidatoJson findByCpf(String cpf);
	CandidatoJson findByNome(String nome);
	Optional<CandidatoJson> findById(Integer id);
	CandidatoJson findFirstByOrderByIdDesc();
	void deleteById(Integer id);
	Page<CandidatoJson> findAll(Pageable pageable);
	CandidatoJson save(CandidatoJson CandidatoJson);

	
}
