package br.com.crossgame.matchmaking.api.usecase;

import br.com.crossgame.matchmaking.internal.entity.ImageGame;
import br.com.crossgame.matchmaking.internal.entity.enums.TypeImage;

public interface RetrieveImageGame {
    ImageGame execute(Integer coverId, TypeImage typeImage);
}
