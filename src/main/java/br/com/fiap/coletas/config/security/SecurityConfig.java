package br.com.fiap.coletas.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

    @Bean
    public SecurityFilterChain filtrarCadeiaSeguranca (
            HttpSecurity httpSecurity) throws Exception{

        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        //EndPoints Cadastro de Empresas
                        .requestMatchers(HttpMethod.GET, "/api/empresas").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/cadastro").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/empresas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/empresas/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/empresas/{id}").hasAnyRole("ADMIN", "USER")
                        //FIM EndPoints Cadastro de Empresas
                        //EndPoints Cadastro de Cooperativas
                        .requestMatchers(HttpMethod.GET, "/api/cooperativas").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/cadastro-cooperativa").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/cooperativas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/cooperativas/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/cooperativas/{id}").hasAnyRole("ADMIN", "USER")
                        //FIM EndPoints Cadastro de Cooperativas
                        //EndPoints Cadastro de Solicitações de Coleta
                        .requestMatchers(HttpMethod.GET, "/api/solicitacoes").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/cadastro-solicitacao").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/solicitacoes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/solicitacoes/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/solicitacoes/{id}").hasAnyRole("ADMIN", "USER")
                        //FIM EndPoints Cadastro de Solicitações de Coleta
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(
                        verificarToken,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
