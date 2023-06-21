package br.com.bantads.orquestrador.sagas.updateGerente;

import br.com.bantads.orquestrador.dtos.sagacliente.ClienteDto;
import br.com.bantads.orquestrador.dtos.updateGerente.UpdateGerenteDto;
import br.com.bantads.orquestrador.sagas.validacoes.Validacoes;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SagaAtualizarGerente {

    public static ResponseEntity<Object> start(UpdateGerenteDto dto, RabbitTemplate rabbitTemplate) {

        //Se estiver tudo ok, enviar para o micro de clientes para registrar no banco.
        rabbitTemplate.convertAndSend("update-gerente", dto);

        return new ResponseEntity<>("Solicitacao de update realizada com sucesso!", HttpStatus.CREATED);
    }
}




