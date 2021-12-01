package com.android.demoretrofit.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.android.demoretrofit.service.MovieApiService;

public class MovieDataSourceFactory extends DataSource.Factory {
    // создаем прокладку для дата сорс
    private MovieDataSorce movieDataSorce;
    private Application application;
    private MovieApiService movieApiService;
    private MutableLiveData<MovieDataSorce> mutableLiveData;

    public MovieDataSourceFactory(Application application, MovieApiService movieApiService) {
        this.application = application;// сохрание
        this.movieApiService = movieApiService;//
        this.mutableLiveData=new MutableLiveData<> (  );//создаем экземпляр мутабле лайв дата(для того что бы публично
        // задекларировать метод пост валуе
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSorce=new MovieDataSorce (movieApiService,application);
        //записываем наш интерфейс и сохранение
        mutableLiveData.postValue (movieDataSorce);// назначаем данные  из муви дата сорс
        return movieDataSorce;
    }

    public MutableLiveData<MovieDataSorce> getMutableLiveData() {
        return mutableLiveData;// возвращаем нашу прокладку
    }
}
