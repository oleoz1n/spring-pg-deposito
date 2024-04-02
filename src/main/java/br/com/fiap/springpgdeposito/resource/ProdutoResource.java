package br.com.fiap.springpgdeposito.resource;


import br.com.fiap.springpgdeposito.dto.request.ProdutoRequest;
import br.com.fiap.springpgdeposito.dto.response.ProdutoResponse;
import br.com.fiap.springpgdeposito.entity.Produto;
import br.com.fiap.springpgdeposito.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<ProdutoResponse> findAll() {
        return service.findAll()
                .stream()
                .map(service::toResponse)
                .toList();
    }

    @GetMapping(value = "/{id}")
    public ProdutoResponse findById(@PathVariable Long id) {
        return service.toResponse(
                service.findById(id)
        );
    }

    @Transactional
    @PostMapping
    public ProdutoResponse save(@RequestBody ProdutoRequest p) {
        Produto saved = service.save(p);
        return service.toResponse(saved);
    }

}
