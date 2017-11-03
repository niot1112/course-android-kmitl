package com.example.student.lab09inclass;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MessageDAO {
    @Insert
    void insert(MessageInfo message);

    @Query("SELECT * FROM MESSAGE_INFO")
    List<MessageInfo> findAll();
}
