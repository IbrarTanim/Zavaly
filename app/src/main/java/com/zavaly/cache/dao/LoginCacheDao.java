package com.zavaly.cache.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.zavaly.cache.entities.LoginCache;

import java.util.List;

@Dao
public interface LoginCacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LoginCache loginCache);

    @Query("SELECT * FROM login_cache")
    List<LoginCache> getLoggedInfo();

    @Query("DELETE FROM login_cache")
    void deleteLoggedInfo();
}
