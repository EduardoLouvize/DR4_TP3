
package br.edu.infnet.repository;

import br.edu.infnet.domain.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
    
    Usuario findByEmail(String email);
    
    Usuario findById (int idUsuario);
    
    List<Usuario> findByNomeContainingIgnoreCase (String nome);
    
       
    
}
