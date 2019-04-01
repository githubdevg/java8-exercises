package mx.devg.collectors;


import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CustomMapCollector<T, K, V, M extends Map<K, V>> implements Collector<T, M, M> {
	
	private Function<? super T, ? extends K> keyMapper;
	
	private Function<? super T, ? extends V>valueMapper;
	
	private Supplier<M> mapSuppiler;

    @Override
    public Supplier<M> supplier() {
        return mapSuppiler;
    }

    @Override
    public BiConsumer<M, T> accumulator() {    	
		return (map, t) -> {
			map.merge(keyMapper.apply(t), valueMapper.apply(t), (v1, v2) -> v2);
		};
    }

    @Override
    public BinaryOperator<M> combiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };    	
    }

    @Override
    public Function<M, M> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Collector.Characteristics> characteristics() {
        return Collections.singleton(Characteristics.IDENTITY_FINISH);
    }
}
