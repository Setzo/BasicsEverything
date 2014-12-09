
package euler10;

public class Euler10 {
	public static void main(String[] args) {
		
		long sum=2;
		for(int i=2; i<2000000; i++) {
			for(int j=2; j<i; j++) {
				if(i%j==0) {
					break;
				}
				if(j==i-1) {
					sum=sum+i;
				}
			}
		}
		System.out.println(sum);
	}
}
