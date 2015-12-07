package adv;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class MovieSpliterator implements Spliterator<Movie> {

	private Spliterator<String> lineSpliterator;

	private String line;
	
	public MovieSpliterator(Spliterator<String> lineSpliterator) {
		
		this.lineSpliterator = lineSpliterator;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Movie> action) {
		
		if(this.lineSpliterator.tryAdvance(line -> this.line = line)) {
			
			String[] elements = line.split("/");
			
			String title = elements[0].substring(0, elements[0].lastIndexOf("(")).trim();
			
			String releaseYear = elements[0].substring(elements[0].lastIndexOf("(") + 1,
					elements[0].lastIndexOf(")"));
			
			if(releaseYear.contains(",")) {
				releaseYear = releaseYear.substring(0, releaseYear.lastIndexOf(","));
			}
			
			Movie movie = new Movie(title, Integer.valueOf(releaseYear));
			
			IntStream.range(0, elements.length).forEach(i -> {
				String[] name = elements[i].split(",");
				String lastname = name[0].trim();
				String firstname = "";
				
				if(name.length > 1) {
					firstname = name[1].trim();
				}
				
				Actor actor = new Actor(lastname, firstname);
				movie.addActor(actor);
			});
			
			action.accept(movie);
			return true;
		}
		return false;
	}

	@Override
	public Spliterator<Movie> trySplit() {
		return null;
	}

	@Override
	public long estimateSize() {
		return this.lineSpliterator.estimateSize();
	}

	@Override
	public int characteristics() {
		return this.lineSpliterator.characteristics();
	}

}
