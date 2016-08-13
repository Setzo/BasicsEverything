package euler9;

public class Euler9 {
	public static void main(String[] args) {
		
		outerloop:
		for (int i=1; i<=1000; i++) {
			for (int j=1; j<=1000; j++) {
				for (int x=1; x<=1000; x++) {
					if(Math.pow(i, 2)+Math.pow(j, 2)==Math.pow(x, 2)) {
						if(i+j+x==1000) {
							System.out.println(i*j*x);
							break outerloop;
						}
					}
				}
			}
		}
	}
}
