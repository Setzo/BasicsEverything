package iterab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

public class UrlLib implements Iterable<String> {
	private LinkedList<String> urls = new LinkedList<String>();
	
	private class UrlIterator implements Iterator<String> {

		private int index = 0;
		@Override
		public boolean hasNext() {
			return (index < urls.size());
		}

		@Override
		public String next() {
			StringBuilder sb = new StringBuilder();
			try {
				URL url = new URL(urls.get(index));
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
				String line;
				while ((line = br.readLine())!= null) {
					sb.append(line)
					.append("\n");
				}
				br.close();
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			index++;
			return sb.toString();
		}
		
		public void remove() {
			urls.remove(index);
		}

	}
	
	public UrlLib() {
		urls.add("http://stackoverflow.com/");
		urls.add("http://csgolounge.com/");
		urls.add("https://projecteuler.net/");
	}

	@Override
	public Iterator<String> iterator() {
		return new UrlIterator();
	}
	
	/*
	@Override
	public Iterator<String> iterator() {
		return urls.iterator();
	}
	*/
}
