package com.rakibofc.onlinestoreexplorer.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.rakibofc.onlinestoreexplorer.R;
import com.rakibofc.onlinestoreexplorer.adapter.StoreAdapter;
import com.rakibofc.onlinestoreexplorer.databinding.ActivityMainBinding;
import com.rakibofc.onlinestoreexplorer.model.Page;
import com.rakibofc.onlinestoreexplorer.receiver.ConnectionReceiver;
import com.rakibofc.onlinestoreexplorer.utility.Constants;
import com.rakibofc.onlinestoreexplorer.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private ConnectionReceiver connectionReceiver;
    private boolean isNoInternetMessageSent;
    private int pageNo = 1;
    private Page pageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        StoreAdapter storeAdapter = new StoreAdapter(new ArrayList<>());

        // Initialize connection status receiver
        intentFilter = new IntentFilter();
        connectionReceiver = new ConnectionReceiver();

        setContentView(view);

        binding.rvStoreList.setAdapter(storeAdapter);

        // Fetch data by view model
        viewModel.initInternetConnection(connectionReceiver);
        viewModel.fetchStoreData(pageNo);

        // Handle live data from view model
        handleLiveData(viewModel, storeAdapter, view);

        binding.rvStoreList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int itemCount = Objects.requireNonNull(recyclerView.getAdapter()).getItemCount();

                if (layoutManager != null && pageInfo != null) {
                    int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();

                    if (lastItem + 1 == itemCount &&
                            pageNo < pageInfo.getLastPage() &&
                            pageNo <= pageInfo.getCurrentPage()) {

                        pageNo++;
                        viewModel.fetchStoreData(pageNo);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        // Add action in intent filter
        intentFilter.addAction(Constants.CONNECTIVITY_ACTION);

        binding.fabInfo.setOnClickListener(v -> showPageInfoSheet());
    }

    private void showPageInfoSheet() {
        PageInfoSheetFragment fragment = PageInfoSheetFragment.newInstance();
        fragment.setPageInfo(pageInfo);
        fragment.show(getSupportFragmentManager(), Constants.SHEET_TAG);
    }

    private void handleLiveData(MainViewModel viewModel, StoreAdapter storeAdapter, View view) {

        // Get connection status
        viewModel.getIsNetworkConnected().observe(this, isConnected -> {

            if (isConnected && isNoInternetMessageSent) {

                Snackbar.make(view, R.string.internet_connection_restored_msg, Toast.LENGTH_SHORT).show();
                viewModel.fetchStoreData(pageNo);
                isNoInternetMessageSent = false;

            } else if (!isConnected) {
                Snackbar.make(view, R.string.no_connection_msg, Toast.LENGTH_SHORT).show();
                isNoInternetMessageSent = true;
            }
        });

        // Get store info data from view model
        viewModel.getStoreInfoLiveData().observe(this, storeInfo -> {

            storeAdapter.addData(storeInfo.getStoreList());
            pageInfo = storeInfo.getPage();
        });

        // Get error status code from view model
        viewModel.getStatusLiveData().observe(this, status -> {

            if (status == 404) {
                Toast.makeText(this, R.string.not_found_msg, Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(this, R.string.something_went_wrong_msg, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(connectionReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(connectionReceiver);
    }
}