package com.edgar.lilyhouse.Data.MainSceneData;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MangaItemDao {

    @Query("SELECT * FROM manga_items ORDER BY last_updatetime DESC")
    LiveData<List<MangaItem>> getLatestUpdateItems();

    @Query("SELECT * FROM manga_items ORDER BY CAST(num AS INTEGER) DESC")
    LiveData<List<MangaItem>> getMostViewedItems();

    @Query("SELECT * FROM manga_items ORDER BY last_updatetime DESC")
    List<MangaItem> getItemsByTime();

    @Query("SELECT * FROM manga_items ORDER BY CAST(num AS INTEGER) DESC")
    List<MangaItem> getItemsByViewers();

    @Query("SELECT * FROM manga_items WHERE id = :id LIMIT 1")
    LiveData<MangaItem> getItemById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItems(MangaItem... mangaItems);

    @Delete
    void deleteItem(MangaItem mangaItem);

    @Query("DELETE FROM manga_items")
    void deleteAllItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItemsInList(List<MangaItem> items);

}
