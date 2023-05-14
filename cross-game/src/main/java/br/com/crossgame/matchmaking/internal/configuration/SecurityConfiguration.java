package br.com.crossgame.matchmaking.internal.configuration;

import br.com.crossgame.matchmaking.api.usecase.AuthenticateUser;
import br.com.crossgame.matchmaking.internal.entity.enums.Role;
import br.com.crossgame.matchmaking.internal.security.JwtAuthFilter;
import br.com.crossgame.matchmaking.internal.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String USERS_ANY_URL = "/users/**";
    private static final String FRIENDS_ANY_URL = "/friends/**";
    private static final String FEEDBACK_ANY_URL = "/feedbacks/**";
    private static final String PREFERENCES_ANY_URL = "/preferences/**";
    private static final String GAME_ANY_URL = "/games/**";
    private static final String USER_GAME_ANY_URL = "/user-games/**";

    @Autowired
    private AuthenticateUser authenticateUser;

    @Autowired
    private JwtService jwtService;

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, authenticateUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2-console/**").hasRole(Role.ADMIN.name())
                .antMatchers("/user-auth").permitAll()
                .antMatchers(HttpMethod.POST, PREFERENCES_ANY_URL).authenticated()
                .antMatchers(HttpMethod.PUT, PREFERENCES_ANY_URL).authenticated()
                .antMatchers(HttpMethod.DELETE, PREFERENCES_ANY_URL).authenticated()
                .antMatchers(HttpMethod.GET, PREFERENCES_ANY_URL).authenticated()
                .antMatchers(HttpMethod.POST, GAME_ANY_URL).hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT, GAME_ANY_URL).hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, GAME_ANY_URL).hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, GAME_ANY_URL).authenticated()
                .antMatchers(HttpMethod.POST, USER_GAME_ANY_URL).authenticated()
                .antMatchers(HttpMethod.GET, USER_GAME_ANY_URL).authenticated()
                .antMatchers(HttpMethod.PUT, USER_GAME_ANY_URL).authenticated()
                .antMatchers(HttpMethod.DELETE, USER_GAME_ANY_URL).authenticated()
                .antMatchers(HttpMethod.POST, FEEDBACK_ANY_URL).authenticated()
                .antMatchers(HttpMethod.GET, FEEDBACK_ANY_URL).authenticated()
                .antMatchers(HttpMethod.PUT, FEEDBACK_ANY_URL).authenticated()
                .antMatchers(HttpMethod.DELETE, FEEDBACK_ANY_URL).authenticated()
                .antMatchers(HttpMethod.POST, FRIENDS_ANY_URL).authenticated()
                .antMatchers(HttpMethod.GET, FRIENDS_ANY_URL).authenticated()
                .antMatchers(HttpMethod.PATCH, FRIENDS_ANY_URL).authenticated()
                .antMatchers(HttpMethod.DELETE, FRIENDS_ANY_URL).authenticated()
                .antMatchers(HttpMethod.POST, USERS_ANY_URL).permitAll()
                .antMatchers(HttpMethod.PUT, USERS_ANY_URL).authenticated()
                .antMatchers(HttpMethod.GET, USERS_ANY_URL).hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, USERS_ANY_URL).hasRole(Role.ADMIN.name())
                    .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .addFilterBefore(this.jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticateUser)
                .passwordEncoder(passwordEncoder());
    }
}
