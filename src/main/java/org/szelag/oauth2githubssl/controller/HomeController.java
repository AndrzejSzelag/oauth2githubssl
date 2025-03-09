package org.szelag.oauth2githubssl.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @GetMapping(value = "/home")
    public String home(@AuthenticationPrincipal OAuth2User oauth2User, final Model model) {
        if (oauth2User != null) {
            isAvatarUrl(oauth2User, model);
            isName(oauth2User, model);
            isLocation(oauth2User, model);
            isBio(oauth2User, model);
            isHtmlUrl(oauth2User, model);
            return "home";
        } else {
            return "redirect:/login";
        }
    }

    private void isHtmlUrl(OAuth2User oauth2User, final Model model) {
        final String HTML_URL = "html_url";
        if (oauth2User.getAttribute(HTML_URL) != null) {
            model.addAttribute(HTML_URL, oauth2User.getAttribute(HTML_URL));
        }
    }

    private void isBio(OAuth2User oauth2User, final Model model) {
        final String BIO = "bio";
        if (oauth2User.getAttribute(BIO) != null) {
            model.addAttribute(BIO, oauth2User.getAttribute(BIO));
        }
    }

    private void isLocation(OAuth2User oauth2User, final Model model) {
        final String LOCATION = "location";
        if (oauth2User.getAttribute(LOCATION) != null) {
            model.addAttribute(LOCATION, oauth2User.getAttribute(LOCATION));
        }
    }

    private void isName(OAuth2User oauth2User, final Model model) {
        final String NAME = "name";
        if (oauth2User.getAttribute(NAME) != null) {
            model.addAttribute(NAME, oauth2User.getAttribute(NAME));
        }
    }

    private void isAvatarUrl(OAuth2User oauth2User, final Model model) {
        final String AVATAR_URL = "avatar_url";
        if (oauth2User.getAttribute(AVATAR_URL) != null) {
            model.addAttribute(AVATAR_URL, oauth2User.getAttribute(AVATAR_URL));
        }
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        return "redirect:/login?logout";
    }
}
