package com.rakibofc.onlinestoreexplorer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rakibofc.onlinestoreexplorer.helper.ApiHelper;
import com.rakibofc.onlinestoreexplorer.helper.DataFetchingListener;
import com.rakibofc.onlinestoreexplorer.model.Store;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<Store>> storeListLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> statusLiveData = new MutableLiveData<>();

    public LiveData<List<Store>> getStoreList() {
        return storeListLiveData;
    }

    public LiveData<Integer> getStatus() {
        return statusLiveData;
    }

    public void fetchStoreData(int pageNo) {
        ApiHelper.getStores(pageNo, new DataFetchingListener<List<Store>>() {
            @Override
            public void onDataFetched(List<Store> response) {
                storeListLiveData.postValue(response);
            }

            @Override
            public void onFailed(int status) {
                statusLiveData.postValue(status);
            }
        });
    }
}
