public class Machine implements IInfo {

    private int id = 7;

    public void start() {
        System.out.println("Machine started.");
    }

    public void showInfo() {
        System.out.println("Machine ID is: " + id);
    }
}
