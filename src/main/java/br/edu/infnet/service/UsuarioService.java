
package br.edu.infnet.service;

import br.edu.infnet.domain.Usuario;
import br.edu.infnet.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public void Incluir(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    
    public void Excluir(Usuario usuario){
        usuarioRepository.delete(usuario);
    }
    
}
