package br.com.bantads.orquestrador.sagas.updateCliente;

import br.com.bantads.orquestrador.dtos.sagacliente.ClienteDto;
import br.com.bantads.orquestrador.sagas.validacoes.Validacoes;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SagaUpdateCliente {
    public static ResponseEntity<Object> start(ClienteDto dto, RabbitTemplate rabbitTemplate) {

        rabbitTemplate.convertAndSend("update-cliente", dto);
        System.out.println("Update solicitado " + dto.getCpfCliente());

        return new ResponseEntity<>("Solicitacao de cadasro realizada com sucesso!", HttpStatus.CREATED);
    }
}