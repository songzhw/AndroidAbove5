package ca.six.archdemo.intro.room.basic;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

 /* @Entity(primaryKeys = {"firstName","lastName"}, tableName = "users") */
 @Entity
 public class User {
     @PrimaryKey
     public int uid;

     @ColumnInfo(name = "first_name")
     public String firstName;

     @ColumnInfo(name = "last_name")
     public String lastName;

     @Embedded(prefix = "adds_")
     public Address address;

     public User() {
     }

     public User(int uid, String firstName, String lastName, Address address) {
         this.uid = uid;
         this.firstName = firstName;
         this.lastName = lastName;
         this.address = address;
     }

     @Override
     public String toString() {
         return "User{" +
                 "uid=" + uid +
                 ", firstName='" + firstName + '\'' +
                 ", lastName='" + lastName + '\'' +
                 ", address=" + address +
                 '}';
     }
 }
