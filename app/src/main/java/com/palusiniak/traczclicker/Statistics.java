package com.palusiniak.traczclicker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.MainThread;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Statistics extends Activity {

    public static long resetMultiplier;
    public static String resetMultiplierKeyString = "resetMultiplier";

    public long totalDot;

    public TextView totalDots;
    public TextView totalDpc;
    public TextView totalDps;
    public TextView totalBonus;
    public TextView totalReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.32));

        final Handler handler = new Handler();

        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                totalDot = MainActivity.totalDpc + MainActivity.totalDps;
                totalDots = (TextView) findViewById(R.id.totalDots);
                totalDots.setText("Łączny zysk: " + totalDot + "$");

                totalDps = (TextView) findViewById(R.id.totalDps);
                totalDps.setText("Łączny zysk z włości: " + MainActivity.totalDps + "$");

                handler.postDelayed(this, 100);
            }
        };
        handler.post(runnableCode);

        totalDpc = (TextView) findViewById(R.id.totalDpc);
        totalDpc.setText("Łączny zysk z obstawy: " + MainActivity.totalDpc + "$");

        totalBonus = (TextView) findViewById(R.id.totalBonus);

        if (MainActivity.patrimonyBonus > 0) {
        totalBonus.setText("Premia z ojcowizny: " + MainActivity.patrimonyBonus + "$/sek");
        }

        totalReset = (TextView) findViewById(R.id.totalReset);

        if (resetMultiplier >= 2) {
            totalReset.setText("Premia z resetu: " + resetMultiplier + "x");
        }

        Button resetBtn = (Button) findViewById(R.id.resetBtn);

        if (ShopDecorations.shopDecBought == 1 && ShopDecorations.shopDec2Bought == 1 && ShopDecorations.shopDec3Bought == 1 && ShopDecorations.shopDec4Bought == 1) {
            resetBtn.setEnabled(true);
            resetBtn.setAlpha(1);
        } else {
            resetBtn.setEnabled(false);
            resetBtn.setAlpha(.5f);
        }

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Statistics.this, Reset.class));
                finish();
            }
        });

        loadPref();

    }

    public void loadPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        long resetMultiplierKey = sharedPref.getLong(resetMultiplierKeyString, 1);
        resetMultiplier = resetMultiplierKey;
    }

    public void onDestroy(){
        super.onDestroy();
    }
}
