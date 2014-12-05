
public class CountFactorial {
	
	public long countF(int a) {

		if (a<0) {
			return -1;
		}
		if (a == 0) {
			return 0;
		}
		if (a == 1) {
			System.out.println(a);
			return a;
		}
		System.out.println(a);
		return countF(a-1) * a;
	}
}
