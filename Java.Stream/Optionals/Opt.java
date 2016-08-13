package optw;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Opt {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		String s = "s";
		String w = "w";

		Optional<String> opt = Optional.of(s);

		Optional<String> opt1 = Optional.ofNullable(w);

		Optional<String> opt2 = Optional.empty();
		
		List<Double> doubles = ThreadLocalRandom.current()
				.doubles(100, -1000.0d, 1000.0d)
				.boxed()
				.collect(Collectors.toList());
		
		List<Double> results = new ArrayList<Double>();
		
		doubles.stream()
			.forEach(
					(item) -> 
						Opt.sqrt(item)
							.flatMap(Opt::inverse)
							.ifPresent(results::add)
		);
		
		System.out.println(doubles.size());
		System.out.println(results.size());

		Function<Double, Stream<Double>> invSqrt = 
				item -> 
					Opt.inverse(item)
						.flatMap(Opt::sqrt)
						.map(Stream::of)
						.orElseGet(Stream::empty);
		
		List<Double> invSqrtOfDoubles = doubles.stream()
				.parallel()
				.flatMap(invSqrt)
				.collect(Collectors.toList());
		
		DoubleSummaryStatistics stats = invSqrtOfDoubles.stream()
				.mapToDouble(x -> x)
				.summaryStatistics();
		
		System.out.println(invSqrtOfDoubles.size());
		
		System.out.printf("\n%s%f\n%s%f\n%s%f\n%s%f\n%s%d\n",
				"Max: ", stats.getMax(),
				"Min: ", stats.getMin(),
				"Avg: ", stats.getAverage(),
				"Sum: ", stats.getSum(),
				"Cnt: ", stats.getCount());
		
		
	}

	public static Optional<Double> sqrt(Double d) {
		return d > 0d ? Optional.of(Math.sqrt(d)) : Optional.empty();
	}

	public static Optional<Double> inverse(Double d) {
		return d != 0 ? Optional.of(1d / d) : Optional.empty();
	}

}
