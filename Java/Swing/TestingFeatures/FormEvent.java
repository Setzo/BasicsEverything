package nameOccupy;

import java.util.EventObject;

public class FormEvent extends EventObject {

    private static final long serialVersionUID = 6409245800344985837L;

    private String name;
    private String occupation;
    private int age;
    private String employment;
    private boolean plCitizen;
    private String nrDow;

    public FormEvent(Object source) {

        super(source);
    }

    public FormEvent(Object source, String name, String occupation, int age,
                     String employment, boolean plCitizen, String nrDow) {

        super(source);

        this.name = name;
        this.occupation = occupation;
        this.age = age;
        this.employment = employment;
        this.plCitizen = plCitizen;
        this.nrDow = nrDow;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getEmployment() {
        return employment;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setAge(int id) {
        this.age = id;
    }

    public void setAge(String employment) {
        this.employment = employment;
    }

    public boolean isPlCitizen() {
        return plCitizen;
    }

    public String getNrDow() {
        return nrDow;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public void setPlCitizen(boolean plCitizen) {
        this.plCitizen = plCitizen;
    }

    public void setNrDow(String nrDow) {
        this.nrDow = nrDow;
    }
}
