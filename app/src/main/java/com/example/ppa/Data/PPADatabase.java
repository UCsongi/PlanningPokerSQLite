package com.example.ppa.Data;

import android.content.Context;

import com.example.ppa.Data.DAOs.RatingDAO;
import com.example.ppa.Data.DAOs.UserDAO;
import com.example.ppa.Data.DAOs.TaskDAO;
import com.example.ppa.Data.Entities.Rating;
import com.example.ppa.Data.Entities.Task;
import com.example.ppa.Data.Entities.User;

import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Task.class, Rating.class}, version = 1)
public abstract class PPADatabase extends RoomDatabase {

    private static PPADatabase INSTANCE;

    public synchronized static PPADatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PPADatabase.class, "PlanningPokerDB")
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);

                            Executors.newSingleThreadScheduledExecutor().execute(() -> getInstance(context).taskDAO().insertAll(new Task[]{
                                    new Task("Title1", "Description1"),
                                    new Task("Title2", "Description2"),
                                    new Task("Title3", "Description3"),
                                    new Task("Title4", "Description4"),
                                    new Task("Title5", "Description5"),
                                    new Task("Title6", "Description6"),
                                    new Task("Title7", "Description7"),
                                    new Task("Title8", "Description8"),
                                    new Task("Title9", "Description9"),
                                    new Task("Title10", "Description10")
                            }));
                        }
                    })
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract UserDAO userDao();
    public abstract TaskDAO taskDAO();
    public abstract RatingDAO ratingDAO();
}

