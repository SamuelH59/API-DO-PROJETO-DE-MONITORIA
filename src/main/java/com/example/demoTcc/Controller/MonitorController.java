package com.example.demoTcc.Controller;

import com.example.demoTcc.Model.Monitor;
import com.example.demoTcc.Model.Aluno;
import com.example.demoTcc.Repository.AlunoRepository;
import com.example.demoTcc.Repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/apiMonitor")
public class MonitorController {

    @Autowired
    MonitorRepository MoniRepo;

    @GetMapping("/Monitores")
    public List<Monitor> listarMonitores(){

        return MoniRepo.findAll();
    }

    @PostMapping("/inserirMonitor")
    public List<Monitor> inserirMonitor(@RequestBody Monitor moni) {
        MoniRepo.save(moni);
        return listarMonitores();
    }

    @GetMapping("/RA/{RA}")
    public Optional<Monitor> buscarMonitorPorRA (@PathVariable("RA") int RA){

        return MoniRepo.findById(RA);
    }


}
