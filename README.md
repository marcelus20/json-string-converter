## JSON string Converter.

### To build:
Clone the project if you haven't done so:
```shell
git clone https://github.com/marcelus20/json-string-converter
```
Then build using the mvn command:
```shell
mvn clean install
```

### Usage:
##### Use case 1: 
Convert a bigger json string into a smaller json string. The smaller json will contain the keys passed in the function argument. 
PS: Any key argument passed that doesn't exist in the json string will make the function return an empty json string ("{}"). 
```java
import org.marcelus.json.string.utils.filters;

public class Main{
    public static void main(String... args){
        
        String bigJsonString = "{\"foo\":\"bar\",\"fizz\":\"buzz\":\"fazz\",\"bazz\":\"far\",\"bizz\":\"fuzz\"}";
        
        // generate a new json string containing only the foo and the fizz keys:
        String smallerJsonString = JsonFilterer.filter(bigJsonString, "foo", "fizz");
        
        // smallerJsonString will be {"foo":"bar","fizz":"buzz"}
    }
}
    

```

---
#### Use Case 2
Convert a bigger json into a smaller json, but enforcing the smaller json string to follow a class Schema. 

In this use case, the filter method will keep the json keys that have the same name as the **class fields**.

PS: The class field names **must** be identical to the json keys. It is case sensitive! 
```java
import org.marcelus.json.string.utils.filters;

public class Main{
    
    public class FooFizz{
        private String foo;
        private String fizz;
        
        //.. Getters and setters
    }
    
    public static void main(String... args){
        
        String bigJsonString = "{\"foo\":\"bar\",\"fizz\":\"buzz\":\"fazz\",\"bazz\":\"far\",\"bizz\":\"fuzz\"}";
        
        // Generating a new JSON following the FooFizz class schema: 
        String smallerJsonString = JsonFilterer.filter(bigJsonString, FooFizz.class);
        
        // smallerJsonString will be "{\"foo\":\"bar\",\"fizz\":\"buzz\"}"
    }
}
    

```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
