package br.com.bantads.orquestrador.sagas.autocadastro;

import br.com.bantads.orquestrador.dtos.ClientReturnDto;
import br.com.bantads.orquestrador.dtos.ClienteDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

public class SagaAutocadastro {

    public static ResponseEntity<Object> start(ClienteDto dto,RabbitTemplate rabbitTemplate){
        //Validar se algum campo está nullo
        if(Validacoes.validarCampos(dto)){
            //Validar o CPF
            if(Validacoes.validarCPF(dto.getCpfCliente())){
                //Se estiver tudo ok, enviar para o micro de clientes para registrar no banco.
                rabbitTemplate.convertAndSend("criar-cliente",dto);
                System.out.println("criou o cliente "+ dto.getCpfCliente());

                return new ResponseEntity<>("Cliente cadastrado com sucesso!", HttpStatus.CREATED);
            }else {
                //Caso CPF invalido
                return new ResponseEntity("CPF Invalido", HttpStatus.BAD_REQUEST);
            }
        }
        //Caso algum campo seja nullo
        return new ResponseEntity<>("Dados do cliente invalidos", HttpStatus.BAD_REQUEST);
    }

}


