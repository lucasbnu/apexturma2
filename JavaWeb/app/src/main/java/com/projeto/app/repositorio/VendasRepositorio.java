package com.projeto.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.app.modelo.VendasModelo;



public interface VendasRepositorio extends JpaRepository<VendasModelo,Integer>  {

}
