package br.com.bantads.orquestrador.sagas.autocadastro;

import br.com.bantads.orquestrador.dtos.sagacliente.ClienteDto;
import br.com.bantads.orquestrador.sagas.validacoes.Validacoes;
import br.com.bantads.orquestrador.utils.EnviarEmail;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SagaAutocadastro {

    public static ResponseEntity<Object> start(ClienteDto dto,RabbitTemplate rabbitTemplate){
        //Validar se algum campo está nullo
        if(Validacoes.validarCampos(dto)){
            //Validar o CPF
            if(Validacoes.validarCPF(dto.getCpfCliente())){
                //Se estiver tudo ok, enviar para o micro de clientes para registrar no banco.
                rabbitTemplate.convertAndSend("criar-cliente",dto);
                System.out.println("criou o cliente "+ dto.getCpfCliente());

                return new ResponseEntity<>("Solicitacao de cadastro realizada com sucesso!", HttpStatus.CREATED);
            }else {
                //Caso CPF invalido
                EnviarEmail enviarEmail = new EnviarEmail();
                enviarEmail.setNomeDestinatario(dto.getNomeCliente());

                enviarEmail.enviarGmail();
                return new ResponseEntity("CPF Invalido", HttpStatus.BAD_REQUEST);
            }
        }
        //Caso algum campo seja nullo
        EnviarEmail enviarEmail = new EnviarEmail();
        enviarEmail.setNomeDestinatario(dto.getNomeCliente());

        enviarEmail.enviarGmail();
        return new ResponseEntity<>("Dados do cliente invalidos", HttpStatus.BAD_REQUEST);
    }

}


