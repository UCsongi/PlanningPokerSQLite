package com.example.ppa.Data.DAOs;

import com.example.ppa.Data.Entities.Rating;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RatingDAO {
    @Query("SELECT * FROM rating")
    List<Rating> getAll();

    @Query("SELECT * FROM rating INNER JOIN task ON rating.task_id = task.id WHERE task.id = :taskId")
    List<Rating> getAllByTaskId(int taskId);

    @Insert
    void insertAll(Rating... ratings);

    @Delete
    void delete(Rating rating);
}