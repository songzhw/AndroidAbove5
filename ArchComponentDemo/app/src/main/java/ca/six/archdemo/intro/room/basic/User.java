package ca.six.archdemo.intro.room.basic;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class User {
    @PrimaryKey
    public int uid;
    @ColumnInfo(name = "uname")
    public String name;
    @Embedded(prefix = "adds_")
    public Address address;
    public Date birthday;

    public User() {
    }

    public User(int uid, String name, Address address, Date birthday) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", brithday=" + birthday +
                '}';
    }
}
