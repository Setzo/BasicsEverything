
public class FormatStr {
	public static void main(String[] args) {
		
		String a="Hello";
		double b=132.7347801;
		
		for (int i=0;i<20;i++) {								//%d - szukaj liczby i ją zamień %d - tylko do intów
			System.out.printf("i is equal to : %10d\n", i);		//%nd (np. %5d) Zapisz liczbę w 5 znakach (15 = xxx15) x=space 
			System.out.printf("i is equal to : %-10d\n", i);	//%-nd = pisz od tyłu 				%-5d= (15 = 15xxx) x=space
		}														
		
		System.out.printf("Here is the string : %s\n", a);		//%s - zamień ze Strigiem
		System.out.printf("Fraction : %f\n", b);				//%f - zamień z floatem/doublem  ||  %5.3f - xxxxx z 3 msc po przecinku
		System.out.printf("Fraction : %.2f\n", b);				//%.nf - skróć do n miejsc po przecinku i zaokrąglij ost. cyfre
	}
}
