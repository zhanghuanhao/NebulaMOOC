package com.nebula.mooc.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 15722 on 2019/5/5.
 */
public class Page<T> implements Serializable {
    public static final long serialVersionUID = 1L;
    private long id;
    private int currentPage;
    private int pageSize;
    private int total;
    private int offset;
    private List<T> list;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
