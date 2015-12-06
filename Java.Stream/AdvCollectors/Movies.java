package adv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Movies {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		Set<Movie> movies = new HashSet<Movie>();
		
		Stream<String> lines = Files.lines(Paths.get("src/adv/movies.txt"));
		
		lines.forEach(line -> {
			
			String[] elements = line.split("/");
			
			String title = elements[0].substring(0, elements[0].lastIndexOf("(")).trim();
			
			String releaseYear = elements[0].substring(elements[0].lastIndexOf("(") + 1,
					elements[0].lastIndexOf(")"));
			
			if(releaseYear.contains(",")) {
				return;
			}
			
			Movie movie = new Movie(title, Integer.valueOf(releaseYear));
			
			for(int i = 0; i < elements.length; i++) {
				
				String[] name = elements[i].split(",");
				String lastname = name[0].trim();
				String firstname = "";
				
				if(name.length > 1) {
					firstname = name[1].trim();
				}
				
				Actor actor = new Actor(lastname, firstname);
				movie.addActor(actor);
			}
			
			movies.add(movie);
		});
		
		lines.close();
		
		System.out.println(movies.size());
	}

}
