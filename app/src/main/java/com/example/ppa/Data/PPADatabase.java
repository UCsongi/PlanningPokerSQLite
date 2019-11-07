package com.example.ppa.Data;

import android.content.Context;

import com.example.ppa.Data.DAOs.RatingDAO;
import com.example.ppa.Data.DAOs.UserDAO;
import com.example.ppa.Data.DAOs.TaskDAO;
import com.example.ppa.Data.Entities.Rating;
import com.example.ppa.Data.Entities.Task;
import com.example.ppa.Data.Entities.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Task.class, Rating.class}, version = 1)
public abstract class PPADatabase extends RoomDatabase {

    private static PPADatabase INSTANCE;

    public static PPADatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PPADatabase.class, "PPADB").build();
        }
        return INSTANCE;
    }

    public abstract UserDAO userDao();
    public abstract TaskDAO taskDAO();
    public abstract RatingDAO ratingDAO();
}

