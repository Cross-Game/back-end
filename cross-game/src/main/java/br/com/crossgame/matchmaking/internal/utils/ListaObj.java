package br.com.crossgame.matchmaking.internal.utils;

import lombok.Getter;

@Getter
public class ListaObj<T>  {

        private T[] vetor;
        private int nroElem;

        public ListaObj(int tamanho) {
            vetor = (T[]) new Object[tamanho];
            nroElem = 0;
        }

        public void adiciona(T elemento) {
            if (nroElem >= vetor.length) {
                System.out.println("Lista está cheia");
            } else {
                vetor[nroElem++] = elemento;
            }
        }

        public void exibe() {
            if (nroElem == 0) {
                System.out.println("\nA lista está vazia.");
            } else {
                System.out.println("\nElementos da lista:");
                for (int i = 0; i < nroElem; i++) {
                    System.out.println(vetor[i]);
                }
            }
        }

        public int busca(T elementoBuscado) {
            for (int i = 0; i < nroElem; i++) {
                if (vetor[i].equals(elementoBuscado)) {
                    return i;
                }
            }
            return -1;
        }

        public boolean removePeloIndice(int indice) {
            if (indice < 0 || indice >= nroElem) {
                System.out.println("\nÍndice inválido!");
                return false;
            }
            for (int i = indice; i < nroElem - 1; i++) {
                vetor[i] = vetor[i + 1];
            }
            nroElem--;
            return true;
        }

        public boolean removeElemento(T elementoARemover) {
            return removePeloIndice(busca(elementoARemover));
        }

        public int getTamanho() {
            return nroElem;
        }

        public void limpa() {
            nroElem = 0;
        }

        public T getElemento(int indice) {
            if (indice < 0 || indice >= nroElem) {
                return null;
            } else {
                return vetor[indice];
            }
        }

        public boolean substitui(T valorAntigo, T valorNovo) {
            int indice = busca(valorAntigo);
            if (indice != -1) {
                vetor[indice] = valorNovo;
                return true;
            }
            System.out.println("Valor não encontrado");
            return false;
        }

        public int contaOcorrencias(T valor) {
            int contador = 0;
            for (int i = 0; i < nroElem; i++) {
                if (vetor[i] == valor) {
                    contador++;
                }
            }
            return contador;
        }

        public boolean adicionarNoInicio(T valor) {
            if (nroElem == vetor.length) {
                System.out.println("Lista cheia!");
                return false;
            }
            for (int i = nroElem - 1; i >= 0; i--) {
                vetor[i + 1] = vetor[i];
            }
            vetor[0] = valor;
            nroElem++;
            return true;
        }



    }


