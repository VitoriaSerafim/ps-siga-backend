package br.ufrj.TreinamentoBackend.service;

import br.ufrj.TreinamentoBackend.model.entity.Aluno;
import br.ufrj.TreinamentoBackend.model.entity.SituacaoMatricula;
import br.ufrj.TreinamentoBackend.repository.AlunoRepository;
import br.ufrj.TreinamentoBackend.repository.SituacaoMatriculaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final SituacaoMatriculaRepository situacaoMatriculaRepository;

    public AlunoService(AlunoRepository alunoRepository, SituacaoMatriculaRepository situacaoMatriculaRepository) {
        this.alunoRepository = alunoRepository;
        this.situacaoMatriculaRepository = situacaoMatriculaRepository;
    }

    public Aluno getAlunoByMatricula(String matricula) {
        Optional<Aluno> optAluno = alunoRepository.findAlunoByMatricula(matricula);

        if (optAluno.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado.");
        }

        return optAluno.get();
    }

    @Transactional
    public void delete(String matricula) {
        Aluno aluno = alunoRepository.findAlunoByMatricula(matricula)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado."));

        alunoRepository.delete(aluno);
    }

    public Aluno create(Aluno aluno) {
        if (alunoRepository.findAlunoByMatricula(aluno.getMatricula()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Aluno já existe.");
        }

        SituacaoMatricula situacaoMatricula = situacaoMatriculaRepository
                .findSituacaoMatriculaByCodigo(SituacaoMatricula.ATIVO)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Situação de Matrícula não existe."));

        Aluno novoAluno = new Aluno();
        novoAluno.setNome(aluno.getNome());
        novoAluno.setMatricula(aluno.getMatricula());
        novoAluno.setHobbies(aluno.getHobbies());
        novoAluno.setSituacaoMatricula(situacaoMatricula);

        return alunoRepository.save(novoAluno);
    }

    public Aluno update(String matricula, Aluno newAluno) {

        Aluno oldAluno = getAlunoByMatricula(matricula);

        oldAluno.setNome(newAluno.getNome());
        if (newAluno.getMatricula().equals(oldAluno.getMatricula())){
            oldAluno.setMatricula(newAluno.getMatricula());
        }else{
            if (alunoRepository.findAlunoByMatricula(newAluno.getMatricula()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Aluno já existe com essa matricula.");
            }
            oldAluno.setMatricula(newAluno.getMatricula());
        }
        oldAluno.setHobbies(newAluno.getHobbies());
        oldAluno.setSituacaoMatricula(newAluno.getSituacaoMatricula());

        return alunoRepository.save(oldAluno);
    }
}
