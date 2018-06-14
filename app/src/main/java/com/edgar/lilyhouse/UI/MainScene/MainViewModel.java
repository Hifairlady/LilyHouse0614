package com.edgar.lilyhouse.UI.MainScene;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.edgar.lilyhouse.Data.MainSceneData.MangaItem;
import com.edgar.lilyhouse.Data.MainSceneData.MangaRepository;
import com.edgar.lilyhouse.Network.NetworkResponse;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = "==============" + MainViewModel.class.getSimpleName();
    private MangaRepository repository;
    private LiveData<List<MangaItem>> listItems;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new MangaRepository(application);
    }

    public LiveData<List<MangaItem>> getAllItems() {
        return repository.getListItems();
    }

    public LiveData<MangaItem> getListItemById(int id) {
        return repository.getListItemById(id);
    }

    public void deleteItem(MangaItem listItem) {
        repository.deleteItem(listItem);
    }

    public void deleteAllItems() {
        repository.deleteAllItems();
    }

    public void insertItems(MangaItem... mangaItems) {
        repository.insertItems(mangaItems);
    }

    public void refreshData(String queryUrl, NetworkResponse response) {
        repository.refreshData(queryUrl, response);
    }

    public void setSortOrder(int sortOrder) {
        repository.setSortOrder(sortOrder);
    }
}
