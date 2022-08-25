package com.projeto.app.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.app.modelo.ClientesModelo;

public interface ClientesRepositorio extends JpaRepository<ClientesModelo,Integer> {

}
