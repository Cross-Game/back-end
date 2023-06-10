package br.com.crossgame.matchmaking.internal.utils;

import br.com.crossgame.matchmaking.api.model.PreferenceData;
import br.com.crossgame.matchmaking.internal.entity.Preference;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class PreferenceDataBuldUtils {

    public static List<PreferenceData> transform(List<Preference> preferences){
        List<PreferenceData> preferenceData = new ArrayList<>();
        for (Preference preference : preferences){
            preferenceData.add(new PreferenceData(preference.getId(), preference.getPreferences()));
        }
        return preferenceData;
    }
}
