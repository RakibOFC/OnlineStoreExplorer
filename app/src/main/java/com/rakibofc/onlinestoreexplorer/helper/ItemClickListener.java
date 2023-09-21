package com.rakibofc.onlinestoreexplorer.helper;

import android.view.View;

import com.rakibofc.onlinestoreexplorer.model.Store;

public interface ItemClickListener {
    void onItemClick(View v, Store storeData, int position);
}
