package org.szelag.oauth2githubssl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.szelag.oauth2githubssl.controller.HomeController;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Mock
    private OAuth2User oauth2User;

    @Mock
    private Model model;

    @Autowired
    private HomeController homeController;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void testHomeWithAuthenticatedUser() {
        // given
        when(oauth2User.getAttribute("avatar_url")).thenReturn("https://avatars.githubusercontent.com");
        when(oauth2User.getAttribute("name")).thenReturn("Andrzej Szelag");
        when(oauth2User.getAttribute("location")).thenReturn("Konin, Poland");
        when(oauth2User.getAttribute("bio")).thenReturn("Passionate about Java");
        when(oauth2User.getAttribute("html_url")).thenReturn("https://github.com/AndrzejSzelag");

        // when
        String viewName = homeController.home(oauth2User, model);

        // then
        assertEquals("home", viewName);
        verify(model).addAttribute("avatar_url", "https://avatars.githubusercontent.com");
        verify(model).addAttribute("name", "Andrzej Szelag");
        verify(model).addAttribute("location", "Konin, Poland");
        verify(model).addAttribute("bio", "Passionate about Java");
        verify(model).addAttribute("html_url", "https://github.com/AndrzejSzelag");
    }

    @Test
    public void testHomeWithAuthenticatedUserMissingAttributes() {
        // given
        when(oauth2User.getAttribute("avatar_url")).thenReturn("https://avatars.githubusercontent.com");
        when(oauth2User.getAttribute("name")).thenReturn(null);
        when(oauth2User.getAttribute("location")).thenReturn(null);
        when(oauth2User.getAttribute("bio")).thenReturn("Passionate about Java");
        when(oauth2User.getAttribute("html_url")).thenReturn(null);

        // when
        String viewName = homeController.home(oauth2User, model);

        // then
        assertEquals("home", viewName);
        verify(model).addAttribute("avatar_url", "https://avatars.githubusercontent.com");
        verify(model).addAttribute("bio", "Passionate about Java");
        verify(model, never()).addAttribute(eq("name"), any());
        verify(model, never()).addAttribute(eq("location"), any());
        verify(model, never()).addAttribute(eq("html_url"), any());
    }

    @Test
    public void testHomeWithNoAuthenticatedUser() {
        // when
        String viewName = homeController.home(null, model);

        // then
        assertEquals("redirect:/login", viewName);
        verify(model, never()).addAttribute(anyString(), any());
    }

    @Test
    public void testLogin() {
        // when
        String viewName = homeController.login();

        // then
        assertEquals("login", viewName);
    }

    @Test
    public void testLogout() {
        var originalContext = SecurityContextHolder.getContext();
        try {
            // when
            String viewName = homeController.logout(request, response);

            // then
            assertEquals("redirect:/login?logout", viewName);
            assertNull(SecurityContextHolder.getContext().getAuthentication(), "After logout security context should be cleared.");
        } finally {
            SecurityContextHolder.setContext(originalContext);
        }
    }
}
