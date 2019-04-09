package mx.devg.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;

public class BenchMarkTest {
	@Benchmark
	public void init() {
		// Do nothing
	}

	public static void main(String[] args) throws Exception {
		org.openjdk.jmh.Main.main(args);
	}

}
