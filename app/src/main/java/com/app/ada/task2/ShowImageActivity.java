package com.app.ada.task2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class ShowImageActivity extends ActionBarActivity {

    private String imageResId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        imageResId = getIntent().getStringExtra("imageResId");
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(getResources().getIdentifier(imageResId,"drawable",getPackageName()));
    }

}
