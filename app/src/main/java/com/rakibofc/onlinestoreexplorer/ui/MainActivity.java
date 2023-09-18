package com.rakibofc.onlinestoreexplorer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rakibofc.onlinestoreexplorer.adapter.StoreAdapter;
import com.rakibofc.onlinestoreexplorer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvStoreList.setAdapter(new StoreAdapter(190));
    }
}