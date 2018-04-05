package ca.six.archdemo.intro.room.basic;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


@Entity(foreignKeys =  @ForeignKey( onDelete = ForeignKey.CASCADE,
        entity = User.class, parentColumns = "uid", childColumns = "userId"))
public class Book {
    @PrimaryKey public int bookId;
    public String title;
    public int userId;
}
