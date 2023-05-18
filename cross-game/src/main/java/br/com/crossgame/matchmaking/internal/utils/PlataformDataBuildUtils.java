package br.com.crossgame.matchmaking.internal.utils;

import br.com.crossgame.matchmaking.api.model.PlataformData;
import br.com.crossgame.matchmaking.internal.entity.Plataform;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class PlataformDataBuildUtils {

    public static List<PlataformData> transform(List<Plataform> plataforms){
        List<PlataformData> plataformDataList = new ArrayList<>();
        for (Plataform plataform : plataforms){
            plataformDataList.add(new PlataformData(plataform.getId(),
                    plataform.getPlataformType()));
        }
        return plataformDataList;
    }
}
