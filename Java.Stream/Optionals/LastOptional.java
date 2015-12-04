package lopt;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LastOptional {

	public static void main(String[] args) {

		List<Double> result = new CopyOnWriteArrayList<Double>();
		
		Function<Double, Stream<Double>> flatMapper =
				d -> LastOptional.inverse(d)
						.flatMap(LastOptional::sqrt)
						.map(Stream::of)
						.orElseGet(Stream::empty);
				
		result = ThreadLocalRandom.current()
					.doubles(1000/*, -1000.0d, 1000.0d*/)
					.parallel()
					.boxed()
					.flatMap(flatMapper)
					.collect(Collectors.toList());
				
		
		System.out.println(result.size());
			
	}

	public static Optional<Double> sqrt(Double d) {
		return d > 0d ? Optional.of(Math.sqrt(d)) : Optional.empty();
	}

	public static Optional<Double> inverse(Double d) {
		return d != 0 ? Optional.of(1d / d) : Optional.empty();
	}

}
