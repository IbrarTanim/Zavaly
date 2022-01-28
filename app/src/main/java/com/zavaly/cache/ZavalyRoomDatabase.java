package com.zavaly.cache;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.zavaly.cache.dao.LoginCacheDao;
import com.zavaly.cache.entities.LoginCache;

@Database(entities = {LoginCache.class}, version = 2, exportSchema = false)

public abstract class ZavalyRoomDatabase extends RoomDatabase {

    private static volatile ZavalyRoomDatabase INSTANCE;

    public static ZavalyRoomDatabase getINSTANCE(Context context) {

        if (INSTANCE == null) {
            synchronized ((ZavalyRoomDatabase.class)) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ZavalyRoomDatabase.class, "zavaly_room_database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract LoginCacheDao loginCacheDao();
}
