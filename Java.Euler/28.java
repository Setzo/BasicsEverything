package euler28;

public class Euler28 {
	public static void main(String[] args) {
		long sum=1, lastNum=1;
		for(int i=3; i<=1001; i+=2) {
			sum = sum +(lastNum*4)+(10*(i-1));
			lastNum = lastNum + 4*(i-1);
		}
		System.out.println(sum);
	}
}


/*

i+=2

i	act			261 = 25 +6 +25 +6+6 +25 +6+6+6 +25 +6+6+6+6 +101
				x9 = 537 = 4*49 + 10*8 + 261
				LastNumber7 = 25+ 4*6 = 25 + 24 = 49
1	1			LastNumber5 = LastNumber(N-1) + 4*(i-1)
3	25			xN =4*LastNumber + 10*(N-1) + LastBase
5	101			x5 = 101 = 4*9 + 10*4 + 25
7	261			LastBase = -4*LastNumber -10*(N-1) +xN



43 44 45 46 47 48 49
42 21 22 23 24 25 26
41 20  7  8  9 10 27
40 19  6  1  2 11 28
39 18  5  4  3 12 29
38 17 16 15 14 13 30
37 36 35 34 33 32 31

*/
