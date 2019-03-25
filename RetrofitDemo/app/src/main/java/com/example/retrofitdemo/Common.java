package com.example.retrofitdemo;

public class Common {

    public static final String BASE_URL ="https://simplifiedcoding.net/demos/";

    public static ApiServices getServices(){
        return new RetrofitClient().getRetrofit(BASE_URL).create(ApiServices.class);
    }
}
