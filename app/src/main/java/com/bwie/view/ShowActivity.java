package com.bwie.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bwie.R;

public class ShowActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        img = findViewById(R.id.img);

        //设置圆角
        setImages();

    }

    private void setImages() {
        Glide
                .with(this)
                .load(R.drawable.head)
                .apply(RequestOptions.circleCropTransform())
                .into(img);
    }
}
