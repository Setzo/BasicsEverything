
public class Caster {
	public static void main(String[] args) {
		
		byte 	byteVal    =	127;
		short	shortVal   =	451;
		int 	intVal     =	678;
		long	longVal    =	20394;
		
		float 	floatVal   =	543.75f;	//(float)543.75
		double 	doubleVal  =	890.45;
		
		intVal = (int)longVal;				//działa tylko, gdy longVal należy do zbioru zakresów wartości int'a
		doubleVal = intVal;					//nie trzeba castować, bo double i tak jest większy od inta
		
		intVal = (int)floatVal;				//int odetnie część ułamkową float'a, bez zaokrąglenia
		
		System.out.printf("\n%16s  Integer values : \n\n", " ");
		
		System.out.printf("%22d  Byte    %-22d\n", Byte.MIN_VALUE, Byte.MAX_VALUE);
		System.out.printf("%22d  Short   %-22d\n", Short.MIN_VALUE, Short.MAX_VALUE);
		System.out.printf("%22d  Integer %-22d\n", Integer.MIN_VALUE, Integer.MAX_VALUE);
		System.out.printf("%22d  Long    %-22d\n", Long.MIN_VALUE, Long.MAX_VALUE);
		
		System.out.printf("\n%13s  Floating point values : \n\n", " ");
		
		System.out.printf("%15s"," ");	
		System.out.println(Float.MIN_VALUE + "  Float   " + Float.MAX_VALUE);
		
		System.out.printf("%14s"," ");	
		System.out.println(Double.MIN_VALUE + "  Double  " + Double.MAX_VALUE);
		
	}
}
