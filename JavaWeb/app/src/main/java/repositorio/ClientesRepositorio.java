package repositorio;
import org.springframework.data.jpa.repository.JpaRepository;

import modelo.ClientesModelo;

public interface ClientesRepositorio extends JpaRepository<ClientesModelo,Integer> {

}
