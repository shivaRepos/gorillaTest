package com.example.gorillatest;

import java.util.ArrayList;

  class ApiResponse {
        Integer page;
        Integer per_page;
        Integer total;
        Integer total_pages;

        ArrayList<DataList> data;

    public Integer getPage() {
        return page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public ArrayList<DataList> getData() {
        return data;
    }
}
