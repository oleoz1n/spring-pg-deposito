package br.com.fiap.springpgdeposito.resource;


import br.com.fiap.springpgdeposito.dto.request.EnderecoRequest;
import br.com.fiap.springpgdeposito.dto.response.EnderecoResponse;
import br.com.fiap.springpgdeposito.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoService service;

    @GetMapping
    public ResponseEntity<List<EnderecoResponse>> findAll() {
        List<EnderecoResponse> list = service.findAll().stream().map( service::toResponse ).toList();
        if(list.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EnderecoResponse> findById(@PathVariable Long id) {
        EnderecoResponse response = service.toResponse( service.findById( id ) );
        if (Objects.isNull( response )) return ResponseEntity.noContent().build();
        return ResponseEntity.ok( response );
    }

    @Transactional
    @PostMapping
    public ResponseEntity<EnderecoResponse> save(@RequestBody EnderecoRequest e) {

        if (Objects.isNull(e)) return ResponseEntity.badRequest().build();

        EnderecoResponse response = service.toResponse( service.save( e ) );
        if (Objects.isNull( response )) return ResponseEntity.badRequest().build();

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( response.id() )
                .toUri();

        return ResponseEntity.created( uri ).body( response );

    }

}
