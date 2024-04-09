package br.com.fiap.springpgdeposito.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepositoRequest(

        @NotNull(message= "O atributo nome é obrigatório")
        @NotBlank(message= "O atributo nome é obrigatório")
        String nome,

        @NotNull(message = "O atributo endereço é obrigatório")
        EnderecoRequest endereco


) {
}
