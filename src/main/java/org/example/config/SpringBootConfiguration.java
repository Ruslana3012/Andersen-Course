package org.example.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootConfiguration {

    @Bean
    @ConditionalOnProperty(name = "conditional.property")
    public String ThisIsMyFirstConditionalBean() {
        return "This bean is only available when conditional.property is true";
    }
}
