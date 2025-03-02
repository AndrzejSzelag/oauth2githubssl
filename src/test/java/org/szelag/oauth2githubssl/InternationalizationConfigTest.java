package org.szelag.oauth2githubssl;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.szelag.oauth2githubssl.internationalization.InternationalizationConfig;

@ExtendWith(MockitoExtension.class)
class InternationalizationConfigTest {

    @Test
    void testMessageSourceBeanCreation() {
        
        // Tworzymy instancję klasy konfiguracyjnej
        InternationalizationConfig config = new InternationalizationConfig();
        MessageSource messageSource = config.messageSource();

        // Sprawdzamy, czy obiekt nie jest null
        assertNotNull(messageSource);
        assertTrue(messageSource instanceof ReloadableResourceBundleMessageSource);

        // Rzutowanie na konkretny typ, aby sprawdzić szczegóły konfiguracji
        ReloadableResourceBundleMessageSource reloadableSource = (ReloadableResourceBundleMessageSource) messageSource;

        // Sprawdzenie, czy bean jest poprawnie wstrzyknięty
        assertNotNull(messageSource, "MessageSource bean should not be null");

        // Sprawdzamy, czy baza nazw wiadomości została poprawnie ustawiona
        assertTrue(reloadableSource.getBasenameSet().contains("classpath:lang/messages"));
    }
}