package br.com.crossgame.matchmaking.internal.repository;

import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.utils.QueryBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
public class CustomUserFilterRepository {

    private EntityManager entityManager;

    public List<User> findAllUsersByFilter(){
        List<User> users = entityManager.createQuery(QueryBuilder.createQuery()).getResultList();
        return users;
    }
}
