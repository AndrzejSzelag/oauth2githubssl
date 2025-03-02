package org.szelag.oauth2githubssl;

import org.apache.catalina.connector.Connector;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.szelag.oauth2githubssl.config.HttpToHttpsRedirectConfig;

class HttpToHttpsRedirectConfigTest {

   @Test
    void servletContainer_ShouldReturnWebServerFactoryCustomizer() {
        // given
        HttpToHttpsRedirectConfig config = new HttpToHttpsRedirectConfig();

        // when
        WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer = config.servletContainer();

        // then
        assertNotNull(customizer, "Customizer should not be null");
    }

    @Test
    void httpToHttpsRedirectConnector_ShouldConfigureConnector() throws Exception {
        // given
        HttpToHttpsRedirectConfig config = new HttpToHttpsRedirectConfig();

        java.lang.reflect.Method method = HttpToHttpsRedirectConfig.class
                .getDeclaredMethod("httpToHttpsRedirectConnector");
        method.setAccessible(true);

        // when
        Connector connector = (Connector) method.invoke(config);

        // then
        assertNotNull(connector, "Connector should not be null");
        assertEquals("http", connector.getScheme(), "Scheme should be http");
        assertEquals(8080, connector.getPort(), "Port should be 8080");
        assertFalse(connector.getSecure(), "Connector should not be secure");
        assertEquals(8443, connector.getRedirectPort(), "Redirect port should be 8443");
    }

    @Test
    void servletContainer_ShouldAddConnectorToFactory() {
        // given
        HttpToHttpsRedirectConfig config = new HttpToHttpsRedirectConfig();
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer = config.servletContainer();

        // when
        customizer.customize(factory);

        // then
        assertEquals(1, factory.getAdditionalTomcatConnectors().size(),
                "Factory should have one additional connector");

        Connector connector = factory.getAdditionalTomcatConnectors().get(0);
        assertEquals("http", connector.getScheme(), "Scheme should be http");
        assertEquals(8080, connector.getPort(), "Port should be 8080");
        assertFalse(connector.getSecure(), "Connector should not be secure");
        assertEquals(8443, connector.getRedirectPort(), "Redirect port should be 8443");
    }

    @Test
    void integrationTest_FactoryShouldUseCustomizer() {
        // given
        HttpToHttpsRedirectConfig config = new HttpToHttpsRedirectConfig();
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

        // when
        config.servletContainer().customize(factory);

        // then
        assertEquals(1, factory.getAdditionalTomcatConnectors().size(),
                "Factory should have one additional connector");

        Connector connector = factory.getAdditionalTomcatConnectors().get(0);
        assertEquals(8080, connector.getPort(), "HTTP connector should use port 8080");
        assertEquals(8443, connector.getRedirectPort(), "HTTP connector should redirect to port 8443");
    }
}
