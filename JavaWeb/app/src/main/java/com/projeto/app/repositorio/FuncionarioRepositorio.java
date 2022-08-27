package com.projeto.app.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.app.modelo.FuncionarioModelo;


public interface FuncionarioRepositorio extends JpaRepository<FuncionarioModelo,Integer> {

}
