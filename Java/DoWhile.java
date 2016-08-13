public class DoWhile {
	public static void main(String[] args){
		int a,b;
		boolean c;
		a=1;
		b=10;
		do{
			a+=1;
			c=a>=b;
			System.out.println("a = " + a + " b = " + b + " c = " + c);
			if(a==7){
				break;
			}
		}while (!c);
	}
}
