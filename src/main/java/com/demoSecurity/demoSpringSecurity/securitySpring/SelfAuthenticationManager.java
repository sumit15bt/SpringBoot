package com.demoSecurity.demoSpringSecurity.securitySpring;

import com.demoSecurity.demoSpringSecurity.dao.LoginLogoutDao;
import com.demoSecurity.demoSpringSecurity.dbBeans.UserDetails;
import com.demoSecurity.demoSpringSecurity.dbBeans.ct_domain;
import com.demoSecurity.demoSpringSecurity.dbBeans.ct_user;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Log4j2
public class SelfAuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private LoginLogoutDao loginLogoutDao;


    @Override
    public Mono<Authentication> authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String userAtDomain = authentication.getName();
            String password = authentication.getCredentials()
                    .toString();

            String[] parts = userAtDomain.split("@");
            if (parts.length != 2) {
                throw new BadCredentialsException("Invalid credentials");
            }
            String userName = parts[0];
            String domainName = parts[1];
            ct_domain domain = loginLogoutDao.findDomainByName(domainName)
                    .orElseThrow(() -> {
                        return new BadCredentialsException("Domain does not exists");
                    });

            ct_user user = loginLogoutDao.findUserByName(userName, domain.getId())
                    .orElseThrow(() -> {
                        return new BadCredentialsException("User does not exists");
                    });

            UserDetails userDetails = new UserDetails(userName,domainName);
            return Mono.just(new UsernamePasswordAuthenticationToken(userDetails, password, Collections.emptyList()));
        } catch (Exception ex) {
            log.error("Error in authentication -----> ", ex);
            throw new AuthenticationServiceException(ex.getMessage());
        }
    }
}
