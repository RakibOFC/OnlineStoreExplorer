package com.rakibofc.onlinestoreexplorer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rakibofc.onlinestoreexplorer.R;
import com.rakibofc.onlinestoreexplorer.adapter.StoreAdapter;
import com.rakibofc.onlinestoreexplorer.databinding.ActivityMainBinding;
import com.rakibofc.onlinestoreexplorer.model.Store;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvStoreList.setAdapter(new StoreAdapter(getStoreDataList()));
    }

    private List<Store> getStoreDataList() {

        List<Store> storeDataList = new ArrayList<>();

        for (int i = 1; i <= 190; i++) {

            storeDataList.add(new Store(i, getString(R.string.store_name_text), getString(R.string.store_address_text)));
        }
        return storeDataList;
    }
}