package com.rakibofc.onlinestoreexplorer.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchStore {

    @SerializedName("data")
    private List<StoreData> storeDataList;
    private Links links;
    private Meta meta;

    public List<StoreData> getStoreDataList() {
        return storeDataList;
    }

    public Links getLinks() {
        return links;
    }

    public Meta getMeta() {
        return meta;
    }

    public static class StoreData {

        private int id;
        private String name;
        private String address;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }
    }

    public static class Links {

        private String first;
        private String last;
        private String prev;
        private String next;

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }

        public String getPrev() {
            return prev;
        }

        public String getNext() {
            return next;
        }
    }

    public static class Meta {

        @SerializedName("current_page")
        private int currentPage;

        private int from;

        @SerializedName("last_page")
        private int lastPage;

        private String path;

        @SerializedName("per_page")
        private int perPage;

        private int to;
        private int total;

        public int getCurrentPage() {
            return currentPage;
        }

        public int getFrom() {
            return from;
        }

        public int getLastPage() {
            return lastPage;
        }

        public String getPath() {
            return path;
        }

        public int getPerPage() {
            return perPage;
        }

        public int getTo() {
            return to;
        }

        public int getTotal() {
            return total;
        }
    }
}
