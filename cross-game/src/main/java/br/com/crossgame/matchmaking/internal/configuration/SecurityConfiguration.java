package br.com.crossgame.matchmaking.internal.configuration;

import br.com.crossgame.matchmaking.api.usecase.AuthenticateUser;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String USERS_ALL_URL = "/users/**";

    @Autowired
    private AuthenticateUser authenticateUser;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2-console/**").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, USERS_ALL_URL).permitAll()
                .antMatchers(HttpMethod.PUT, USERS_ALL_URL).permitAll()
                .antMatchers(HttpMethod.GET, USERS_ALL_URL).hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, USERS_ALL_URL).hasRole(Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticateUser)
                .passwordEncoder(passwordEncoder());
    }
}
