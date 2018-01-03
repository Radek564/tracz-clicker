package com.sd.traczclicker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Statistics extends Activity {

    public static long resetMultiplier;
    public static String resetMultiplierKeyString = "resetMultiplier";

    public TextView totalDots;
    public TextView totalDpc;
    public TextView totalDps;
    public TextView totalBonus;
    public TextView totalReset;

    Thread thread = new Thread() {
        public void run() {
            try {
                while (!isInterrupted()) {
                    Thread.sleep(100);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            totalDots = (TextView) findViewById(R.id.totalDots);
                            totalDots.setText("Łączny zysk: " + MainActivity.totalDot + "$");

                            totalDps = (TextView) findViewById(R.id.totalDps);
                            totalDps.setText("Łączny zysk z włości: " + MainActivity.totalDps + "$");
                        }
                    });
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.35));

        totalDots = (TextView) findViewById(R.id.totalDots);
        totalDots.setText("Łączny zysk: " + MainActivity.totalDot + "$");

        totalDpc = (TextView) findViewById(R.id.totalDpc);
        totalDpc.setText("Łączny zysk z obstawy: " + MainActivity.totalDpc + "$");

        totalDps = (TextView) findViewById(R.id.totalDps);
        totalDps.setText("Łączny zysk z włości: " + MainActivity.totalDps + "$");

        totalBonus = (TextView) findViewById(R.id.totalBonus);

        if (ShopDecorations.shopDec2Bought == 1) {
            totalBonus.setVisibility(TextView.VISIBLE);
            totalBonus.setText("Premia z ojcowizny: " + MainActivity.patrimonyBonus + "$/sek");
        }

        totalReset = (TextView) findViewById(R.id.totalReset);

        if (resetMultiplier >= 2) {
            totalReset.setVisibility(TextView.VISIBLE);
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

        thread.start();

    }

    public void loadPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        long resetMultiplierKey = sharedPref.getLong(resetMultiplierKeyString, 1);
        resetMultiplier = resetMultiplierKey;
    }

    public void onDestroy(){
        super.onDestroy();
        thread.interrupt();
    }
}
