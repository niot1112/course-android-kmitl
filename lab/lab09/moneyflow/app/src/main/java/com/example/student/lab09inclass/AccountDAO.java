package com.example.student.lab09inclass;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AccountDAO {
    @Insert
    void insert(AccountInfo message);

    @Query("SELECT * FROM ACCOUNT_INFO")
    List<AccountInfo> findAll();

    @Query("SELECT SUM(MONEY) FROM ACCOUNT_INFO WHERE TYPE='+'")
    String findIncome();

    @Query("SELECT SUM(MONEY) FROM ACCOUNT_INFO WHERE TYPE='-'")
    String findPayment();

    @Query("UPDATE ACCOUNT_INFO SET TYPE = :type,TEXT = :text ,MONEY = :money WHERE id =:id")
    int Update(String type, String text, int money, int id);

    @Query("DELETE FROM ACCOUNT_INFO WHERE id like :id")
    int Delete(int id);

}
