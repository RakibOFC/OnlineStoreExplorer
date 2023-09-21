package com.rakibofc.onlinestoreexplorer.model;

import java.util.List;

public class StoreInfo {

    private final List<Store> storeList;
    private final Page Page;

    public StoreInfo(List<Store> storeList, Page page) {
        this.storeList = storeList;
        Page = page;
    }

    public List<Store> getStoreList() {
        return storeList;
    }

    public Page getPage() {
        return Page;
    }
}