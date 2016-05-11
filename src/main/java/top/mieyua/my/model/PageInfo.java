/*
 * Copyright (C) Mie Yua <mieyua@126.com>, 2016.
 * All rights reserved.
 */

package top.mieyua.my.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 分页展示类
 */
public class PageInfo {
    @JsonProperty("current_page")
    private int currentPage;
    @JsonProperty("items_per_page")
    private int itemsPerPage;
    @JsonProperty("total_page")
    private int totalPage;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}

