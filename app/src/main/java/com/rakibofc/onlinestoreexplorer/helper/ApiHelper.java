package com.rakibofc.onlinestoreexplorer.helper;

import androidx.annotation.NonNull;

import com.rakibofc.onlinestoreexplorer.model.FetchStore;
import com.rakibofc.onlinestoreexplorer.model.Store;
import com.rakibofc.onlinestoreexplorer.restapi.ApiClient;
import com.rakibofc.onlinestoreexplorer.restapi.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiHelper {

    public static void getStores(int pageNo, DataFetchingListener<List<Store>> storesListener) {

        ApiService apiService = ApiClient.getInstance().create(ApiService.class);
        Call<FetchStore> fetchStoreCall = apiService.getStore(pageNo);

        fetchStoreCall.enqueue(new Callback<FetchStore>() {
            @Override
            public void onResponse(@NonNull Call<FetchStore> call, @NonNull Response<FetchStore> storeResponse) {

                if (storeResponse.isSuccessful()) {

                    FetchStore fetchStore = storeResponse.body();

                    if (fetchStore != null) {

                        List<Store> storeList = new ArrayList<>();

                        for (FetchStore.StoreData storeData : fetchStore.getStoreDataList()) {
                            storeList.add(new Store(storeData.getId(), storeData.getName(), storeData.getAddress()));
                        }
                        storesListener.onDataFetched((storeList));

                    } else storesListener.onFailed(0);

                } else storesListener.onFailed(storeResponse.code());
            }

            @Override
            public void onFailure(@NonNull Call<FetchStore> call, @NonNull Throwable t) {
                storesListener.onFailed(0);
            }
        });
    }
}
