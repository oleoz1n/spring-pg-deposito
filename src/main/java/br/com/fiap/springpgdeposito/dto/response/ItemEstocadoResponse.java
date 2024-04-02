package br.com.fiap.springpgdeposito.dto.response;

import java.time.LocalDateTime;

public record ItemEstocadoResponse(

        Long id,

        String numeroDeSerie,

        ProdutoResponse produto,

        DepositoResponse deposito,

        LocalDateTime entrada,

        LocalDateTime saida

) {
}
