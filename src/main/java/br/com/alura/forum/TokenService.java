package br.com.alura.forum;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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

    public Boolean isTokenValido(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return true;

    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
