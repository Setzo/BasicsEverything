import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scrabble {
	
										//a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
	private static final int[] scores = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };

	public static void main(String[] args) throws IOException {

		final Set<String> shakespeareWords = Scrabble.getSet("src/shake.txt");
		final Set<String> scrabbleWords = Scrabble.getSet("src/ospd.txt");
		
		System.out.println(shakespeareWords.size());
		System.out.println(scrabbleWords.size());
		
		final Function<String, Integer> getWordScore =
				word -> word.chars().map(letter -> Scrabble.scores[letter - 'a']).sum();
				
		final ToIntFunction<String> getWordScore2 = 
				word -> word.chars().map(letter -> Scrabble.scores[letter - 'a']).sum();
		
		System.out.println("Score of 'hello': " + getWordScore.apply("hello"));
		
		final String bestWord = shakespeareWords.stream()
				.filter(scrabbleWords::contains)
				.max(Comparator.comparing(getWordScore)).get();
		
		System.out.println(bestWord);
		
		IntSummaryStatistics stats = shakespeareWords.stream()
				.parallel()
				.filter(scrabbleWords::contains)
				.mapToInt(getWordScore2)
				.summaryStatistics();
		
		System.out.printf("\n%s%d\n%s%d\n%s%f\n%s%d\n%s%d\n",
				"Max Score: ", stats.getMax(),
				"Min Score: ", stats.getMin(),
				"Avg Score: ", stats.getAverage(),
				"Sum of Scores: ", stats.getSum(),
				"Score Count: ", stats.getCount());
	}
	
	public static Set<String> getSet(String path) throws IOException {
		
		return Scrabble.getStream(path).collect(Collectors.toSet());
	}
	
	public static Stream<String> getStream(String path) throws IOException {
		
		return Files.lines(Paths.get(path))
				.map(word -> word.toLowerCase());
	}
}
