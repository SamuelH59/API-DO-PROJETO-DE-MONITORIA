package com.example.demoTcc.Repository;

import com.example.demoTcc.Model.Monitoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonitoriaRepository extends JpaRepository <Monitoria, Integer> {
     List<Monitoria> findByEmailAluno(String emailAluno);

     List<Monitoria> findByEmailMonitor(String emailMonitor);
}
