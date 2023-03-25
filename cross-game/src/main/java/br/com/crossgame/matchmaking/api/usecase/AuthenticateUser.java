package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticateUser extends UserDetailsService {

    User execute(User user);
}
