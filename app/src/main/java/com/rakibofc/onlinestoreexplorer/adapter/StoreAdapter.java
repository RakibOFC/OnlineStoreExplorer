package com.rakibofc.onlinestoreexplorer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rakibofc.onlinestoreexplorer.R;
import com.rakibofc.onlinestoreexplorer.helper.ItemClickListener;
import com.rakibofc.onlinestoreexplorer.model.Store;
import com.rakibofc.onlinestoreexplorer.viewholder.StoreViewHolder;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreViewHolder> {

    private final List<Store> storeList;
    private final ItemClickListener itemClickListener;

    public StoreAdapter(List<Store> storeList, ItemClickListener itemClickListener) {
        this.storeList = storeList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item, parent, false);
        return new StoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {

        Store store = storeList.get(position);
        holder.bind(store, itemClickListener, position);
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public void addData(List<Store> stores) {
        int startPosition = storeList.size();
        storeList.addAll(stores);
        notifyItemRangeInserted(startPosition, stores.size());
    }
}
