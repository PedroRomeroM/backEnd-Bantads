package br.com.bantads.orquestrador.sagas.cadastroGerente;

import br.com.bantads.orquestrador.dtos.sagacliente.ClienteDto;
import br.com.bantads.orquestrador.dtos.sagainserirgerente.GerenteDto;
import br.com.bantads.orquestrador.sagas.validacoes.Validacoes;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SagaInserirGerente {
    public static ResponseEntity<Object> start(GerenteDto dto, RabbitTemplate rabbitTemplate){
        //Validar se algum campo está nullo
        if(Validacoes.validarCampos(dto)){
            //Validar o CPF
            if(Validacoes.validarCPF(dto.getCpfGerente())){
                //Se estiver tudo ok, enviar para o micro de gerente para registrar no banco.
                rabbitTemplate.convertAndSend("criar-gerente",dto);

                return new ResponseEntity<>("Solicitacao de cadasro realizada com sucesso!", HttpStatus.CREATED);
            }else {
                //Caso CPF invalido
                return new ResponseEntity("CPF Invalido", HttpStatus.BAD_REQUEST);
            }
        }
        //Caso algum campo seja nullo
        return new ResponseEntity<>("Dados do gerente invalidos", HttpStatus.BAD_REQUEST);
    }

}