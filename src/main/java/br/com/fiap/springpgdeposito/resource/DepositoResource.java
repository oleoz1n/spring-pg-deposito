package br.com.fiap.springpgdeposito.resource;


import br.com.fiap.springpgdeposito.entity.Deposito;
import br.com.fiap.springpgdeposito.repository.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/deposito")
public class DepositoResource {

    @Autowired
    private DepositoRepository repo;

    @GetMapping
    public List<Deposito> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Deposito findById(@PathVariable Long id){
       return repo.findById( id ).orElseThrow();
    }

    @Transactional
    @PostMapping
    public Deposito save(@RequestBody Deposito d){
       return repo.save( d );
    }

}
