package bantadsbackend.microGerentes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class Configuracao {

    @Bean
    public ModelMapper oterModelMapper(){ return new ModelMapper(); }
}
