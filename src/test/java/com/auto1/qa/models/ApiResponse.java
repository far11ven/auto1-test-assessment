package com.auto1.qa.models;

public class ApiResponse {
    private Wkda wkda;

    private String pageSize;

    private String totalPageCount;

    private String page;

    public Wkda getWkda() {
        return wkda;
    }

    public void setWkda(Wkda wkda) {
        this.wkda = wkda;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(String totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}