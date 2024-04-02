package br.com.fiap.springpgdeposito.service;

import br.com.fiap.springpgdeposito.dto.AbstractDTO;
import br.com.fiap.springpgdeposito.dto.request.ItemEstocadoRequest;
import br.com.fiap.springpgdeposito.dto.response.ItemEstocadoResponse;
import br.com.fiap.springpgdeposito.entity.ItemEstocado;
import br.com.fiap.springpgdeposito.repository.ItemEstocadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ItemEstocadoService implements ServiceDTO<ItemEstocado, ItemEstocadoRequest, ItemEstocadoResponse, AbstractDTO> {

    @Autowired
    private ItemEstocadoRepository repo;

    @Autowired
    private DepositoService depositoService;

    @Autowired
    private ProdutoService produtoService;

    @Override
    public ItemEstocado toEntity(ItemEstocadoRequest itemEstocadoRequest) {

        if(Objects.isNull(itemEstocadoRequest)) return null;

        var deposito = depositoService.findDatabaseObjet(itemEstocadoRequest.deposito());
        var produto = produtoService.findDatabaseObjet(itemEstocadoRequest.produto());

        return ItemEstocado.builder()
                .deposito(deposito)
                .produto(produto)
                .build();

    }

    @Override
    public ItemEstocadoResponse toResponse(ItemEstocado itemEstocado) {
        if(Objects.isNull(itemEstocado)) return null;
        var produto = produtoService.toResponse(itemEstocado.getProduto());
        var deposito = depositoService.toResponse(itemEstocado.getDeposito());
        return new ItemEstocadoResponse(
                itemEstocado.getId(),
                itemEstocado.getNumeroDeSerie(),
                produto,
                deposito,
                itemEstocado.getEntrada(),
                itemEstocado.getSaida()
        );
    }

    @Override
    public ItemEstocado findDatabaseObjet(AbstractDTO abstractDTO) {
        if(Objects.isNull(abstractDTO)) return null;
        return repo.findById(abstractDTO.id()).orElse(null);
    }

    @Override
    public ItemEstocado save(ItemEstocadoRequest itemEstocadoRequest) {
        if(Objects.isNull(itemEstocadoRequest)) return null;
        ItemEstocado entity = toEntity(itemEstocadoRequest);
        return repo.save(entity);
    }

    @Override
    public List<ItemEstocado> findAll() {
        return repo.findAll();
    }

    @Override
    public ItemEstocado findById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
