package org.marcelus.json.string.utils.filters;

import org.junit.Assert;
import org.junit.Test;

public class JsonFiltererTest {

    @Test
    public void filterShouldFilterJSONStringIntoAnotherJSONStringContainingTheFilteredKeys(){
        // Given
        String jsonString = "{\"name\":\"Foo\",\"surname\":\"Bar\",\"age\":25,\"address\":\"fizz buzz\"}";
        String filteredJsonString = "{\"name\":\"Foo\",\"age\":25}";

        // When
        String resultingJson = JsonFilterer.filter(jsonString, "name", "age");

        // Then
        Assert.assertEquals(filteredJsonString, resultingJson);
    }

}