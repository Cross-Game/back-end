package br.com.crossgame.matchmaking.internal.utils;

public class PilhaObj<T> {

    private T[] pilha;
    private int topo;
    private int capacidade;

    public PilhaObj(int capacidade) {
        this.capacidade = capacidade;
        pilha =(T[]) new Object[capacidade];
        topo = -1;
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public boolean isFull() {
        return topo == capacidade - 1;
    }

    public void push(T info) {
        if (isFull()) {
            throw new IllegalStateException("Pilha cheia!");
        } else {
            pilha[++topo] = info;
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T info = (T) pilha[topo];
        pilha[topo--] = null;
        return info;
    }
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T) pilha[topo];
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha está vazia!");
            return;
        }
        for (int i = topo; i >= 0; i--) {
            System.out.println(pilha[i]);
        }
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getTopo() {
        return topo;
    }
}
