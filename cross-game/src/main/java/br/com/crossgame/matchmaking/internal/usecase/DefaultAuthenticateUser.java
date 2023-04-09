package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.AuthenticateUser;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.exception.InvalidPasswordException;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@AllArgsConstructor
public class DefaultAuthenticateUser implements AuthenticateUser {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Override
    public User execute(User user) {
        UserDetails userToAuth = loadUserByUsername(user.getUsername());
        boolean passwordMatches = passwordEncoder.matches(user.getPassword(), userToAuth.getPassword());
        if(passwordMatches){
            User userAuthenticated = userRepository.findByUsername(userToAuth.getUsername())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            userAuthenticated.setOnline(true);
            return userRepository.save(userAuthenticated);
        }
        throw new InvalidPasswordException(HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with username = %s not found", username))
                );

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
