package mx.devg.collectors;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.function.Function;
import java.util.function.Supplier;

public class CustomMapCollectorTest {


    @Test
    public void testCustomMapCollector() {
        List<String> list = Arrays.asList("One", "Two", "Three");


        Supplier<LinkedHashMap<String, String>> mapSupplier = LinkedHashMap::new;
        Function<String, String> keyMapper = Function.identity();
        Function<String, String> valueMapper = Function.identity();
        CustomMapCollector<String, String, String, LinkedHashMap<String, String>> customMapCollector = CustomMapCollector.
                toMap(keyMapper, valueMapper, mapSupplier);

        LinkedHashMap<String, String> map = list.stream().collect(customMapCollector);

        map.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));

    }

    @Test
    public void testCustomMapCollectorTwo() {
        List<String> list = Arrays.asList("One", "Two", "Three");
        LinkedHashMap<String, String> map = list.stream().collect(CustomMapCollector.toMap(
                Function.identity(), Function.identity(), LinkedHashMap::new
        ));
        map.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
    }

}