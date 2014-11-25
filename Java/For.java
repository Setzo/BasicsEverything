public class For {
	public static void main(String[] args){;
	
		final int di,n;
		di= 1; 										//final = const, po przypisaniu nie da się zmienić. 
		n = 10;										//n - limit funkcji for
		
		for(int i=1; i<=n; i=i+di){					//zmieniając di zmieniam przyrost i (di)         i+=n  ------>  i=i+n
			System.out.println("i = " + i);
		}
	}
}
