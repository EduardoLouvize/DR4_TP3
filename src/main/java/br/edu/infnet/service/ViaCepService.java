
package br.edu.infnet.service;

import br.edu.infnet.domain.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="https://viacep.com.br/ws/", name="viaCepService")
public interface ViaCepService {
    
    @GetMapping(value = "{cep}/json")
    Endereco BuscarEnderecoPor(@PathVariable String cep);
    
}
