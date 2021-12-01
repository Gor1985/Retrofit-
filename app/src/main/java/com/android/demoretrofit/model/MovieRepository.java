package com.android.demoretrofit.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.android.demoretrofit.R;
import com.android.demoretrofit.service.MovieApiService;
import com.android.demoretrofit.service.RetrofitInctance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ArrayList<Result> results=new ArrayList<> (  );
    private MutableLiveData<List<Result>> mutableLiveData=new MutableLiveData<> (  );
    private Application application;


    public MovieRepository(Application application) {
        this.application = application;
    }
// так как репозиторий мы =перенесли в мове дата сорсе (просто сохранил класс для примера) то все хорошо


    public MutableLiveData<List<Result>> getMutableLiveData() {
        MovieApiService movieApiService= RetrofitInctance.getService ();
        Call<MovieApiResponse> call=movieApiService.getPopularMovies (application.getApplicationContext ().
                getString (R.string.api_key));
        call.enqueue (new Callback<MovieApiResponse> ( ) {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                MovieApiResponse movieApiResponse = response.body ( );
                if (movieApiResponse != null && movieApiResponse.getResults ( ) != null) {
                    results = (ArrayList<Result>) movieApiResponse.getResults ( );
                   mutableLiveData.setValue (results);
                }


            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }


        });
        return mutableLiveData;
    }
}
