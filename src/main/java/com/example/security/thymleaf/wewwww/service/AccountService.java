package com.example.security.thymleaf.wewwww.service;

import com.example.security.thymleaf.wewwww.enitty.Account;
import com.example.security.thymleaf.wewwww.repo.AccountRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepo repo;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(Account account) {
        if (repo.findByUsername(account.getUsername()).isPresent()) {
            return "redirect:error";
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        repo.saveAndFlush(account);
        return "redirect:index";
    }
}
