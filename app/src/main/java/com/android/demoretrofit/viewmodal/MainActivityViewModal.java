package com.android.demoretrofit.viewmodal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.android.demoretrofit.model.MovieDataSorce;
import com.android.demoretrofit.model.MovieDataSourceFactory;
import com.android.demoretrofit.model.MovieRepository;
import com.android.demoretrofit.model.Result;
import com.android.demoretrofit.service.MovieApiService;
import com.android.demoretrofit.service.RetrofitInctance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModal extends AndroidViewModel {
    private MovieRepository movieRepository;// создаем переменную репозитория
    private LiveData<MovieDataSorce> movieDataSorceLiveData;// создаем прокладку для дата сорс
    private Executor executor;// создаем класс екзекутор
    private LiveData<PagedList<Result>> pagedListLiveData;// создаем переменную прокладки для пейджлиста

    public MainActivityViewModal(@NonNull Application application) {
        super (application);//создаем контсруктор с аппликатион(класс для сохранения состояния)
        movieRepository=new MovieRepository (application);// создаем обьект репозитория загружаем сохранение
        MovieApiService movieApiService= RetrofitInctance.getService ();//создаем экземпляр нашего ретрофита
        MovieDataSourceFactory movieDataSourceFactory=new MovieDataSourceFactory (application,movieApiService);
        //создаем обьект нашей прокладки и даем отсылку на запись и интерфейс
       movieDataSorceLiveData=movieDataSourceFactory.getMutableLiveData ();// записываем наши данные из дата сорс
        PagedList.Config config=new PagedList.Config.Builder ().setEnablePlaceholders (true)
                //передаем тру так как нет пустых заполнителей
                .setInitialLoadSizeHint (10)// определяем сколько элементов будет при первой загрузке
                .setPageSize (20)// определяем количество элементов одновременно загружаемых
                .setPrefetchDistance (3)// расстояние от края
                .build ();
        executor= Executors.newCachedThreadPool ( );// записываем метод который удаляет неиспользуемые потоки
        // в кэше
        pagedListLiveData=new LivePagedListBuilder<Long,Result> (movieDataSourceFactory,config)
                .setFetchExecutor (executor)// назначаем управляющим потоками ексзекутор
                .build ();


    }
    public LiveData<List<Result>> getAllMovieData(){



        return movieRepository.getMutableLiveData ();// возвращаем из прокладки данные
    }

    public LiveData<PagedList<Result>> getPagedListLiveData() {
        return pagedListLiveData;// возвращаем из пейджера данные
    }
}
