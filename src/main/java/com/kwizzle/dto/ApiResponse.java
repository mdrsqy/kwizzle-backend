package com.kwizzle.dto;

public class ApiResponse<T> {
    private boolean sukses;
    private String pesan;
    private T data;

    public ApiResponse() {}

    public ApiResponse(boolean sukses, String pesan, T data) {
        this.sukses = sukses;
        this.pesan = pesan;
        this.data = data;
    }

    public boolean isSukses() {
        return sukses;
    }

    public void setSukses(boolean sukses) {
        this.sukses = sukses;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}