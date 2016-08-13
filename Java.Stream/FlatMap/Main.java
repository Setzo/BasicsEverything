package test.stream;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws URISyntaxException, IOException {

		Path path1 = Paths.get(Main.class.getResource("plik.txt").toURI());
		Path path2 = Paths.get(Main.class.getResource("plik2.txt").toURI());
		
		Stream<String> stream1 = Files.lines(path1);
		Stream<String> stream2 = Files.lines(path2);
		
		Function<String, Stream<String>> func = line -> Pattern.compile(" ").splitAsStream(line);
		
		Set<String> set = 
				Stream.of(stream1, stream2)
					.flatMap(Function.identity())
					.flatMap(func)
					.collect(Collectors.toSet());
		
		stream1 = Files.lines(path1);
		stream2 = Files.lines(path2);
		
		List<String> list = 
				Stream.of(stream1, stream2)
					.flatMap(Function.identity())
					.flatMap(func)
					.distinct()
					.sorted()
					.collect(Collectors.toList());
		
		list.forEach(System.out::println);
		
		System.out.println(set.size());
		System.out.println(list.size());
		
	}

}
