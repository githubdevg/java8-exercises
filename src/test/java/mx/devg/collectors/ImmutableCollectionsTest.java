package mx.devg.collectors;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class ImmutableCollectionsTest {

    @Test
    public void whenUsingCollectingToImmutableList_thenSuccess() {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        list.add("d");

        List<String> immutableList = list.stream().collect(collectingAndThen(toList(), ImmutableList::copyOf));
        //immutableList.add("e"); //throws UnsoportedOperationException
        System.out.println(immutableList.getClass());

    }

    @Test
    public void whenCollectToImmutableList_thenSuccess() {
        List<Integer> list = IntStream.range(0,9).boxed().collect(ImmutableList.toImmutableList());

        System.out.println(list.getClass());

    }

    @Test
    public void whenCollectToMyImmutableListCollector_thenSuccess() {
        Collector<String, List, List> customCollector = Collector.of(ArrayList::new, List::add,
                (left, right) ->
                {left.addAll(right);
                    return left;
                }, Collections::unmodifiableList);

        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));

        List<String> result = list.stream().collect(customCollector);
        //result.add("d"); //throws UnsoportedOperationException

        System.out.println(result.getClass());

    }

    @Test
    public void whenPassingSupplier_thenSuccess() {
        Collector<String, List<String>, List<String>> customCollector = toImmutableList(LinkedList::new);
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));

        List<String> result = list.stream().collect(customCollector);
        //result.add("d"); //throws UnsoportedOperationException

        System.out.println(result.getClass());
    }

    private static <T, A extends List<T>> Collector<T, A, List<T>> toImmutableList(Supplier<A> supplier) {
        return Collector.of(supplier, List::add,
                (left, right) ->
                {left.addAll(right);
                    return left;
                }, Collections::unmodifiableList);
    }

}
