package br.com.crossgame.matchmaking.internal.utils;

public class FilaObj<T> {
    private int tamanho;
    private T[] fila;

    public FilaObj(int capaciade) {
        this.tamanho = 0;
        this.fila = (T[]) new Object[capaciade];
    }

    public boolean isEmpty() {
        if (tamanho == 0){
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (tamanho == fila.length){
            return true;
        }
        return false;
    }

    public void insert(T info) {
        if (tamanho == fila.length){
            throw new IllegalStateException();
        }
        this.fila[tamanho] = info;
        tamanho++;
    }

    public T peek() {
        return this.fila[0];
    }

    public T poll() {
        T firstOfQueue = this.fila[0];
        for (int k = 0; k < fila.length; k++){
            if (k+1 < tamanho){
                this.fila[k] = this.fila[k+1];
            }
        }
        this.fila[tamanho-1] = null;
        tamanho--;
        return firstOfQueue;
    }

    public void exibe() {
        for (int k = 0; k < tamanho; k++){
            System.out.print(this.fila[k] + ", ");
        }
    }

    public int getTamanho(){
        return this.tamanho;
    }

    public T[] getFila() {
        return fila;
    }
}

