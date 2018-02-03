
package ca.six.archdemo.intro.transfer.auto;

public class User {
    public long id;
    public String name;

    public User(String name) {
        this.id = System.currentTimeMillis();
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
