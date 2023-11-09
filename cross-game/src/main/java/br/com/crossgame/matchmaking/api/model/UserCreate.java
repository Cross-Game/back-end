package br.com.crossgame.matchmaking.api.model;

import br.com.crossgame.matchmaking.internal.entity.enums.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UserCreate(Long id,
                        @Size(min = 3, message = "username must contain at least 3 characters")
                          String username,
                          @Email(message = "must be an email")
                         String email,
                          @Size(min = 12, message = "password must contain at least 12 characters")
                         String password,
                         @NotNull
                         Role role) {
}
