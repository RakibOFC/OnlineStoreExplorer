package com.rakibofc.onlinestoreexplorer.model;

public class Page {

    private final int currentPage;
    private final int from;
    private final int lastPage;
    private final String path;
    private final int perPage;
    private final int to;
    private final int total;

    public Page(int currentPage, int from, int lastPage, String path, int perPage, int to, int total) {
        this.currentPage = currentPage;
        this.from = from;
        this.lastPage = lastPage;
        this.path = path;
        this.perPage = perPage;
        this.to = to;
        this.total = total;
    }

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
