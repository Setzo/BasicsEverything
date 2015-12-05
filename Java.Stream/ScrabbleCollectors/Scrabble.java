import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scrabble {
	
										//a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q,  r, s, t, u, v, w, x, y, z
	private static final int[] scores = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };
												   //a, b, c, d,  e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
	private static final int[] letterDistribution = {9, 2, 2, 1, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};

	public static void main(String[] args) throws IOException {

		Set<String> shakespeareWords = Scrabble.getSet("src/shake.txt");
		Set<String> scrabbleWords = Scrabble.getSet("src/ospd.txt");
				
		Function<String, Map<Integer, Long>> histogram = 
			word -> word.chars().boxed()
				.collect(
					Collectors.groupingBy(
						letter -> letter,
						Collectors.counting()
					)
				);	
		
		Function<String, Long> blankCounter = 
			word -> histogram.apply(word)
				.entrySet()
				.stream()
				.mapToLong(
					entry -> 
						Long.max(
							entry.getValue() -
								Scrabble.letterDistribution[entry.getKey() - 'a'],
							0L
						)
				)
				.sum();
			
		Function<String, Integer> score = 
			word -> histogram.apply(word)
				.entrySet()
				.stream()
				.mapToInt(
					entry -> 
						Scrabble.scores[entry.getKey() - 'a'] *
						Integer.min(
							entry.getValue().intValue(),
							Scrabble.letterDistribution[entry.getKey() - 'a']
						)
				)
				.sum();
		
		shakespeareWords.stream()
			.filter(scrabbleWords::contains)
			.filter(word -> blankCounter.apply(word) <= 2)
			.sorted()
			.collect(
				Collectors.groupingBy(score)
			)
			.entrySet().stream()
			.sorted(
					Comparator.comparing(entry -> -entry.getKey())
			)
			.limit(3)
			.forEach(System.out::println);
	}
	
	public static Set<String> getSet(String path) throws IOException {
		return Scrabble.getStream(path).collect(Collectors.toSet());
	}
	
	public static Stream<String> getStream(String path) throws IOException {
		
		return Files.lines(Paths.get(path))
				.map(word -> word.toLowerCase());
	}
}
