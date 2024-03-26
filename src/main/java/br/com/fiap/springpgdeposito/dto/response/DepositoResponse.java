package br.com.fiap.springpgdeposito.dto.response;

public record DepositoResponse(

        Long id,

        String nome,

        EnderecoResponse endereco

) {
}
