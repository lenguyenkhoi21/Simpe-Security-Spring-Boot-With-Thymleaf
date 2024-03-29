package com.example.security.thymleaf.wewwww.service;

import com.example.security.thymleaf.wewwww.enitty.Account;
import com.example.security.thymleaf.wewwww.repo.AccountRepo;
import com.example.security.thymleaf.wewwww.security.UserPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountDetailSerrvice implements UserDetailsService {
    private final AccountRepo repo;

    public AccountDetailSerrvice(AccountRepo repo) {
        this.repo = repo;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repo
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username "  + username));

        return new UserPrincipal(account);
    }
}
