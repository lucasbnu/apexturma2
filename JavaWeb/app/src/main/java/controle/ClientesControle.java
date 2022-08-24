package controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import modelo.ClientesModelo;
import repositorio.ClientesRepositorio;

@RestController
public class ClientesControle {
    @Autowired
	ClientesRepositorio rep;
    
    @GetMapping(path = "api/clientes")
    public List<ClientesModelo> buscar(){
         return rep.findAll();	
    }
    
}
