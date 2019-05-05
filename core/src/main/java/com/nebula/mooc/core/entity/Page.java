package com.nebula.mooc.core.entity;

/**
 * Created by 15722 on 2019/5/5.
 */
public class Page {
    private long id;
    private int currentPage;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
