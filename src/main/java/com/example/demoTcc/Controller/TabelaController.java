package com.example.demoTcc.Controller;


import com.example.demoTcc.Model.Tabela;
import com.example.demoTcc.Repository.TabelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/apiTabela")
public class TabelaController {
    @Autowired
    private TabelaRepository TabeRepo;

    @GetMapping("/Tabelas")
    public List<Tabela> BuscarInformacoes(){

        return TabeRepo.findAll();
    }

    @GetMapping("/cod/{cod}")
    public Optional<Tabela> MostrarID (@PathVariable int cod){

        return TabeRepo.findById(cod);
    }




}
