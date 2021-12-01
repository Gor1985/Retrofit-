package com.android.demoretrofit.service;

import com.android.demoretrofit.model.MovieApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("movie/popular")// создаем метод для загрузки всех данных по ключу
    Call<MovieApiResponse> getPopularMovies(@Query ("api_key")String apiKey);
    @GET("movie/popular")
    // создаем метод для загрузки данных по столбцу(как в бд)
    Call<MovieApiResponse> getPopularMoviesWithPadding(@Query ("api_key")String apiKey,@Query ("page")long page);

}
