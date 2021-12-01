package com.android.demoretrofit.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInctance {// создаем класс для основной ссылки ретрофита
    private static Retrofit retrofit=null;// создаем переменную ретрофита
    private static String BASE_URL="https://api.themoviedb.org/3/";// записываем ссылку

    public static MovieApiService getService(){
        if (retrofit==null){// если ретровит не равен нулю
            retrofit=new Retrofit.Builder ().baseUrl (BASE_URL).addConverterFactory (GsonConverterFactory.create (  ))
                    .build ();// записываем в него ссылку и записываем что принимаем данные в виде json
        }
        return retrofit.create (MovieApiService.class);// возвращаем созданный ретрофит на базе нашего интерфейса
    }
}
