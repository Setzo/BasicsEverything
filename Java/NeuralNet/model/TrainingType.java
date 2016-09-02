package model;

public class TrainingType {

    private static int cnt = 0;

    private int id;
    private String name;

    public TrainingType(String name) {

        this.name = name;
        this.id = cnt;
        cnt++;
    }

    public String toString() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}
