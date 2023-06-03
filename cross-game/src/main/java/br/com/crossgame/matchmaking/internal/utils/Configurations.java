package br.com.crossgame.matchmaking.internal.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configurations {
    private static final String ARQUIVO_CONFIGURACAO = "config.properties";
    private static final String PROPRIEDADE_API_KEY = "API_KEY";
    private static String apiKey;

    static {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(ARQUIVO_CONFIGURACAO);
            properties.load(fileInputStream);
            apiKey = properties.getProperty(PROPRIEDADE_API_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getApiKey() {
        return apiKey;
    }
}
