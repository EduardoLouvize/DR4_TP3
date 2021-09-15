
package br.edu.infnet;

import br.edu.infnet.domain.Endereco;
import br.edu.infnet.domain.Usuario;
import br.edu.infnet.repository.UsuarioRepository;
import br.edu.infnet.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner{
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ViaCepService viaCepService;

   
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Usuario usuario = new Usuario();
        
        Endereco endereco;
        endereco = viaCepService.BuscarEnderecoPor("03607000");
             
        
        
        
        usuario.setNome("Bilbo Bolseiro");
        
        usuario.setEmail("bilbobolseiro@terramedia.com");
        usuario.setTelefone("23569874");        
        usuario.setCep("03607000");
        
        usuario.setEndereco(endereco);
        
       usuarioRepository.save(usuario);
        
        
    }
    
}
