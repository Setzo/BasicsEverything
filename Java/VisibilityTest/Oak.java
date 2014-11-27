package pack1;
 
public class Oak extends Plant {
     
    public Oak() {
        
        //Działa - size jest protected  i jest w tym samym pakiecie	
        this.size = "large";
         
        //height nie ma specyfikacji co do widoczności, więc widać go w całym pakiecie
        this.height = 10;
    }
 
}
