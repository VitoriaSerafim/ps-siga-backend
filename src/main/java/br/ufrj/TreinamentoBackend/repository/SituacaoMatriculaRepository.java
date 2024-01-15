package br.ufrj.TreinamentoBackend.repository;

import br.ufrj.TreinamentoBackend.model.entity.SituacaoMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SituacaoMatriculaRepository extends JpaRepository<SituacaoMatricula, Long> {

    @Query("SELECT sm FROM SituacaoMatricula sm where sm.codigo = :codigo")
    Optional<SituacaoMatricula> findSituacaoMatriculaByCodigo(String codigo);

}
