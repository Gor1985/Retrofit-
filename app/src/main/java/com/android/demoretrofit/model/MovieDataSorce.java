package com.android.demoretrofit.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.android.demoretrofit.R;
import com.android.demoretrofit.service.MovieApiService;
import com.android.demoretrofit.service.RetrofitInctance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSorce extends PageKeyedDataSource<Long,Result> {
    // создаем класс для передачи данных по ключу
    private MovieApiService movieApiService;// создаем переменные для ретрофита
    private Application application;// создаем класс для сохранения

    public MovieDataSorce(MovieApiService movieApiService, Application application) {
        this.movieApiService = movieApiService;
        this.application = application;
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> loadParams, @NonNull LoadCallback<Long, Result>  loadCallback) {
//загрузка следующей порции данных
        movieApiService= RetrofitInctance.getService ();
        Call<MovieApiResponse> call=movieApiService.getPopularMoviesWithPadding (
                application.getApplicationContext ().getString (R.string.api_key),loadParams.key);
// выводим звонок по ключу айпи , то есть мы звоним и на сайт передаем наш ключ доступа и загружаем
        // по значению(таблице)

        call.enqueue (new Callback<MovieApiResponse> ( ) {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                MovieApiResponse movieApiResponse= response.body ( );
                ArrayList<Result> results=new ArrayList<> (  );// создаем список
                if (movieApiResponse!=null && movieApiResponse.getResults ()!=null) {
                    // если данные не пустые
                    results = (ArrayList<Result>) movieApiResponse.getResults ( );
                    // загружаем в список результаты ответа от сервера
                    loadCallback.onResult (results, (long) 2 + 1);
                    //загрузка результатов обратного вызова по ключу(ключ у нас вторая страница и прибавляем один
                    //что бы обновлялось прибавляем+1
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
//  метод для обработки ошибок
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> loadParams, @NonNull LoadCallback<Long, Result> loadCallback) {

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> loadInitialParams, @NonNull LoadInitialCallback<Long, Result> loadInitialCallback) {
        // первоначальная загрузка данных из нашего ретрофита
        movieApiService= RetrofitInctance.getService ();
        Call<MovieApiResponse> call=movieApiService.getPopularMoviesWithPadding (
                application.getApplicationContext ().getString (R.string.api_key),1);

        call.enqueue (new Callback<MovieApiResponse> ( ) {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                MovieApiResponse movieApiResponse= response.body ( );
                ArrayList<Result> results=new ArrayList<> (  );
                if (movieApiResponse!=null && movieApiResponse.getResults ()!=null) {
                    results = (ArrayList<Result>) movieApiResponse.getResults ( );
                    loadInitialCallback.onResult (results, null, (long) 2);
                    //загружаем со второй страницы
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });
    }
}
