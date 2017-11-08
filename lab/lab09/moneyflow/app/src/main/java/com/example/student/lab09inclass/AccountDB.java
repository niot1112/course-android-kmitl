package com.example.student.lab09inclass;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {AccountInfo.class}, version = 1)
public abstract class AccountDB extends RoomDatabase {
    public abstract AccountDAO getAccountDAO();

}
