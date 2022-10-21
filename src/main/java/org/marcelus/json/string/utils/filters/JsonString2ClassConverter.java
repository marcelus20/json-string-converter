package org.marcelus.json.string.utils.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class isn't supposed to be used outside this package.
 * @param <T>
 */
class JsonString2ClassConverter <T>{

    private final Class<T> type;

    public JsonString2ClassConverter(Class<T> type) {
        this.type = type;
    }

    public T convert(String jsonString){
        try {
            return new ObjectMapper().readValue(jsonString, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
