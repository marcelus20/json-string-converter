package org.marcelus.json.string.utils.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JsonFilterer {

    private static ObjectMapper instantiateObjectMapper(){
        return new ObjectMapper();
    }
    public static String filter(String jsonString, String... keys) {
        return Optional.ofNullable(jsonString)
                .map(js-> {
                    try {
                        return instantiateObjectMapper().readValue(js, new TypeReference<Map<String,Object>>(){});
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(map-> {
                    try {
                        return instantiateObjectMapper().writeValueAsString(map.entrySet().stream()
                            .filter(keyValuePair-> Arrays.asList(keys).contains(keyValuePair.getKey()))
                            .collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue()))
                    );
                    } catch (JsonProcessingException ex) {
                        throw new RuntimeException(ex);
                    }
                }).orElse("");
    }

}
