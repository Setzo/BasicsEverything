package euler28;

public class Euler28 {
	public static void main(String[] args) {
		long sum=1, lastNum=1;
		for(int i=3; i<=1001; i+=2) {
			sum += (lastNum*4)+(10*(i-1));
			lastNum = lastNum + 4*(i-1);
		}
		System.out.println(sum);
	}
}
