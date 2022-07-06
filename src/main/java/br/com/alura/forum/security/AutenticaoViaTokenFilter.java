package br.com.alura.forum.security;

import br.com.alura.forum.TokenService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AutenticaoViaTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public AutenticaoViaTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token != null && !token.isEmpty()) {
            Boolean tokenValido = tokenService.isTokenValido(token);
            System.out.println(tokenValido);
        }

        filterChain.doFilter(request, response);
    }
}
