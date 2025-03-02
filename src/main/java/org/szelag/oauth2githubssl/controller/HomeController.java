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
        if (oauth2User.getAttribute("html_url") != null) {
            model.addAttribute("html_url", oauth2User.getAttribute("html_url"));
        }
    }

    private void isBio(OAuth2User oauth2User, final Model model) {
        if (oauth2User.getAttribute("bio") != null) {
            model.addAttribute("bio", oauth2User.getAttribute("bio"));
        }
    }

    private void isLocation(OAuth2User oauth2User, final Model model) {
        if (oauth2User.getAttribute("location") != null) {
            model.addAttribute("location", oauth2User.getAttribute("location"));
        }
    }

    private void isName(OAuth2User oauth2User, final Model model) {
        if (oauth2User.getAttribute("name") != null) {
            model.addAttribute("name", oauth2User.getAttribute("name"));
        }
    }

    private void isAvatarUrl(OAuth2User oauth2User, final Model model) {
        if (oauth2User.getAttribute("avatar_url") != null) {
            model.addAttribute("avatar_url", oauth2User.getAttribute("avatar_url"));
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