package com.example.demoTcc.Repository;

import com.example.demoTcc.Model.Aluno;
import com.example.demoTcc.Model.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonitorRepository extends JpaRepository<Monitor, Integer> {

    public List<Monitor> findByNome (String nome);
    public List<Monitor> findByCurso (String curso);
}
