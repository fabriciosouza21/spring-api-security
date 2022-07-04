package br.com.alura.forum.controller;

import br.com.alura.forum.TokenService;
import br.com.alura.forum.controller.dto.TokenDTO;
import br.com.alura.forum.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> auth(@Valid @RequestBody LoginForm form) {
        try {
            UsernamePasswordAuthenticationToken dadosLogin = form.toAuthenticationToken();
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authenticate);

            return ResponseEntity.ok().body(new TokenDTO(token, "Bearer"));
        }
        catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }

    }

  }

