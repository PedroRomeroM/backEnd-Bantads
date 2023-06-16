package br.com.bantads.orquestrador.controller;

import br.com.bantads.orquestrador.dtos.sagacliente.ClienteDto;
import br.com.bantads.orquestrador.dtos.sagainserirgerente.GerenteDto;
import br.com.bantads.orquestrador.sagas.autocadastro.SagaAutocadastro;
import br.com.bantads.orquestrador.sagas.cadastroGerente.SagaInserirGerente;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class Controller {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/criar-cliente")
    public ResponseEntity<Object> cadastroCliente(@RequestBody @Valid ClienteDto dto) {
        SagaAutocadastro sagaAutocadastro = new SagaAutocadastro();
        return sagaAutocadastro.start(dto,rabbitTemplate);
    }

    @PostMapping(value = "/criar-gerente")
    public ResponseEntity<Object> cadastroGerente(@RequestBody @Valid GerenteDto dto) {

        //todo consultar gerente no micro de conta
        SagaInserirGerente sagaInserirGerente = new SagaInserirGerente();
        return sagaInserirGerente.start(dto,rabbitTemplate);
    }
}
