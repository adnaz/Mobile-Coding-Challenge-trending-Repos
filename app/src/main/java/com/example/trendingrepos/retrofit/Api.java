package com.example.trendingrepos.retrofit;

import com.example.trendingrepos.response.GitApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("repositories?sort=stars&order=desc")
    Call<GitApiResponse> getRepos(@Query("page") int page, @Query("q") String createdDate);
}
