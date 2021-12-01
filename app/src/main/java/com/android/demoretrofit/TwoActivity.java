package com.android.demoretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.demoretrofit.databinding.ActivityTwoBinding;
import com.android.demoretrofit.model.Result;
import com.bumptech.glide.Glide;

public class TwoActivity extends AppCompatActivity {
private Result result;// создаем переменную класса результ

private ActivityTwoBinding activityTwoBinding;// создаем переменную для биндинга
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_two);

        activityTwoBinding= DataBindingUtil.setContentView (this,R.layout.activity_two);
// расшариваем в биндинг наш хмл


        Intent intent =getIntent ();//принмиаем интент
        if(intent!=null && intent.hasExtra ("movieData")){
/// проверяем на наполненность
         result=intent.getParcelableExtra ("movieData");
/// загружаем сохранненный интент
         activityTwoBinding.setResult (result);//загружаем в хмл





        }
    }
}