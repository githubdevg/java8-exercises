package mx.devg.collectors;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ImmutableSetCollector<T> implements Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>> {

    @Override
    public Supplier<ImmutableSet.Builder<T>> supplier() {
        // return ImmutableSet::builder;
        return () -> ImmutableSet.builder();
    }

    @Override
    public BiConsumer<ImmutableSet.Builder<T>, T> accumulator() {
        //  return ImmutableSet.Builder::add;
        return (builder, T) -> builder.add(T);
    }

    @Override
    public BinaryOperator<ImmutableSet.Builder<T>> combiner() {
        return (result1, result2) -> result1.addAll(result2.build());
    }

    @Override
    public Function<ImmutableSet.Builder<T>, ImmutableSet<T>> finisher() {
        // return ImmutableSet.Builder::build;
        return immutableSet -> immutableSet.build();
    }

    @Override
    public Set<Characteristics> characteristics() {
//        Set<Characteristics> hashSetSet = new HashSet<>();
//        return hashSet;
        return Sets.immutableEnumSet(Characteristics.UNORDERED);
    }

    public static <T> ImmutableSetCollector<T> toImmutableSet() {
        return new ImmutableSetCollector<>();
    }

}
