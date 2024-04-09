package br.com.fiap.springpgdeposito.resource;


import br.com.fiap.springpgdeposito.dto.AbstractDTO;
import br.com.fiap.springpgdeposito.dto.EntradaDTO;
import br.com.fiap.springpgdeposito.dto.request.ItemEstocadoRequest;
import br.com.fiap.springpgdeposito.dto.response.ItemEstocadoResponse;
import br.com.fiap.springpgdeposito.entity.ItemEstocado;
import br.com.fiap.springpgdeposito.service.ItemEstocadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.Vector;

@RestController
@RequestMapping(value = "/entrada/deposito")
public class EntradaResource {

    @Autowired
    private ItemEstocadoService service;


    @Transactional
    @PostMapping(value = "/{idDeposito}/produto/{idProduto}")
    public ResponseEntity<List<ItemEstocadoResponse>> entrada(
            @PathVariable Long idDeposito,
            @PathVariable Long idProduto,
            @RequestBody @Valid EntradaDTO entrada
    ) {

        if (Objects.isNull( entrada ) || entrada.quantidade() < 1) return ResponseEntity.badRequest().build();

        Long i = 0L;

        List<ItemEstocado> estocados = new Vector<>();

        while (entrada.quantidade() > i) {
            var produto = new AbstractDTO( idProduto );
            var deposito = new AbstractDTO( idDeposito );
            ItemEstocadoRequest itemEstocado = new ItemEstocadoRequest(
                    produto,
                    deposito
            );

            ItemEstocado saved = service.save( itemEstocado );
            saved.setEntrada( LocalDateTime.now() );
            saved.setNumeroDeSerie( UUID.randomUUID().toString() );
            estocados.add( saved );
            i++;
        }

        List<ItemEstocadoResponse> list = estocados.stream().map( service::toResponse ).toList();

        if (Objects.isNull( list ) || list.isEmpty()) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok( list );


    }

}
