
package euler10;

public class Euler10 {
	public static void main(String[] args) {
		
		long sum=2;
		int n=2000000;
		for(int i=3; i<n; i+=2) {
			loop:
			{
				for(int j=3; j<=Math.sqrt(i); j+=2) {
					if(i%j==0) {
						break loop;
					}
			}
			sum += i;
			}
		}
		System.out.println(sum);
	}
}
