package br.com.fiap.springpgdeposito.dto.request;

import br.com.fiap.springpgdeposito.dto.AbstractDTO;
import jakarta.validation.constraints.NotNull;

public record ItemEstocadoRequest(

        @NotNull(message = "O produto é obrigatório")
        AbstractDTO produto,

        @NotNull(message = "A depósito é obrigatório")
        AbstractDTO deposito

) {
}
