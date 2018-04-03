package ca.six.archdemo.intro.room.basic;

public class Address {
    public String city;
    public int postCode;

    public Address(String city, int postCode) {
        this.city = city;
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", postCode=" + postCode +
                '}';
    }
}
