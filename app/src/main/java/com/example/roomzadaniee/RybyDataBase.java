package com.example.roomzadaniee;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Ryby.class}, version = 2)
public abstract class RybyDataBase extends RoomDatabase {
    public abstract RybyDAO zwrocGryDao();
}