package ca.six.archdemo.intro.room.basic;

import android.arch.persistence.room.ColumnInfo;
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

     public User() {
     }

     public User(int uid, String firstName, String lastName) {
         this.uid = uid;
         this.firstName = firstName;
         this.lastName = lastName;
     }
 }
