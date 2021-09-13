package br.edu.infnet.domain.controller;

import br.edu.infnet.domain.Endereco;
import br.edu.infnet.domain.Usuario;
import br.edu.infnet.repository.UsuarioRepository;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = {"/usuarios"})
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(path = {"/"})
    public ResponseEntity ObterUsuarios() {
        ResponseEntity retorno = ResponseEntity.notFound().build();
        List<Usuario> lista = (List<Usuario>) usuarioRepository.findAll();
        if (!lista.isEmpty()) {
            retorno = ResponseEntity.ok().body(lista);
        }
        return retorno;
    }

    @GetMapping(path = {"{id}"})
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
            System.out.println("Entrou no getEmail 1");
            Usuario resultado = usuarioRepository.findByEmail(email);
            System.out.println("Resultado: " + resultado);
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
    public ResponseEntity InserirUsuario(@RequestBody Usuario usuario, @RequestBody Endereco endereco) {
        System.out.println("Entrou no Post");
        ResponseEntity retorno = ResponseEntity.badRequest().build();

        try {
            if (usuario != null && usuario.getId() == null && endereco != null && endereco.getId() == null) {
                
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
    public ResponseEntity AtualizarUsuario(@RequestBody Usuario usuario) {
        System.out.println("Entrou");
        ResponseEntity retorno = ResponseEntity.badRequest().build();
        System.out.println("Usuario: " + usuario.getNome());
        System.out.println("Usuario: " + usuario.getEmail());
        System.out.println("Usuario: " + usuario);
        try {

            if (usuario != null && usuario.getId() != null) {
                System.out.println("Entrou no if");
                Usuario usuarioGravado = this.findById(usuario.getId());
                System.out.println("Gravado: " + usuarioGravado);
                if (usuarioGravado != null) {

                    usuarioGravado = usuarioRepository.save(usuario);

                    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(usuarioGravado).toUri();

                    retorno = ResponseEntity.created(uri).body(usuarioGravado);
                }
            }
        } catch (Exception e) {

        }

        return retorno;
    }

}
