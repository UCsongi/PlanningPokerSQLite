package com.example.ppa.Data.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(primaryKeys = {"user_id", "task_id"},
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = {"id"},
                        childColumns = {"user_id"},
                        onUpdate = CASCADE,
                        onDelete = CASCADE),
                @ForeignKey(
                        entity = Task.class,
                        parentColumns = {"id"},
                        childColumns = {"task_id"},
                        onUpdate = CASCADE,
                        onDelete = CASCADE)})
public class Rating {
    @ColumnInfo(name = "user_id")
    public int user_id;

    @ColumnInfo(name = "task_id")
    public int task_id;

    @ColumnInfo(name = "rating")
    public int rating;

    public Rating(int user_id, int task_id, int rating){
        this.user_id = user_id;
        this.task_id = task_id;
        this.rating = rating;
    }
}