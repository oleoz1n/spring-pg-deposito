package br.com.fiap.springpgdeposito.resource;


import br.com.fiap.springpgdeposito.dto.request.ProdutoRequest;
import br.com.fiap.springpgdeposito.dto.response.ProdutoResponse;
import br.com.fiap.springpgdeposito.entity.Produto;
import br.com.fiap.springpgdeposito.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> findAll() {


        List<ProdutoResponse> list = service.findAll()
                .stream()
                .map( service::toResponse )
                .toList();

        if (list.isEmpty()) ResponseEntity.noContent().build();

        return ResponseEntity.ok( list );

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> findById(@PathVariable Long id) {
        ProdutoResponse response = service.toResponse(
                service.findById( id )
        );
        if (response == null) ResponseEntity.noContent().build();
        return ResponseEntity.ok( response );
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ProdutoResponse> save(@RequestBody ProdutoRequest p) {

        if (p == null) ResponseEntity.badRequest().build();

        Produto saved = service.save( p );
        ProdutoResponse response = service.toResponse( saved );

        if (response == null) ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( response.id() )
                .toUri();


        return ResponseEntity.created( uri ).body( response );

    }

}
