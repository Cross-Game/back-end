package br.com.crossgame.matchmaking.internal.exception;

import java.rmi.UnexpectedException;

public class ListIsEmptyException extends UnexpectedException {

    public ListIsEmptyException(String s) {
        super(s);
    }
}
