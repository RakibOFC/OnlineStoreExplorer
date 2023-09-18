package com.rakibofc.onlinestoreexplorer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rakibofc.onlinestoreexplorer.R;
import com.rakibofc.onlinestoreexplorer.model.Store;
import com.rakibofc.onlinestoreexplorer.viewholder.StoreViewHolder;

public class StoreAdapter extends RecyclerView.Adapter<StoreViewHolder> {

    private final int noOfStore;

    public StoreAdapter(int noOfStore) {
        this.noOfStore = noOfStore;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item, parent, false);
        return new StoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {

        Store store = new Store(position, "Store Name", "Store Address");
        holder.bind(store);
    }

    @Override
    public int getItemCount() {
        return noOfStore;
    }
}
