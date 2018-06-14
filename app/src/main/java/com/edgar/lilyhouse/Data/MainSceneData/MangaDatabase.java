package com.edgar.lilyhouse.Data.MainSceneData;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {MangaItem.class}, version = 1, exportSchema = false)
public abstract class MangaDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "MainListItems";

    private static MangaDatabase instance = null;
    private static final Object LOCK = new Object();

    public static MangaDatabase getInstance(Context context) {

        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), MangaDatabase.class,
                            DATABASE_NAME)
//                            .addCallback(callback)
                            .build();
                }
            }
        }

        return instance;
    }

    public abstract MangaItemDao mainListItemDao();

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            //clear the database and insert something. comment this line if not needed
            new PopulateDbAsync(instance).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private MangaItemDao itemDao;

        PopulateDbAsync(MangaDatabase database) {
            this.itemDao = database.mainListItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            itemDao.deleteAllItems();

            return null;
        }
    }


}
