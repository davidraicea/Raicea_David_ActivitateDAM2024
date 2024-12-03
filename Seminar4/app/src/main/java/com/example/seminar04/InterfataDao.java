package com.example.seminar04;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface InterfataDao {
    @Insert
    void insert(Disc disc);

    @Query("SELECT * FROM Discuri")
    List<Disc> getDiscuri();

    @Delete
    void delete(Disc disc);
}
