package br.com.fiap.springpgdeposito.resource;


import br.com.fiap.springpgdeposito.dto.request.DepositoRequest;
import br.com.fiap.springpgdeposito.dto.response.DepositoResponse;
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
    public List<DepositoResponse> findAll() {
        return service.findAll()
                .stream()
                .map(service::toResponse)
                .toList();
    }

    @GetMapping(value = "/{id}")
    public DepositoResponse findById(@PathVariable Long id) {
        return service.toResponse(service.findById(id));
    }

    @Transactional
    @PostMapping
    public DepositoResponse save(@RequestBody DepositoRequest d) {
        Deposito saved = service.save(d);
        return service.toResponse(saved);
    }

}
