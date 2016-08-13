package euler14;

public class Euler14 {
	public static void main(String[] args) {

		long max = 0, maxNum = 0;
		
		for (int i = 1	; i <= 1000000; i ++) {
			
			long num = i;
			long act = 0;
			
			while (num != 1) {
				act++;
				num = collatz(num);
			}
			
			if(act > max) {
				max = act;
				maxNum = i;
			}
		}
		System.out.print(maxNum);
	}
	
	public static long collatz(long n) {
		
		return n % 2 == 0 ? n / 2 : 3 * n + 1;
	}
}
