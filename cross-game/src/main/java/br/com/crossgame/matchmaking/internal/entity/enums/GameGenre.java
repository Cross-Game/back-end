package br.com.crossgame.matchmaking.internal.entity.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum GameGenre {
    AVENTURA(31, "Aventura"),
    POINT_AND_CLICK(2, "Point-and-click"),
    LUTA(4, "Luta"),
    TIRO(5, "Tiro"),
    MUSICA(7, "Música"),
    PLATAFORMA(8, "Plataforma"),
    QUEBRA_CABECA(9, "Quebra-cabeça"),
    CORRIDA(10, "Corrida"),
    ESTRATEGIA_RTS(11, "Estratégia em Tempo Real (RTS)"),
    RPG(12, "RPG (Role-playing)"),
    SIMULACAO(13, "Simulação"),
    ESPORTES(14, "Esportes"),
    ESTRATEGIA(15, "Estratégia"),
    ESTRATEGIA_TBS(16, "Estratégia por turnos (TBS)"),
    TATICO(24, "Tático"),
    HACK_AND_SLASH(25, "Hack and slash/Beat 'em up"),
    QUIZ_TRIVIA(26, "Quiz/Trivia"),
    PINBALL(30, "Pinball"),
    ARCADE(33, "Arcade"),
    VISUAL_NOVEL(34, "Visual Novel"),
    INDIE(32, "Indie"),
    JOGO_CARTAS_TABULEIRO(35, "Jogo de Cartas e Tabuleiro"),
    MOBA(36, "MOBA");

    private int id;
    private String nome;

    GameGenre(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static List<GameGenre> mapIdsToGenres(List<Integer> genreIds) {
        Map<Integer, GameGenre> genreIdMap = new HashMap<>();
        for (GameGenre genre : values()) {
            genreIdMap.put(genre.getId(), genre);
        }

        List<GameGenre> genres = new ArrayList<>();

        for (int id : genreIds) {
            GameGenre genre = genreIdMap.get(id);
            if (genre != null) {
                genres.add(genre);
            }
        }

        return genres;
    }
}
