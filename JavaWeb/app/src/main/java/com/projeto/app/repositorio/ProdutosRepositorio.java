package com.projeto.app.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.app.modelo.ProdutosModelo;


public interface ProdutosRepositorio extends JpaRepository<ProdutosModelo,Integer>  {

}
