package br.com.fiap.springpgdeposito.resource;


import br.com.fiap.springpgdeposito.dto.request.ProdutoRequest;
import br.com.fiap.springpgdeposito.dto.response.ProdutoResponse;
import br.com.fiap.springpgdeposito.entity.Produto;
import br.com.fiap.springpgdeposito.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "descricao", required = false) String descricao,
            @RequestParam(name = "valor", required = false) BigDecimal valor
    ) {
        Produto produto = Produto.builder()
                .nome( nome )
                .descricao( descricao )
                .valor( valor )
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<Produto> example = Example.of(produto, matcher);

        List<ProdutoResponse> list = service.findAll(example)
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
    public ResponseEntity<ProdutoResponse> save(@RequestBody @Valid ProdutoRequest p) {

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
