package com.example.infobooks.api;

import com.example.infobooks.model.LivroResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService
{
    @GET("search.json")
    Call<LivroResponse> buscarLivros(
            @Query("q") String query,
            @Query("limit") int limit
    );

    @GET("search.json")
    Call<LivroResponse> buscarLivrosPorAutor(
            @Query("author") String author,
            @Query("limit") int limit
    );
}