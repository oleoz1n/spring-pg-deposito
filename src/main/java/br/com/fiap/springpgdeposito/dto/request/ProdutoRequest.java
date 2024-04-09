package br.com.fiap.springpgdeposito.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProdutoRequest(

        @NotNull(message = "O atributo nome é obrigatório")
        @Size(min = 5, max= 255, message = "O nome deve ter entre 5 e 255 caracteres")
        String nome,
        @NotNull(message = "O atributo descrição é obrigatório")
        @Size(min = 10, max= 255, message = "A descrição deve ter entre 10 e 255 caracteres")
        String descricao,

        @NotNull(message = "O atributo valor é obrigatório")
        @Min(value = 0, message = "O valor deve ser maior que zero")
        BigDecimal valor

) {
}
