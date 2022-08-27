package com.projeto.app.controle;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.app.modelo.ProdutosModelo;
import com.projeto.app.repositorio.ProdutosRepositorio;

@RestController // define esse classe como restcontroler
                // assim ele vai buscar pelos GetMapping atras das urls do http
public class ProdutosControle {
    @Autowired
	ProdutosRepositorio rep;
    
    @GetMapping(path = "api/produtos") // Mapeia o HTTP do metodo
    public List<ProdutosModelo> buscar(){
         return rep.findAll();	
    }
    
    @GetMapping(path = "api/produtos/{codigo}")
    public ProdutosModelo buscaPorID(@PathVariable("codigo") Integer codigo ) {
		Optional<ProdutosModelo> cliente = rep.findById(codigo);
		return cliente.get();
    }
    
    @PostMapping(path = "api/produtos/salvar")
    public void salvar(@RequestBody ProdutosModelo cli) {
    	rep.save(cli);
    }
     
    @DeleteMapping(path = "api/produtos/deletar/{codigo}")
    public void deletar(@PathVariable("codigo") Integer codigo ) {
    	try {
		  rep.deleteById(codigo);	
		} catch (Exception e) {
		      System.out.print(e.getMessage());
		}
    }
    
    @RequestMapping(path = "api/produtos/altera", method = RequestMethod.PUT)
    public void altera(@RequestBody ProdutosModelo cli) {
    	try {
	       rep.save(cli);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
    }
    
}
