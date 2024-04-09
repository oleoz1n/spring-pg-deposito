package br.com.fiap.springpgdeposito.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoRequest(

        @NotNull(message = "O atributo cep é obrigatório")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O cep deve seguir o padrão XXXXX-XXX")
        String cep,
        String numero,
        String complemento

) {
}
