package br.com.alura.forum;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {


    @Value("${forum.jwt.expiration}")
    private Long exp;

    @Value("${forum.jwt.secret}")
    private String secret;
    public String gerarToken(Authentication authentication) {
        Date date = new Date();
        Usuario logado = (Usuario) authentication.getPrincipal();
        return Jwts.builder()
                .setIssuer("alura.com")
                .setSubject(logado.getId().toString())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + exp))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
