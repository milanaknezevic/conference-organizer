package com.example.pisioconf_backend.services.impl;


import com.example.pisioconf_backend.exception.UnauthorizedException;
import com.example.pisioconf_backend.models.dto.JwtUser;
import com.example.pisioconf_backend.models.dto.LoginResponse;
import com.example.pisioconf_backend.models.requests.LoginRequest;
import com.example.pisioconf_backend.services.AuthService;
import com.example.pisioconf_backend.services.KorisnikService;
import com.example.pisioconf_backend.util.LoggingUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthImpl implements AuthService {

    public final KorisnikService korisnikService;
    public final AuthenticationManager authenticationManager;


    @Value("${authorization.token.expiration-time}")
    private String tokenExpirationTime;
    @Value("${authorization.token.secret}")
    private String tokenSecret;

    public AuthImpl(KorisnikService korisnikService, AuthenticationManager authenticationManager) {
        this.korisnikService = korisnikService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        LoginResponse response = null;
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(),request.getPassword()
                            )
                    );
            JwtUser user = (JwtUser) authenticate.getPrincipal();
            response = korisnikService.findById(user.getId(), LoginResponse.class);
            response.setToken(generateJwt(user));
        } catch (Exception ex) {
            LoggingUtil.logException(ex, getClass());
            throw new UnauthorizedException();
        }
        return response;
    }


    private String generateJwt(JwtUser user) {
        return Jwts.builder()
                .setId(user.getId().toString())
                .setSubject(user.getUsername())
                .claim("role", user.getRole().name())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(tokenExpirationTime)))
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }
}
