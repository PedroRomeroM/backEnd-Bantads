package br.com.bantads.orquestrador.amqp;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AMQPConfiguration {
    @Bean
    public Queue criarFilaCriarCliente(){
        return new Queue("criar-cliente",false);
    }
    @Bean
    public Queue criarFilaCriarLogin(){
        return new Queue("criar-login",false);
    }
    @Bean
    public Queue criarFilaConsultarGerente(){
        return new Queue("consultar-gerente",false);
    }
    @Bean
    public Queue criarFilaCriarConta(){
        return new Queue("criar-conta",false);
    }
    @Bean
    public Queue criarFilaOrquestradorCliente(){
        return new Queue("fila-orquestrador-cliente-criado",false);
    }
    @Bean
    public Queue criarFilaOrquestradorConta(){
        return new Queue("fila-orquestrador-conta-criada",false);
    }
    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory conn){
        return new RabbitAdmin(conn);
    }
    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializarRabbitAdmin(RabbitAdmin rabbitAdmin){
        return e -> rabbitAdmin.initialize();
    }
    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }
}
