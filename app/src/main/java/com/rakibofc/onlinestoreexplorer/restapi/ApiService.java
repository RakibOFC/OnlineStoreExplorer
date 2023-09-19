package com.rakibofc.onlinestoreexplorer.restapi;

import com.rakibofc.onlinestoreexplorer.model.FetchStore;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/stores/")
    Call<FetchStore> getStore(@Query("page") int page);
}
