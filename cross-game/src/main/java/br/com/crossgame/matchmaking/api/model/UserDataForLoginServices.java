package br.com.crossgame.matchmaking.api.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public record UserDataForLoginServices(@Size(min = 3, message = "username must contain at least 3 characters")
                                       String username,
                                       @Email(message = "must be an email")
                                       String email,
                                       String password) {
}
