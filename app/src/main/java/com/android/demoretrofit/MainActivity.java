package com.android.demoretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.demoretrofit.adapter.ResultAdapter;
import com.android.demoretrofit.databinding.ActivityMainBinding;
import com.android.demoretrofit.model.MovieApiResponse;
import com.android.demoretrofit.model.Result;
import com.android.demoretrofit.service.MovieApiService;
import com.android.demoretrofit.service.RetrofitInctance;
import com.android.demoretrofit.viewmodal.MainActivityViewModal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private PagedList<Result> results;//создаем оьект классе паге и записываем в него резулть
    private RecyclerView recyclerView;// создаем переменную для ресайклер вью
    private ResultAdapter adapter;// создаем переменную для адаптера
    private SwipeRefreshLayout swipeRefreshLayout;//создаем переменную для обновления данных
    private MainActivityViewModal mainActivityViewModel;// создаем переменную нашей модели
    private ActivityMainBinding activityMainBinding;// создаем переменную для класса биндинг для связки с нашим вью



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);// связываем наш лайот с биндингом

        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModal.class);// записываем в нашу вивев модель класс

        getPopularMovies();// вызываем метод для загрузки фильмов

        swipeRefreshLayout = activityMainBinding.swiperefresh;// связываем биндинг с рефрешем
        swipeRefreshLayout.setColorSchemeResources(R.color.design_default_color_primary);
        // ставим цвет нашего реферша
        swipeRefreshLayout.setOnRefreshListener(// включаем слушатель
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        getPopularMovies();// добавляем метод обновления фильмов при одновлении рефреша

                    }
                });
    }

    public void getPopularMovies() {

//        mainActivityViewModel.getAllMovieData().observe(this,
//                new Observer<List<Result>>() { метод для ресайклер
//                    @Override
//                    public void onChanged(List<Result> resultList) {
//                        results = (ArrayList<Result>) resultList;
//                        fillRecyclerView();
//                    }
//                });

        mainActivityViewModel.getPagedListLiveData().observe(this,
                new Observer<PagedList<Result>>() {// обозреваем нашу модель
                    @Override
                    public void onChanged(PagedList<Result> resultList) {

                        results = resultList;// записываем список в наш аррайлист
                        fillRecyclerView();// говорим что надо обновить расйклер вью

                    }
                });

    }

    private void fillRecyclerView() {// метод для обновления ресайклер вью

        recyclerView = activityMainBinding.recyclerView;// связываем с биндингом
        adapter = new ResultAdapter(this);//создаем обьект адаптера
        adapter.submitList(results);// загружаем в него наш список

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {// если ориентация портрет

            recyclerView.setLayoutManager(
                    new GridLayoutManager(this, 2));// делаем две позицию в колонку

        } else {

            recyclerView.setLayoutManager(
                    new GridLayoutManager(this, 4));// четыре

        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());// добавдяем анимацию в рециклер
        recyclerView.setAdapter(adapter);// загружаем адаптер в наш рециклер
        adapter.notifyDataSetChanged();// говорим рециклеру что надо обновиться
        swipeRefreshLayout.setRefreshing(false);// выключаем его после обновления
    }
}
