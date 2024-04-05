package br.com.fiap.springpgdeposito.resource;


import br.com.fiap.springpgdeposito.dto.request.DepositoRequest;
import br.com.fiap.springpgdeposito.dto.response.DepositoResponse;
import br.com.fiap.springpgdeposito.entity.Deposito;
import br.com.fiap.springpgdeposito.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/deposito")
public class DepositoResource {

    @Autowired
    private DepositoService service;

    @GetMapping
    public ResponseEntity<List<DepositoResponse>> findAll() {
        List<DepositoResponse> list = service.findAll()
                .stream()
                .map( service::toResponse )
                .toList();
        if (list.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok( list );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DepositoResponse> findById(@PathVariable Long id) {
        DepositoResponse response = service.toResponse( service.findById( id ) );
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok( response );
    }

    @Transactional
    @PostMapping
    public ResponseEntity<DepositoResponse> save(@RequestBody DepositoRequest d) {
        Deposito saved = service.save( d );
        DepositoResponse response = service.toResponse( saved );
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok( response );
    }

}
