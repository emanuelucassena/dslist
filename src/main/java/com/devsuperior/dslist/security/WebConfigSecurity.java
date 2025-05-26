package com.devsuperior.dslist.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Diz ao Spring que essa classe contém beans de configuração
public class WebConfigSecurity {


    // Cria um bean que define os usuários do sistema (armazenados em memória)
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Gerenciador de usuários em memória
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // Cria um usuário chamado "admin" com a senha "123456", codificada com BCrypt
        manager.createUser(User
                .withUsername("admin") // nome de usuário
                .password(passwordEncoder.encode("123456")) // senha codificada
                .roles("USER") // papel do usuário (pode ser usado para autorização)
                .build());

        // Retorna o gerenciador de usuários
        return manager;
    }

    // Cria um bean para codificar senhas usando o algoritmo BCrypt (recomendado)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // força padrão de 10
    }

    // Cria um bean que define as regras de segurança HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // qualquer requisição precisa de autenticação
                )
                .httpBasic() // habilita autenticação HTTP Basic
                .and()
                .csrf().disable(); // desabilita proteção CSRF (necessário para APIs REST)

        return http.build(); // retorna a configuração final
    }
}
