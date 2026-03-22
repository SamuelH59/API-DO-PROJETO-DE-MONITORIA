package com.example.demoTcc.Controller;


import com.example.demoTcc.Model.Aluno;
import com.example.demoTcc.Model.Monitor;
import com.example.demoTcc.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/apiAluno")
public class AlunoController {

    @Autowired
    AlunoRepository AluRepo;

    @GetMapping("/Alunos")
    public List<Aluno> listarAluno(){

        return AluRepo.findAll();
    }

    @PostMapping("/inserirCadastro")
    public List<Aluno> inserirNomeAlu(@RequestBody Aluno alu) {
        AluRepo.save(alu);
        return listarAluno();
    }

    @GetMapping("/RA/{RA}")
    public Optional<Aluno> buscarAlunoPorRA (@PathVariable("RA") int RA){

        return AluRepo.findById(RA);
    }



}
