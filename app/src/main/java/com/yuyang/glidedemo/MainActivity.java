package com.yuyang.glidedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.security.MessageDigest;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

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
                default:break;
        }
    }


    private void loadIntIv() {
        String url = "http://cn.bing.com/az/hprichbg/rb/AvalancheCreek_ROW11173354624_1920x1080.jpg";
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.iv_null)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .override(Target.SIZE_ORIGINAL);

        Glide.with(this)
                .load(url)
                .placeholder(R.mipmap.iv_null)
                .apply(requestOptions)
                .into(imageView);

        Log.d("wPx", imageView.getWidth() + " ");
        Log.d("hPx", imageView.getHeight() + " ");

        int wPx = imageView.getWidth();
        int hPx = imageView.getHeight();
        System.out.println("wPx:" + wPx + "  hPx:" + hPx);
    }


    public void downloadImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://www.guolin.tech/book.png";
                    final Context context = getApplicationContext();
                    FutureTarget<File> target = Glide.with(context)
                            .asFile()
                            .load(url)
                            .submit();
                    final File imageFile = target.get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, imageFile.getPath(), Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void loadNetIv() {

        downloadImage();
        String url = "http://cn.bing.com/az/hprichbg/rb/AvalancheCreek_ROW11173354624_1920x1080.jpg";

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.iv_null)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .circleCrop()
                .skipMemoryCache(true);


        Glide.with(this)
                .load(url)
                .apply(requestOptions)
                .into(imageView);
//                .into(new SimpleTarget<Drawable>() {
//                          @Override
//                          public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                              imageView.setImageDrawable(resource);
//                          }
//                      }
//                );


        Log.d("wPx", imageView.getWidth() + " ");
        Log.d("hPx", imageView.getHeight() + " ");

//        Glide.with(this)
//             //   .asBitmap()
//                .load(url)
//                .listener(new RequestListener<Bitmap>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                })
//                .placeholder(R.mipmap.iv_null)//空占位图
//                .error(R.mipmap.iv_01) //异常占位图
                //.override(50,50)   //设置图片分辨率大小
             //  .dontTransform()//加载图片时不进行变换
           //     .centerCrop()
              // .centerInside()
              //  .fitCenter()
             //   .centerInside()

                 // .centerCrop()
                 // .override(500, 500)  //指定分辨率大小
                // .diskCacheStrategy(DiskCacheStrategy.DATA)//禁用Glide磁盘缓存的缓存功能
              //  .transform(new GrayscaleTransformation(),new BlurTransformation(20))

//               .into(imageView);

              int wPx = imageView.getWidth();
              int hPx = imageView.getHeight();
              System.out.println("wPx:" + wPx + "  hPx:" + hPx);
    }

}
