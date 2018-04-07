package ca.six.archdemo.intro.room.basic;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DbConverters {
    // 存User.Date到数据库中
    @TypeConverter
    public static Long dateToTimeStamp(Date date){
        return date == null ? null : date.getTime();
    }

    // 从数据库中还原出User.Date
    @TypeConverter
    public static Date timeStampToDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }
}
