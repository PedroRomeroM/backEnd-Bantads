package br.com.bantads.orquestrador.controller;

import br.com.bantads.orquestrador.dtos.ClienteDto;
import br.com.bantads.orquestrador.sagas.autocadastro.SagaAutocadastro;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/autocadastro")
public class Controller {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<Object> cadastroCliente(@RequestBody @Valid ClienteDto dto) {
        SagaAutocadastro sagaAutocadastro = new SagaAutocadastro();
        return sagaAutocadastro.start(dto,rabbitTemplate);
    }
}
