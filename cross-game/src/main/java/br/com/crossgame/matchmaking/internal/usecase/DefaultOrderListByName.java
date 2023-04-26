package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.OrderListByName;
import br.com.crossgame.matchmaking.internal.entity.Friend;
import br.com.crossgame.matchmaking.internal.entity.User;
import br.com.crossgame.matchmaking.internal.utils.ListaObj;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class DefaultOrderListByName implements OrderListByName {
    @Override
    public List<Friend> execute(List<Friend> Friend) {
        return sortList(Friend);
    }
    public static List<Friend> sortList(List<Friend> lista)  {
        if (lista.isEmpty()) {
            log.error("Lista de amigos está vazia!");
        }

        ListaObj<Friend> FriendListaObj = new ListaObj<>(lista.size());
        for (Friend f : lista) {
            FriendListaObj.adiciona(f);
        }

        int n = FriendListaObj.getTamanho();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String nome = FriendListaObj.buscaPorIndice(j).get().getUsername();
                String nome2 = FriendListaObj.buscaPorIndice(j+1).get().getUsername();
                if (nome.compareToIgnoreCase(nome2) > 0) {
                    FriendListaObj.trocarValoresDePosicao(j, j+1);
                }
            }
        }

        lista.clear();
        for (int i = 0; i < FriendListaObj.getTamanho(); i++) {
            lista.add(FriendListaObj.buscaPorIndice(i).get());
        }
        return lista;
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

        lista.clear();
        for (int i = 0; i < userListaObj.getTamanho(); i++) {
            lista.add(userListaObj.buscaPorIndice(i).get());
        }
        return lista;
    }
}



