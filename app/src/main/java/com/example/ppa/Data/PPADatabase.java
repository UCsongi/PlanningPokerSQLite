package com.example.ppa.Data;

import com.example.ppa.Data.DAOs.RatingDAO;
import com.example.ppa.Data.DAOs.UserDAO;
import com.example.ppa.Data.DAOs.TaskDAO;
import com.example.ppa.Data.Entities.Rating;
import com.example.ppa.Data.Entities.Task;
import com.example.ppa.Data.Entities.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Task.class, Rating.class}, version = 1)
public abstract class PPADatabase extends RoomDatabase {
    public abstract UserDAO userDao();
    public abstract TaskDAO taskDAO();
    public abstract RatingDAO ratingDAO();
}

