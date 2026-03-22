package com.example.demoTcc.Controller;

import com.example.demoTcc.Model.Candidato;
import com.example.demoTcc.Repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/apiCandidato")
public class CandidatoController {

    @Autowired
    private CandidatoRepository candRepo;

    // Caminho absoluto para evitar problemas de diretórios no Tomcat
    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/uploads/boletins/";

    @GetMapping("/candidatos")
    public List<Candidato> listarCandidatos() {
        return candRepo.findAll();
    }

    @PostMapping("/inserirCandidato")
    public List<Candidato> inserirCandidato(
            @RequestPart("candidato") Candidato candidato,
            @RequestPart("file") MultipartFile file) {

        try {
            // Define o arquivo destino
            String filePath = UPLOAD_DIR + file.getOriginalFilename();
            File dest = new File(filePath);

            // Cria todos os diretórios pai, se não existirem
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            // Salva o arquivo
            file.transferTo(dest);

            // Define o caminho do boletim no candidato
            candidato.setBoletim(filePath);

            // Salva no banco
            candRepo.save(candidato);

        } catch (IOException e) {
            e.printStackTrace(); // Para debug rápido
        }

        // Retorna a lista atualizada
        return listarCandidatos();
    }

    @GetMapping("/RA/{RA}")
    public Optional<Candidato> buscarCandidatoPorRA(@PathVariable("RA") int RA) {
        return candRepo.findById(RA);
    }
}
