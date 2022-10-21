package org.marcelus.json.string.utils.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JsonFilterer {

    /**
     * Instantiates Object Mapper
     * @return
     */
    private static ObjectMapper instantiateObjectMapper(){
        return new ObjectMapper();
    }

    /**
     * Converts a string in json format to a Map<String, Object> type.
     * @param jsonString
     * @return
     */
    private static Map<String, Object> convertJsonStringToMapObject(String jsonString){
        try {
            return instantiateObjectMapper().readValue(jsonString, new TypeReference<Map<String,Object>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts any object back to JSON
     * @param object
     * @return
     */
    private static String convertObjectToJSonString(Object object){
        try {
            return instantiateObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Filter a "bigger" map into a "smaller" map based on the keys.
     * @param map
     * @param keys
     * @return
     */
    private static Map<String, Object> filterMapObjectBasedOnKeys(Map<String, Object> map, String... keys){
            return filterMapObjectBasedOnKeys(map, Arrays.asList(keys));
    }

    /**
     * Filter a "bigger" map into a "smaller" map based on the keys. (Overloaded)
     * @param map
     * @param keys
     * @return
     */
    private static Map<String, Object> filterMapObjectBasedOnKeys(Map<String, Object> map, List<String> keys){
        return map.entrySet().stream()
                .filter(keyValuePair-> keys.contains(keyValuePair.getKey()))
                .collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue()));
    }


    /**
     * Filters a "bigger" json string into a "smaller" json string based on the keys.
     * @param jsonString
     * @param keys
     * @return
     */
    public static String filter(String jsonString, String... keys) {
        return Optional.ofNullable(jsonString)
                .map(JsonFilterer::convertJsonStringToMapObject)
                .map(map-> filterMapObjectBasedOnKeys(map, keys))
                .map(JsonFilterer::convertObjectToJSonString)
                .orElse("");
    }

    /**
     * Filters a "bigger" json string into a "smaller" json string based on a class definition.
     * @param jsonString
     * @param tClass
     * @return
     */
    public static <T> String filter(String jsonString, Class<T> tClass) {
        return Optional.ofNullable(jsonString)
                .map(JsonFilterer::convertJsonStringToMapObject)
                .map(map->filterMapObjectBasedOnKeys(map, Arrays.asList(tClass.getDeclaredFields())
                        .stream()
                        .map(field->field.getName())
                        .collect(Collectors.toList())))
                .map(JsonFilterer::convertObjectToJSonString)
                .map(filteredJsonString-> new JsonString2ClassConverter<T>(tClass).convert(filteredJsonString))
                .map(JsonFilterer::convertObjectToJSonString)
                .orElse("");
    }
}
