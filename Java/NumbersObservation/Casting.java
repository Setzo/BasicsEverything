class Type1 {
	
    public void start() {
        System.out.println("Type1 start");
    }
}
 
class Type2 extends Type1 {
	
    public void start() {
        System.out.println("Type2 start");
    }
     
    public void snap() {
        System.out.println("Type2 snap");
    }
}
 
public class App {
    public static void main(String[] args) {
 
        Type1 type1val0 = new Type1();
        Type2 type2val0 = new Type2();
         
        type1val0.start();
        type2val0.start();
        type2val0.snap();
         
        // Upcasting 
        Type1 type1val1 = type2val0;
        type1val1.start();

        // Downcasting
        Type1 type1val2 = new Type2();
        Type2 type2val1 = (Type2)type1val2;
        type2val1.start();
        type2val1.snap();
        
    }
}
