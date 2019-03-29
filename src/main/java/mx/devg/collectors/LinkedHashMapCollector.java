package mx.devg.collectors;


import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class LinkedHashMapCollector<T extends Function<T, R>, R> implements Collector<T, LinkedHashMap<Object, Object>, LinkedHashMap<Object, Object>> {

    @Override
    public Supplier<LinkedHashMap<Object, Object>> supplier() {
        return LinkedHashMap::new;
    }

    @Override
    public BiConsumer<LinkedHashMap<Object, Object>, T> accumulator() {
        return (map, R) -> map.put(R, T);
    }

    @Override
    public BinaryOperator<LinkedHashMap<Object, Object>> combiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    @Override
    public Function<LinkedHashMap<Object, Object>, LinkedHashMap<Object, Object>> finisher() {
        return (map -> map);
    }

    @Override
    public Set<Collector.Characteristics> characteristics() {
        return Collections.singleton(Characteristics.IDENTITY_FINISH);
    }
}
