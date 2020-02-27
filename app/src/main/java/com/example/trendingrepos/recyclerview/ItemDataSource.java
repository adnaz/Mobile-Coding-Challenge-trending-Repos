package com.example.trendingrepos.recyclerview;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.trendingrepos.response.GitApiResponse;
import com.example.trendingrepos.response.Item;
import com.example.trendingrepos.retrofit.RetrofitClient;
import com.example.trendingrepos.response.GitApiResponse;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {
    public static final int PAGE_SIZE = 30;
    //we will start from the first page which is 1
    private static final int FIRST_PAGE = 1;
    LocalDate date = LocalDate.now().minusDays(30);
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {
        Log.d("h",date.toString());
        RetrofitClient.getInstance()
                .getApi().getRepos(FIRST_PAGE,"created:>"+date.toString())
                .enqueue(new Callback<GitApiResponse>() {
                    @Override
                    public void onResponse(Call<GitApiResponse> call, Response<GitApiResponse> response) {
                        if (response.body() != null) {

                            callback.onResult(response.body().items, null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<GitApiResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi().getRepos(params.key , "created:>"+date.toString())
                .enqueue(new Callback<GitApiResponse>() {
                    @Override
                    public void onResponse(Call<GitApiResponse> call, Response<GitApiResponse> response) {

                        //if the current page is greater than one
                        //we are decrementing the page number
                        //else there is no previous page
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {

                            //passing the loaded data
                            //and the previous page key
                            callback.onResult(response.body().items, adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<GitApiResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getRepos(params.key, "created:>"+date.toString())
                .enqueue(new Callback<GitApiResponse>() {
                    @Override
                    public void onResponse(Call<GitApiResponse> call, Response<GitApiResponse> response) {

                        if (response.body() != null) {
                            //if the response has next page
                            //incrementing the next page number
                            Integer key = params.key + 1 ;

                            //passing the loaded data and next page value
                            callback.onResult(response.body().items, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<GitApiResponse> call, Throwable t) {

                    }
                });
    }
    }

