package br.com.crossgame.matchmaking.internal.exception;

import java.rmi.UnexpectedException;

public class ListIsEmptyExcepetion extends UnexpectedException {

    public ListIsEmptyExcepetion(String s) {
        super(s);
    }
}
