package mx.devg.reductions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import mx.devg.model.User;


public class ReduceTest {
	
	
	
	@Test
	public void testSimpleReduction() {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		
		int result = numbers.stream().reduce(0, (subtotal, element) -> subtotal + element);
		
		Assert.assertEquals(21, result);
		
	}
	
	@Test
	public void testSimpleReductionNoIdentity() {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		
		Optional<Integer> result = numbers.stream().reduce((subtotal, element) -> subtotal + element);
		
		Assert.assertEquals((Integer)21, result.get());
		
	}
	
	@Test
	public void testSimpleMethodReference() {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		
		int result = numbers.stream().reduce(0, Integer::sum);
		
		Assert.assertEquals(21, result);
		
	}

	@Test
	public void testSimpleString() {
		List<String> list = Arrays.asList("a", "b", "c", "d");
		
		String result = list.stream().reduce("", (partialString, element) -> partialString + element);
		
		Assert.assertEquals("abcd", result);
		
	}
	
	@Test
	public void testSimpleStringMethodReference() {
		List<String> list = Arrays.asList("a", "b", "c", "d");
		
		String result = list.stream().reduce("", String::concat);
		
		Assert.assertEquals("abcd", result);
		
	}
	
	@Test
	public void testReduceCombinerDifferentTypes() {
		List<User> list = Arrays.asList(new User("John", 30), new User("Julie", 35), new User("Charly", 35));
		
		int result = list.stream().reduce(0, (subtotal, user) -> subtotal + user.getAge(), Integer::sum);
//		int result = list.stream().reduce(0, (subtotal, user) -> subtotal + user.getAge(), (res1, res2) -> res1 + res2);
		
		Assert.assertEquals(100, result);
		
	}
	
}
