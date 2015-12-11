package euler59;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Euler59 {

	private static final String CIPHER_URL = "https://projecteuler.net/project/resources/p059_cipher.txt";

	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	private static final int FROM = 0;

	private static final int TO = 3;

	private static int cnt = 0;

	private static List<Byte> numbers;

	static {

		Scanner sc;
		
		try {
			sc = new Scanner(new URL(Euler59.CIPHER_URL).openStream());

			StringBuilder sb = new StringBuilder();

			while (sc.hasNextLine()) {

				sb.append(sc.nextLine()).append("\n");
			}

			sc.close();

			Euler59.numbers = Stream.of(sb.toString().split(","))
					.map(String::trim)
					.map(Byte::parseByte)
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static void main(String[] args) {

		final List<Byte> keys = Euler59.ALPHABET.chars()
				.mapToObj(n -> (byte) n)
				.collect(Collectors.toList());

		Euler59.Looper.loopThrough(keys, Euler59.FROM, Euler59::valSigma);

		System.out.printf("%d%s%s\n%s\n", Euler59.Looper.max, " : ",
				Euler59.Looper.maxKey.stream()
						.map(x -> (new Character((char) x.byteValue()).toString()))
						.collect(Collectors.joining("")),
				Euler59.xor(Euler59.Looper.maxKey).stream()
						.map(x -> (new Character((char) x.byteValue()).toString()))
						.collect(Collectors.joining("")));

	}

	private static int valSigma(List<Byte> key) {

		return Euler59.sigma(Euler59.xor(key));
	}

	private static List<Byte> xor(List<Byte> key) {

		List<Byte> res = new ArrayList<Byte>(Euler59.numbers.size());

		Euler59.numbers.forEach(number -> {
			res.add((byte) (number ^ key.get(Euler59.cnt++ % key.size())));
		});

		Euler59.cnt = 0;

		return res;
	}

	private static int sigma(List<Byte> xored) {

		Euler59.cnt += xored.stream()
			.filter(item -> Character.isLetter((char) ((byte) item)))
			.collect(Collectors.counting())
			.intValue();
		
		int cnt = Euler59.cnt;
		Euler59.cnt = 0;

		return cnt;
	}

	private static class Looper {

		private static List<Byte> maxKey;

		private static int max;

		private static List<Byte> key;

		static {
			Euler59.Looper.maxKey = new ArrayList<Byte>(Euler59.TO);
			Euler59.Looper.key = new ArrayList<Byte>(Euler59.TO);
			Euler59.Looper.max = 0;
			
			IntStream.range(Euler59.FROM, Euler59.TO).forEach(x -> {
				Euler59.Looper.key.add((byte) 'a');
				Euler59.Looper.maxKey.add((byte) 'a');
			});
		}

		private static final void loopThrough(final List<Byte> keys, final int recursionCounter,
				Function<List<Byte>, Integer> function) {

			if (recursionCounter + 1 == Euler59.TO) {
				
				keys.forEach(key0 -> {

					Euler59.Looper.key.set(recursionCounter, key0);
					int tmp = function.apply(Euler59.Looper.key);

					if (tmp > Euler59.Looper.max) {
						
						Euler59.Looper.max = tmp;
						Collections.copy(Euler59.Looper.maxKey, Euler59.Looper.key);
					}
				});

			} else {
				keys.forEach(key0 -> {
					Euler59.Looper.key.set(recursionCounter, key0);
					Euler59.Looper.loopThrough(keys, recursionCounter + 1, function);
				});
			}
		}

	}

}
