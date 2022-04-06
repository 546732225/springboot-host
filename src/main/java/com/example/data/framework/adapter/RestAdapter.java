package com.example.data.framework.adapter;


import com.example.data.framework.RestResponse;


public class RestAdapter<T> {


    public RestResponse<T> apply(T data) {
        return RestResponse.success(data);
    }
}
