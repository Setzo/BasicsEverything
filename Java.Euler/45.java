
public class Euler45 {
	public static void main(String[] args) {
		
		int t = 285;
		int p = 165;
		int h = 144;
		
		while (true) {

			long tri = (long)t * (t + 1) / 2;
			long pen = (long)p * (p * 3 - 1) / 2;
			long hex = (long)h * (h * 2 - 1);
			
			long min = Math.min(Math.min(tri, pen), hex);

			if(tri == pen && tri == hex) {
				System.out.print(tri);
				break;
			}
			
			if(min == tri) {
				t++;
			}
			if(min == pen) {
				p++;
			}
			if(min == hex) {
				h++;
			}
		}
	}
}
