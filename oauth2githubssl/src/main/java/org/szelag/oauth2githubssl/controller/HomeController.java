package org.szelag.oauth2githubssl.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/home")
    public String home(@AuthenticationPrincipal OAuth2User oauth2User, final Model model) {
        if (oauth2User != null) {
            model.addAttribute("avatar_url", oauth2User.getAttribute("avatar_url"));
            model.addAttribute("name", oauth2User.getAttribute("name"));
            model.addAttribute("location", oauth2User.getAttribute("location"));
            model.addAttribute("bio", oauth2User.getAttribute("bio"));
            model.addAttribute("html_url", oauth2User.getAttribute("html_url"));
            return "home";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        // null if no further authentication information should be stored
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/login";
    }
}
