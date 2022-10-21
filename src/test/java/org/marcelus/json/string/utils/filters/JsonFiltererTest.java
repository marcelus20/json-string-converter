package org.marcelus.json.string.utils.filters;

import org.junit.Assert;
import org.junit.Test;
import org.marcelus.json.string.utils.testclassess.PersonWithNameAndAge;

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

    @Test
    public void filterShouldReturnAnEmptyJSONWhenKeysAreNotExisting(){
        // Given
        String jsonString = "{\"name\":\"Foo\",\"surname\":\"Bar\",\"age\":25,\"address\":\"fizz buzz\"}";
        String filteredJsonString = "{}";

        // When
        String resultingJson = JsonFilterer.filter(jsonString, "id", "height");

        // Then
        Assert.assertEquals(filteredJsonString, resultingJson);
    }

    @Test
    public void filterShouldFilterJSONStringBasedOnAJavaClass(){
        // Given
        String jsonString = "{\"name\":\"Foo\",\"surname\":\"Bar\",\"age\":25,\"address\":\"fizz buzz\"}";
        String filteredJsonString = "{\"name\":\"Foo\",\"age\":25}";

        // When
        String resultingJson = JsonFilterer.filter(jsonString, PersonWithNameAndAge.class);

        // Then
        Assert.assertEquals(filteredJsonString, resultingJson);
    }

}