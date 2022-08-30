package com.digitaldot.utils.enviroment;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static java.lang.String.format;

@Component
public class EnvFile {
    private static final String PATH;

    public String get(String key) {
        return configurationPath().get(key);
    }

    private Map<String, String> configurationPath() {

        Map<String, String> propertiesValues;

        try (InputStream input = new FileInputStream(PATH)) {

            Properties properties = new Properties();
            properties.load(input);

            propertiesValues = convertMap(properties);

            return propertiesValues;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Map<String, String> convertMap(Map<?, ?> keyMap) {

        Map<String, String> maps = new HashMap<>();

       keyMap.keySet().forEach( key -> maps.put(key.toString(), keyMap.get(key).toString()));

        return maps;
    }

    static {
        final String HOME = System.getProperty("user.home");
        PATH = format("%1$s/temp/config.properties", HOME);
    }
}
