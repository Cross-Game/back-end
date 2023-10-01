package br.com.crossgame.matchmaking.internal.entity.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum GameplayPlatformType {
    PC(6),
    PLAYSTATION(48),
    XBOX(49),
    MOBILE(34, 39);

    private int[] ids;

    private GameplayPlatformType(int... ids) {
        this.ids = ids;
    }

    public static List<GameplayPlatformType> mapIdsToPlatforms(List<Integer> platformIds) {
        Map<Integer, GameplayPlatformType> platformIdMap = new HashMap<>();
        for (GameplayPlatformType platformType : values()) {
            for (int id : platformType.ids) {
                platformIdMap.put(id, platformType);
            }
        }

        List<GameplayPlatformType> platforms = new ArrayList<>();
        boolean hasMobile = false; // Flag para rastrear a presença de "MOBILE"

        for (int id : platformIds) {
            if (id == 34 || id == 39) {
                // Se for 34 ou 39 (MOBILE), verifique se já foi adicionado
                if (!hasMobile) {
                    platforms.add(GameplayPlatformType.MOBILE);
                    hasMobile = true; // Define a flag para true após a adição
                }
            } else {
                GameplayPlatformType platformType = platformIdMap.get(id);
                if (platformType != null) {
                    platforms.add(platformType);
                }
            }
        }

        return platforms;
    }

}
