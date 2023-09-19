package com.rakibofc.onlinestoreexplorer.helper;

public interface DataFetchingListener<T> {

    void onDataFetched(T response);

    void onFailed(int status);
}