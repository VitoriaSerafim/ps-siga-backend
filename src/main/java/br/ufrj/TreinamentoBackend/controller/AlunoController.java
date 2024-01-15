package br.ufrj.TreinamentoBackend.controller;

import br.ufrj.TreinamentoBackend.model.entity.Aluno;
import br.ufrj.TreinamentoBackend.repository.AlunoRepository;
import br.ufrj.TreinamentoBackend.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoRepository alunoRepository;
    private final AlunoService alunoService;

    public AlunoController(AlunoRepository alunoRepository, AlunoService alunoService) {
        this.alunoRepository = alunoRepository;
        this.alunoService = alunoService;
    }

    @GetMapping()
    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    @GetMapping("/matricula/{matricula}")
    public Aluno findByMatricula(@PathVariable String matricula){
        return alunoService.getAlunoByMatricula(matricula);
    }

    @PostMapping
    public Aluno create(@RequestBody Aluno aluno) {
        return alunoService.create(aluno);
    }

    @PutMapping("/{matricula}")
    public Aluno update(@PathVariable String matricula, @RequestBody Aluno aluno){
        return alunoService.update(matricula,aluno);
    }

    @DeleteMapping("/{matricula}")
    public void delete(@PathVariable String matricula){
        alunoService.delete(matricula);
    }


}
