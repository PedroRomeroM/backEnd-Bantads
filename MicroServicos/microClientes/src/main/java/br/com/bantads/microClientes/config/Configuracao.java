package br.com.bantads.microClientes.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class Configuracao {
    @Bean
    public ModelMapper oterModelMapper() {
        return new ModelMapper();
    }
}
