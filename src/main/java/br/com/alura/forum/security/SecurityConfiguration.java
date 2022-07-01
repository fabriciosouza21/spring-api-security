package br.com.alura.forum.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // Configuração de autorização de acesso aos endpoints
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //Adicionando acesso
                .antMatchers(HttpMethod.GET, "/topicos").permitAll()
                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
                //forma de autienticação
                .anyRequest().authenticated().and().formLogin();
        ;
    }
}
