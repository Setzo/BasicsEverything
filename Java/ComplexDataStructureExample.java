import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


public class ComplexDataStructure {

    public static String[] vehicles = {
            "ambulance", "helicopter", "boat"
    };

    public static String[][] drivers = {
            {"Fred", "Sue", "Pete"},
            {"Sue", "Richard", "Bob", "Fred"},
            {"Pete", "Mary", "Bob"}
    };

    public static void main(String[] args) {
        Map<String, Set<String>> personel = new HashMap<String, Set<String>>();

        for (int i = 0; i < vehicles.length; i++) {

            String veh = vehicles[i];
            String[] driversList = drivers[i];
            Set<String> driverSet = new LinkedHashSet<String>();
            System.out.println(veh);

            for (String driver : driversList) {
                driverSet.add(driver);
                System.out.println(driver);
            }
            System.out.println();
            personel.put(veh, driverSet);
        }
        System.out.println();
        {
            Set<String> driversList = personel.get("boat");
            for (String driver : driversList) {
                System.out.println(driver);
            }
        }
        System.out.println();
        System.out.println();

        for (String veh : personel.keySet()) {
            System.out.println(veh);
            System.out.println(personel.get(veh).toString());
        }
    }
}
