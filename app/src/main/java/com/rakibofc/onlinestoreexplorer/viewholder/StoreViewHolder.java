package com.rakibofc.onlinestoreexplorer.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rakibofc.onlinestoreexplorer.R;
import com.rakibofc.onlinestoreexplorer.model.Store;

public class StoreViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvStoreId;
    private final TextView tvStoreName;
    private final TextView tvStoreAddress;

    public StoreViewHolder(@NonNull View itemView) {
        super(itemView);

        tvStoreId = itemView.findViewById(R.id.tv_store_id);
        tvStoreName = itemView.findViewById(R.id.tv_store_name);
        tvStoreAddress = itemView.findViewById(R.id.tv_store_address);
    }

    public void bind(Store store) {
        tvStoreId.setText(String.valueOf(store.getId()));
        tvStoreName.setText(store.getName());
        tvStoreAddress.setText(store.getAddress());
    }
}