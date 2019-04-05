package mx.devg.benchmarks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import mx.devg.model.User;

@State(Scope.Thread)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class ReductionBenchMark {

	private final List<User> userList = createUsers();

	@Benchmark
	public Integer executeReduceOnParallelizedStream() {
		return this.userList.parallelStream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(),
				Integer::sum);
	}

	@Benchmark
	public Integer executeReduceOnSequentialStream() {
		return this.userList.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(),
				Integer::sum);
	}

	private List<User> createUsers() {
		IntStream intStream = IntStream.range(0, 10);
		List<User> listUser = new ArrayList<>();
		intStream.forEach(num -> listUser.add(new User("User" + num, num)));
		return listUser;
	}

	public static void main(String[] args) throws Exception {
		// org.openjdk.jmh.Main.main(args);

		Options opt = new OptionsBuilder().include(ReductionBenchMark.class.getSimpleName()).forks(1).build();

		new Runner(opt).run();
	}

}
