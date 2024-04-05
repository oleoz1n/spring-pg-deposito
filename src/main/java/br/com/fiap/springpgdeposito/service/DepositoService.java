package br.com.fiap.springpgdeposito.service;

import br.com.fiap.springpgdeposito.dto.AbstractDTO;
import br.com.fiap.springpgdeposito.dto.request.DepositoRequest;
import br.com.fiap.springpgdeposito.dto.response.DepositoResponse;
import br.com.fiap.springpgdeposito.entity.Deposito;
import br.com.fiap.springpgdeposito.entity.Endereco;
import br.com.fiap.springpgdeposito.repository.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepositoService implements ServiceDTO<Deposito, DepositoRequest, DepositoResponse, AbstractDTO> {

    @Autowired
    EnderecoService enderecoService;


    @Autowired
    DepositoRepository repo;


    /**
     * @param depositoRequest
     * @return
     */
    @Override
    public Deposito toEntity(DepositoRequest depositoRequest) {
        if (Objects.isNull(depositoRequest)) return null;
        return Deposito.builder()
                .nome(depositoRequest.nome())
                .endereco(enderecoService.toEntity(depositoRequest.endereco()))
                .build();

    }

    /**
     * @param deposito
     * @return
     */
    @Override
    public DepositoResponse toResponse(Deposito deposito) {

        if (Objects.isNull(deposito)) return null;

        return new DepositoResponse(
                deposito.getId(),
                deposito.getNome(),
                enderecoService.toResponse(deposito.getEndereco())
        );

    }

    /**
     * @param abstractDTO
     * @return
     */
    @Override
    public Deposito findDatabaseObjet(AbstractDTO abstractDTO) {
        if (Objects.isNull(abstractDTO.id())) return null;
        return repo.findById(abstractDTO.id()).orElse(null);
    }

    /**
     * @param depositoRequest
     * @return
     */
    @Override
    public Deposito save(DepositoRequest depositoRequest) {
        return repo.save(toEntity(depositoRequest));
    }

    /**
     * @return
     */
    @Override
    public List<Deposito> findAll() {
        return repo.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Deposito findById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
