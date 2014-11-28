
public class ByteObservation {
	public static void main(String[] args) {

		byte byteVal;

		//byteVal = (byte) 128;
		//System.out.println(byteVal);

		//byteVal = (byte) 400;
		//System.out.println(byteVal);
		
		for(int i=0;i<=400;i++) {
			
			byteVal=(byte)i;
			System.out.printf("%3d %4d | ",i,byteVal);
			if(i%50==0) {
				System.out.printf("\n\n");
			}
		}
		
	}
}
