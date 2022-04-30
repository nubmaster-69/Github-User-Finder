package com.hisu.retrofit.api;

import com.hisu.retrofit.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {
    GithubApi githubApiService = new Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GithubApi.class);

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);
}