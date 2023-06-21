package br.com.bantads.orquestrador.controller;

import br.com.bantads.orquestrador.dtos.sagacliente.ClienteDto;
import br.com.bantads.orquestrador.dtos.sagainserirgerente.GerenteDto;
import br.com.bantads.orquestrador.dtos.updateGerente.UpdateGerenteDto;
import br.com.bantads.orquestrador.sagas.excluirGerente.SagaExcluirGerente;
import br.com.bantads.orquestrador.sagas.autocadastro.SagaAutocadastro;
import br.com.bantads.orquestrador.sagas.cadastroGerente.SagaInserirGerente;
import br.com.bantads.orquestrador.sagas.updateCliente.SagaUpdateCliente;
import br.com.bantads.orquestrador.sagas.updateGerente.SagaAtualizarGerente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
        SagaInserirGerente sagaInserirGerente = new SagaInserirGerente();
        return sagaInserirGerente.start(dto,rabbitTemplate);
    }

    @DeleteMapping("/delete/{cpf}")
    public ResponseEntity<Object> deleteCliente(@PathVariable @NotNull String cpf) {
        SagaExcluirGerente sagaExcluirGerente = new SagaExcluirGerente();
        return sagaExcluirGerente.start(cpf,rabbitTemplate);
    }

    @PutMapping("/manager/{cpfGerente}")
    public ResponseEntity<Object> updateGerente(@PathVariable @NotNull String cpfGerente, @RequestBody UpdateGerenteDto dto){
        dto.setCpfGerente(cpfGerente);
        SagaAtualizarGerente sagaAtualizarGerente = new SagaAtualizarGerente();
        return sagaAtualizarGerente.start(dto,rabbitTemplate);
    }
    @PutMapping("/client/update")
    public ResponseEntity<Object> updateCliente(@RequestBody ClienteDto dto){
        SagaUpdateCliente sagaUpdateCliente = new SagaUpdateCliente();
        return sagaUpdateCliente.start(dto,rabbitTemplate);
    }
}
