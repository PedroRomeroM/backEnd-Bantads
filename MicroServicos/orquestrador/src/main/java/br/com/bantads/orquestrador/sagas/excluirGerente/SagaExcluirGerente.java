package br.com.bantads.orquestrador.sagas.excluirGerente;

import br.com.bantads.orquestrador.dtos.sagaExcluirGerente.CpfGerenteDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SagaExcluirGerente {

    public static ResponseEntity<Object> start(String cpf, RabbitTemplate rabbitTemplate){
        CpfGerenteDto cpfGerenteDto = new CpfGerenteDto(cpf);

        //Passar para a fila de excluir cliente
        rabbitTemplate.convertAndSend("excluir-gerente",cpfGerenteDto);
        System.out.println("mandou o gerente do id: "+ cpfGerenteDto.getCpf() + "pras trevas");

        return new ResponseEntity<>("Solicitacao de Exclusso realizada com sucesso!", HttpStatus.CREATED);
    }
}
