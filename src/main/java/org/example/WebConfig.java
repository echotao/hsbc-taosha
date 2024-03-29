package org.example;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebConfig {
    @Bean
    public ConfigurableServletWebServerFactory servletContainer() {
        return new TomcatServletWebServerFactory();
    }
}
