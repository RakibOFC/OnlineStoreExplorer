package com.rakibofc.onlinestoreexplorer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    private final int noOfStore;

    public StoreAdapter(int noOfStore) {
        this.noOfStore = noOfStore;
    }

    @NonNull
    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreAdapter.ViewHolder holder, int position) {

        Store store = new Store(position, "Store Name", "Store Address");
        holder.bind(store);
    }

    @Override
    public int getItemCount() {
        return noOfStore;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvStoreId;
        private final TextView tvStoreName;
        private final TextView tvStoreAddress;

        public ViewHolder(@NonNull View itemView) {
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
}
