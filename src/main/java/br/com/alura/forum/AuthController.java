package br.com.alura.forum;

import br.com.alura.forum.controller.form.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping
    public ResponseEntity<?> auth(@Valid @RequestBody LoginForm form) {
        System.out.println(form.getEmail());
        System.out.println(form.getSenha());
        return ResponseEntity.ok().build();
    }

  }

