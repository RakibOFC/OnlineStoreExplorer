package com.rakibofc.onlinestoreexplorer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.rakibofc.onlinestoreexplorer.databinding.ActivityStoreBinding;
import com.rakibofc.onlinestoreexplorer.utility.Constants;

public class StoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityStoreBinding binding = ActivityStoreBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();

        setContentView(binding.getRoot());

        binding.tvStoreName.setText(intent.getStringExtra(Constants.STORE_NAME_KEY));
        binding.tvStoreId.setText(String.valueOf(intent.getIntExtra(Constants.STORE_ID_KEY, 1)));
        binding.tvStoreAddress.setText(intent.getStringExtra(Constants.STORE_ADDRESS_KEY));

        binding.topAppBar.setNavigationOnClickListener(v -> onBackPressed());
    }
}