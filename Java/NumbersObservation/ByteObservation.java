
public class ByteObservation {
	public static void main(String[] args) {

		byte byteVal;

		//byteVal = (byte) 128;
		//System.out.println(byteVal);

		//byteVal = (byte) 400;
		//System.out.println(byteVal);
		
		for(int i=0;i<=999;i++) {
			if((i%50==0)&&(i!=0)) {
				System.out.printf("\n");
				for(int j=0;j<=48;j++) {
					if(j==0) {
						System.out.printf("%10s|", " ");
					}
					System.out.printf("%11s|", " ");
				}
				System.out.printf("\n");
			}
			byteVal=(byte)i;
			System.out.printf("%4d %4d | ",i,byteVal);
			
		}	
	}
}
