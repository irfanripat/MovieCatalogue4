package com.example.submission_3.room;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.submission_3.dao.MovieDAO;
import com.example.submission_3.dao.TvDAO;
import com.example.submission_3.model.Movie;
import com.example.submission_3.model.TVShow;

@Database(entities = {Movie.class, TVShow.class}, version = 100, exportSchema = false)
public abstract class DBRoom extends RoomDatabase {
    private static DBRoom dbRoom;

    public static DBRoom getInstance(Context context) {
        if (dbRoom == null) {
            dbRoom = Room.databaseBuilder(context, DBRoom.class, "db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbRoom;
    }

    public abstract MovieDAO movieDao();

    public abstract TvDAO tvDao();

}
