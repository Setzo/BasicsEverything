package main;

public class Impl implements Check<Integer>{

	@Override
	public Boolean eqls(Integer e, Integer ex) {
		
		return e.compareTo(ex) == 0 ? true : false;
	}

}
