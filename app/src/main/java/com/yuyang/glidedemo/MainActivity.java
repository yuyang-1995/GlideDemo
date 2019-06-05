package com.yuyang.glidedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_01, btn_02;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVL();
    }

    private void initVL() {
        btn_01 = findViewById(R.id.btn_01);
        btn_02 = findViewById(R.id.btn_02);
        imageView = findViewById(R.id.iv);
        btn_01.setOnClickListener(this);
        btn_02.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_01:
                loadNetIv();
                break;
            case R.id.btn_02:
                loadIntIv();
                break;
        }

    }

    private void loadIntIv() {

        Glide.with(this)
                .load(R.mipmap.iv_01)
                .into(imageView);
    }

    private void loadNetIv() {

        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        Glide.with(this)
                .load(url)
                .placeholder(R.mipmap.iv_null)//占位图
                .diskCacheStrategy(DiskCacheStrategy.NONE)//禁用Glide 的缓存功能
                .into(imageView);


    }
}
