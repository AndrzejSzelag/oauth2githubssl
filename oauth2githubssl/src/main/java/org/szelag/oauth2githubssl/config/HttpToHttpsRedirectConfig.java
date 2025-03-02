package org.szelag.oauth2githubssl.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpToHttpsRedirectConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
        // Metoda addAdditionalTomcatConnectors() jest dostępna tylko w TomcatServletWebServerFactory!
        return factory -> factory.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
    }

    // Przekierowanie z portu 8080 na port 8443
    private Connector httpToHttpsRedirectConnector() {
        Connector connector = new Connector(Http11NioProtocol.class.getName());
        connector.setScheme("http");
        connector.setPort(8080); // Domyślny port serwera Apache Tomcat
        connector.setSecure(false);
        connector.setRedirectPort(8443); // Automatyczne przekierowanie na HTTPS
        return connector;
    }
}