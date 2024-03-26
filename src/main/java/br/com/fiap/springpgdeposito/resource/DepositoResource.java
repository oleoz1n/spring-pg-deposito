package br.com.fiap.springpgdeposito.resource;


import br.com.fiap.springpgdeposito.entity.Deposito;
import br.com.fiap.springpgdeposito.repository.DepositoRepository;
import br.com.fiap.springpgdeposito.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/deposito")
public class DepositoResource {

    @Autowired
    private DepositoService service;

    @GetMapping
    public List<Deposito> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Deposito findById(@PathVariable Long id){
       return service.findById( id );
    }

    @Transactional
    @PostMapping
    public Deposito save(@RequestBody Deposito d){
       return service.save( d );
    }

}
