package org.szelag.oauth2githubssl;

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
        
        InternationalizationConfig config = new InternationalizationConfig();
        MessageSource messageSource = config.messageSource();

        assertNotNull(messageSource);
        assertTrue(messageSource instanceof ReloadableResourceBundleMessageSource);

        ReloadableResourceBundleMessageSource reloadableSource = (ReloadableResourceBundleMessageSource) messageSource;
        assertNotNull(messageSource, "MessageSource bean should not be null");
        assertTrue(reloadableSource.getBasenameSet().contains("classpath:lang/messages"));
    }
}
