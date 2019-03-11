package mx.devg.collectors;

import com.google.common.collect.ImmutableSet;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ImmutableSetCollectorTest {

    @Test
    public void testImmutableSetCollector() {
        List<String> list = Arrays.asList("a", "bb", "ccc", "dddd");

        ImmutableSet<String> immutableSet = list.stream().collect(ImmutableSetCollector.toImmutableSet());

        System.out.println(immutableSet.iterator().hasNext());

        Assert.assertTrue(immutableSet.isEmpty());

    }

}
