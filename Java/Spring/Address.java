package tst;

public class Address {

    private String address;
    private String something;

    public Address(String address, String something) {

        this.address = address;
        this.something = something;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSomething() {
        return something;
    }

    public void setSomething(String something) {
        this.something = something;
    }

    public String toString() {
        return "Address [address=" + address + ", something=" + something + "]";
    }

    public void init() {

        System.out.println("init");
    }

    public void destroy() {

        System.out.println("destroy");
    }
}
