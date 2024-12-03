package com.example.seminar04;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Disc.class},version = 1)
public abstract class BazaDeDateDiscuri extends RoomDatabase {
    public abstract InterfataDao interfataDao();
}
