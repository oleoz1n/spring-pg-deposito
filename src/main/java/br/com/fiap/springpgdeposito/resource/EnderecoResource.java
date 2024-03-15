package br.com.fiap.springpgdeposito.resource;


import br.com.fiap.springpgdeposito.entity.Endereco;
import br.com.fiap.springpgdeposito.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoRepository repo;

    @GetMapping
    public List<Endereco> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Endereco findById(@PathVariable Long id){
       return repo.findById( id ).orElseThrow();
    }

    @Transactional
    @PostMapping
    public Endereco save(@RequestBody Endereco e){
       return repo.save( e );
    }

}
