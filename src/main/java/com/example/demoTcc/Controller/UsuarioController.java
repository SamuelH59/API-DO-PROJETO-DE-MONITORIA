package com.example.demoTcc.Controller;


import com.example.demoTcc.Model.Aluno;
import com.example.demoTcc.Model.Usuario;
import com.example.demoTcc.Repository.AlunoRepository;
import com.example.demoTcc.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/apiUsuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository UsuRepo;

    @GetMapping("/Usuarios")
    public List<Usuario> listarUsuarios(){

        return UsuRepo.findAll();
    }
}
