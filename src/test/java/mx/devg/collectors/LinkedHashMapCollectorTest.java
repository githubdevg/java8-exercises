package mx.devg.collectors;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class LinkedHashMapCollectorTest {


    @Test
    public void testLinkedHashMapCollector() {
        List<String> stringsList = Arrays.asList("One", "Two", "Three");

        Function<String, Integer> keyMapperFunction = s -> s.length();
        Function<String, String> valueMapperFunction = Function.identity();


        Map<Integer, String> linkedHashMap = stringsList.stream()


    }

}
