package com.example.security.thymleaf.wewwww.repo;

import com.example.security.thymleaf.wewwww.enitty.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
}
