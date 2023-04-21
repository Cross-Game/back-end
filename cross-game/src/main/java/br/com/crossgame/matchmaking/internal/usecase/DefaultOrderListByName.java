package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.OrderListByName;
import br.com.crossgame.matchmaking.internal.entity.Friends;
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
    private List<Friends> sortList(List<Friends> lista)  {
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





}
