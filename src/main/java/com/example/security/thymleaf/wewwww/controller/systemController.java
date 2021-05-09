package com.example.security.thymleaf.wewwww.controller;

import com.example.security.thymleaf.wewwww.enitty.Account;
import com.example.security.thymleaf.wewwww.service.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
public class systemController {
    private final AccountService accountService;

    public systemController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String requestRoot(Authentication authResult) {
        return common(authResult);
    }

    @GetMapping("/index")
    public String index(Authentication authResult) {
        return common(authResult);
    }

    @GetMapping("/signup")
    public String signup(@ModelAttribute("AccountDTO") Account account, Model model) {
        model.addAttribute("AccountDTO", Account.builder().build());
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@ModelAttribute("AccountDTO") Account account, Model model) {
        model.addAttribute("AccountDTO", account);
        return accountService.register(account);
    }

    @GetMapping("/hellFromTom")
    public String hellFromTom() {
        return "hellFromTom";
    }

    @GetMapping("/helloFromAdmin")
    public String hellFromAdmin() {
        return "helloFromAdmin";
    }

    @GetMapping("/helloFromUser")
    public String hellFromUser() {
        return "helloFromUser";
    }

    private String common(Authentication authResult) {
        String url = "index";
        if (authResult !=null) {
            String role = authResult
                    .getAuthorities()
                    .stream()
                    .collect(Collectors.toList())
                    .get(0)
                    .getAuthority();
            switch (role) {
                case "ROLE_ADMIN":
                    url = "helloFromAdmin";
                    break;

                case "ROLE_TOM":
                    url = "hellFromTom";
                    break;

                case "ROLE_USER":
                    url = "helloFromUser";
                    break;
            }
        }
        return url;
    }

    @GetMapping("/login")
    public String login(Authentication authResult) {
        if (authResult==null) {
            return "login";
        }
        return "redirect:index";
    }
}
