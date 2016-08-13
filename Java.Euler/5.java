
public class Euler5 {
	public static void main(String[] args) {
		
		boolean x=true;	
		
		for(int j = 2520; j <= 1000000000; j++) {
			for(int i = 1; i <= 20; i++) {
				
				
				if(j%i==0) {
					x=true;
				}
				else if(j%i!=0) {
					x=false;
				}
				
				if(i==20 && j%i==0) {
					System.out.println(j);
				}
				
				if(!x) {
					break;
				}
			}
		}
	}
}
