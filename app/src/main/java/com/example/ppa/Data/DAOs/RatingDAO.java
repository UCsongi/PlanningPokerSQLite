package com.example.ppa.Data.DAOs;

import com.example.ppa.Data.Entities.Rating;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface RatingDAO {
    @Query("SELECT * FROM rating")
    List<Rating> getAll();

    @Query("SELECT * FROM rating INNER JOIN task ON rating.task_id = task.id WHERE task.id = :taskId")
    List<Rating> getAllByTaskId(int taskId);

    @Query("SELECT * FROM rating INNER JOIN user ON rating.user_id = user.id WHERE user.id = :userId")
    List<Rating> getAllByUserId(int userId);

    @Query("SELECT * FROM rating INNER JOIN user ON rating.user_id = user.id INNER JOIN task ON rating.task_id = task.id WHERE user.id = :userId AND task.id = :taskId")
    Rating getByUserIdAndTaskId(int userId, int taskId);

    @Insert
    void insertAll(Rating... ratings);

    @Update
    void updateAll(Rating... ratings);

    @Delete
    void delete(Rating rating);
}