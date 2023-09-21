package com.rakibofc.onlinestoreexplorer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rakibofc.onlinestoreexplorer.helper.ApiHelper;
import com.rakibofc.onlinestoreexplorer.helper.DataFetchingListener;
import com.rakibofc.onlinestoreexplorer.model.StoreInfo;
import com.rakibofc.onlinestoreexplorer.receiver.ConnectionReceiver;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isNetworkConnected = new MutableLiveData<>();
    private final MutableLiveData<StoreInfo> storeInfoLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> statusLiveData = new MutableLiveData<>();

    public LiveData<Boolean> getIsNetworkConnected() {
        return isNetworkConnected;
    }

    public MutableLiveData<StoreInfo> getStoreInfoLiveData() {
        return storeInfoLiveData;
    }

    public MutableLiveData<Integer> getStatusLiveData() {
        return statusLiveData;
    }

    public void initInternetConnection(ConnectionReceiver connectionReceiver) {
        connectionReceiver.setNetworkStateChangeListener(isNetworkConnected::postValue);
    }

    public void fetchStoreData(int pageNo) {
        ApiHelper.getStores(pageNo, new DataFetchingListener<StoreInfo>() {
            @Override
            public void onDataFetched(StoreInfo response) {

                storeInfoLiveData.postValue(response);
            }

            @Override
            public void onFailed(int status) {
                statusLiveData.postValue(status);
            }
        });
    }
}
