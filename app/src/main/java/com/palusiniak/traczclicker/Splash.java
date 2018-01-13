package com.palusiniak.traczclicker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 4000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent i = new Intent(Splash.this, MainActivity.class);

                startActivity(i);
                finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}