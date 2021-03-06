package br.edu.infnet.domain.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.infnet.domain.Endereco;
import br.edu.infnet.domain.Usuario;
import br.edu.infnet.repository.UsuarioRepository;
import br.edu.infnet.service.ViaCepService;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping(path = {"/usuarios"})
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    ViaCepService viaCepService;

    @GetMapping
    public ResponseEntity ObterUsuarios() {
        ResponseEntity retorno = ResponseEntity.notFound().build();
        List<Usuario> lista = (List<Usuario>) usuarioRepository.findAll();
        if (!lista.isEmpty()) {
            retorno = ResponseEntity.ok().body(lista);
        }
        return retorno;
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity ObterPorId(@PathVariable int id) {
        ResponseEntity retorno = ResponseEntity.notFound().build();
        Usuario usuario = this.findById(id);
        if (usuario != null) {
            retorno = ResponseEntity.ok().body(usuario);
        }
        return retorno;
    }

    private Usuario findById(int id) {
        Usuario retorno = null;
        try {
            retorno = usuarioRepository.findById(id);
            usuarioRepository.findById(id);
        } catch (Exception e) {

        }

        return retorno;
    }

    @GetMapping(path = {"/email/{email}"})
    public ResponseEntity ListarPorEmail(@PathVariable String email) {

        ResponseEntity retorno = ResponseEntity.notFound().build();

        try {
           
            Usuario resultado = usuarioRepository.findByEmail(email);
            
            if (resultado != null) {
                retorno = ResponseEntity.ok().body(resultado);
            }
        } catch (Exception e) {
        }

        return retorno;
    }

    @GetMapping(path = {"/nome/{nome}"})
    public ResponseEntity ListarPorNome(@PathVariable String nome) {

        ResponseEntity retorno = ResponseEntity.notFound().build();

        List<Usuario> lista = usuarioRepository.findByNomeContainingIgnoreCase(nome);
        if (!lista.isEmpty()) {
            retorno = ResponseEntity.ok().body(lista);
        }

        return retorno;
    }	

    @PostMapping
    public ResponseEntity InserirUsuario(@RequestBody Usuario usuario) {
       
        ResponseEntity retorno = ResponseEntity.badRequest().build();

        try {
            if (usuario != null && usuario.getId() == null) {
                
                Endereco endereco = viaCepService.BuscarEnderecoPor(usuario.getCep());
                
                usuario.setEndereco(endereco);
                
                Usuario usuarioInserido = usuarioRepository.save(usuario);
                

                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(usuarioInserido).toUri();

                retorno = ResponseEntity.created(uri).body(usuarioInserido);
            }
        } catch (Exception e) {

        }

        return retorno;
    }

    @PutMapping
    @Transactional
    public ResponseEntity AtualizarUsuario(@RequestBody Usuario usuario) {
        
        ResponseEntity retorno = ResponseEntity.badRequest().build();
        
        Usuario usuarioGravado = usuarioRepository.findByEmail(usuario.getEmail());        
        
        try {

            if (usuarioGravado != null && usuarioGravado.getId() != null) {   
                

                    Endereco endereco = viaCepService.BuscarEnderecoPor(usuario.getCep());

                    usuarioGravado.setEndereco(endereco);
                    usuarioGravado.setNome(usuario.getNome());
                    usuarioGravado.setCep(usuario.getCep());
                    usuarioGravado.setTelefone(usuario.getTelefone());

                    retorno = ResponseEntity.ok(usuarioGravado);
                
            }
        } catch (Exception e) {

        }

        return retorno;
    }
    
    @DeleteMapping(path = {"/{email}"})
    public ResponseEntity DeletarUsuario(@PathVariable String email) {
        
        ResponseEntity retorno = ResponseEntity.badRequest().build();
        
        Usuario usuarioGravado = usuarioRepository.findByEmail(email);
        
        
        if(usuarioGravado != null){
            
            usuarioRepository.deleteById(usuarioGravado.getId());
            
            retorno = ResponseEntity.ok().build();
        }       
        
        return retorno;
    }

}
