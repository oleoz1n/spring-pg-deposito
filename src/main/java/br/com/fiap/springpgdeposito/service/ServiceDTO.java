package br.com.fiap.springpgdeposito.service;

import java.util.List;


/**
 * @param <Entity>      classe que contem a anotação @Entity
 * @param <Request>     Um DTO de Request
 * @param <Response>    Um DTO de Response
 * @param <AbstractDTO> Um DTO que tenha o ID
 */
public interface ServiceDTO<Entity, Request, Response, AbstractDTO> {

    Entity toEntity(Request request);

    Response toResponse(Entity entity);


    Entity findDatabaseObjet(AbstractDTO abstractDTO);

    Entity save(Request request);

    List<Entity> findAll();

    Entity findById(Long id);

}

