package com.example.demoTcc.Controller;


import com.example.demoTcc.Model.Monitoria;
import com.example.demoTcc.Repository.MonitoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/apiMonitoria")
public class MonitoriaController {

    @Autowired
    MonitoriaRepository ToriaRepo;

    @GetMapping("/Monitorias")
    public List<Monitoria> listarMonitorias(){

        return ToriaRepo.findAll();
    }

    @PostMapping("/inserirMonitoria")
    public List<Monitoria> inserirNomeAlu(@RequestBody Monitoria monit) {

        ToriaRepo.save(monit);
        return listarMonitorias();
    }

    @GetMapping("/Email/{emailAluno}")
    public List<Monitoria> getMonitoriasPorEmail(@PathVariable String emailAluno) {
        return ToriaRepo.findByEmailAluno(emailAluno);
    }

    @GetMapping("/EmailMoni/{emailMonitor}")
    public List<Monitoria> getMonitoriasPorEmailMoni(@PathVariable String emailMonitor) {
        return ToriaRepo.findByEmailMonitor(emailMonitor);
    }

    @PatchMapping("/concluirMonitoria/{id}")
    public Monitoria concluirMonitoria(@PathVariable int id) {
        return ToriaRepo.findById(id)
                .map(monitoria -> {
                    monitoria.setSituacao("CONCLUÍDA");
                    // Você pode querer adicionar a data de conclusão aqui, se houver um campo no Model
                    return ToriaRepo.save(monitoria);
                })
                .orElseThrow(() -> new RuntimeException("Monitoria não encontrada com o ID: " + id));
    }
}
