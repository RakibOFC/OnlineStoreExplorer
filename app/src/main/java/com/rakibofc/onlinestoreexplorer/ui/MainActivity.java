package com.rakibofc.onlinestoreexplorer.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.rakibofc.onlinestoreexplorer.adapter.StoreAdapter;
import com.rakibofc.onlinestoreexplorer.databinding.ActivityMainBinding;
import com.rakibofc.onlinestoreexplorer.receiver.ConnectionReceiver;
import com.rakibofc.onlinestoreexplorer.utility.Constants;
import com.rakibofc.onlinestoreexplorer.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private ConnectionReceiver receiver;
    private int pageNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        StoreAdapter storeAdapter = new StoreAdapter(new ArrayList<>());

        // Initialize connection status receiver
        intentFilter = new IntentFilter();
        receiver = new ConnectionReceiver();

        setContentView(binding.getRoot());

        binding.rvStoreList.setAdapter(storeAdapter);

        // Fetch data by view model
        viewModel.fetchStoreData(pageNo);

        // Add action in intent filter
        intentFilter.addAction(Constants.CONNECTIVITY_ACTION);

        // Get data from view model
        viewModel.getStoreList().observe(this, storeAdapter::addData);

        binding.rvStoreList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int itemCount = Objects.requireNonNull(recyclerView.getAdapter()).getItemCount();

                if (layoutManager != null) {
                    int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();

                    if (lastItem + 1 == itemCount) {
                        pageNo++;
                        viewModel.fetchStoreData(pageNo);
                        Log.e("IfPageNo", pageNo + "");
                    }
                    Log.e("PageNo", pageNo + "");
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}