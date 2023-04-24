package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.OrderListByName;
import br.com.crossgame.matchmaking.internal.entity.Friends;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.exception.ListIsEmptyException;
import br.com.crossgame.matchmaking.internal.utils.ListaObj;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultOrderListByName implements OrderListByName {
    @Override
    public List<Friends> execute(List<Friends> friends) {
        return sortList(friends);
    }
    public static List<Friends> sortList(List<Friends> lista)  {
        if (lista.isEmpty()) {
            return new ArrayList<>();
        }

        ListaObj<Friends> friendsListaObj = new ListaObj<>(lista.size());
        for (Friends f : lista) {
            friendsListaObj.adiciona(f);
        }

        int n = friendsListaObj.getTamanho();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String nome = friendsListaObj.buscaPorIndice(j).get().getUsername();
                String nome2 = friendsListaObj.buscaPorIndice(j+1).get().getUsername();
                if (nome.compareToIgnoreCase(nome2) > 0) {
                    friendsListaObj.trocarValoresDePosicao(j, j+1);
                }
            }
        }

        // Converter a lista ordenada de volta para uma lista Java
        lista.clear();
        for (int i = 0; i < friendsListaObj.getTamanho(); i++) {
            lista.add(friendsListaObj.buscaPorIndice(i).get());
        }
        return lista;
    }

    @Override
    public List<User> executeUser(List<User> users) {
        return sortListUser(users);
    }

    public static List<User> sortListUser(List<User> lista)  {
        if (lista.isEmpty()) {
            return new ArrayList<>();
        }

        ListaObj<User> userListaObj = new ListaObj<>(lista.size());
        for (User u : lista) {
            userListaObj.adiciona(u);
        }

        int n = userListaObj.getTamanho();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String nome = userListaObj.buscaPorIndice(j).get().getUsername();
                String nome2 = userListaObj.buscaPorIndice(j+1).get().getUsername();
                if (nome.compareToIgnoreCase(nome2) > 0) {
                    userListaObj.trocarValoresDePosicao(j, j+1);
                }
            }
        }

        // Converter a lista ordenada de volta para uma lista Java
        lista.clear();
        for (int i = 0; i < userListaObj.getTamanho(); i++) {
            lista.add(userListaObj.buscaPorIndice(i).get());
        }
        return lista;
    }
}
