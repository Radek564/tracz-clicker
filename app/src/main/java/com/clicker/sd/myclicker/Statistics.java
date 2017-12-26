package com.clicker.sd.myclicker;

import android.app.Activity;
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
                            totalDots.setText("Łącznie dolarów: " + MainActivity.totalDot + "$");

                            totalDps = (TextView) findViewById(R.id.totalDps);
                            totalDps.setText("Łącznie dolarów z włości: " + MainActivity.totalDps + "$");

                            if (resetMultiplier >= 2) {
                                totalReset = (TextView) findViewById(R.id.totalReset);
                                totalReset.setVisibility(TextView.VISIBLE);
                                totalReset.setText("Premia z resetu: " + resetMultiplier + "x");
                            }
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

        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        totalDots = (TextView) findViewById(R.id.totalDots);
        totalDots.setText("Łącznie dolarów: " + MainActivity.totalDot + "$");

        totalDpc = (TextView) findViewById(R.id.totalDpc);
        totalDpc.setText("Łącznie dolarów z obstawy: " + MainActivity.totalDpc + "$");

        totalDps = (TextView) findViewById(R.id.totalDps);
        totalDps.setText("Łącznie dolarów z włości: " + MainActivity.totalDps + "$");

        totalBonus = (TextView) findViewById(R.id.totalBonus);

        if (ShopDecorations.shopDec2Bought == 1) {
            totalBonus.setVisibility(TextView.VISIBLE);
            totalBonus.setText("Premia z ojcowizny: " + MainActivity.patrimonyBonus + "$/sek");
        }

        Button resetBtn = (Button) findViewById(R.id.resetBtn);

        if (ShopDecorations.shopDecBought == 1 && ShopDecorations.shopDec2Bought == 1 && ShopDecorations.shopDec3Bought == 1 && ShopDecorations.shopDec4Bought == 1) {
            resetBtn.setVisibility(View.VISIBLE);
        } else {
            resetBtn.setVisibility(View.INVISIBLE);
        }

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetMultiplier += 1;
                MainActivity.dot = 0;
                MainActivity.totalDot = 0;
                MainActivity.totalDpc = 0;
                MainActivity.totalDps = 0;
                MainActivity.dpc = (1 * resetMultiplier);
                MainActivity.dps = 0;
                ShopDecorations.shopDecBought = 0;
                ShopDecorations.shopDec2Bought = 0;
                ShopDecorations.shopDec3Bought = 0;
                ShopDecorations.shopDec4Bought = 0;
                ShopSecond.patrimonyBought = 0;
                ShopSecond.shopDps = (500 * resetMultiplier);
                ShopSecond.shopDps2 = (4000 * resetMultiplier);
                ShopSecond.shopDps3 = (10000 * resetMultiplier);
                ShopSecond.shopDps4 = (25000 * resetMultiplier);
                ShopClick.shopDpc = (1000 * resetMultiplier);
                ShopClick.shopDpc2 = (5000 * resetMultiplier);
                ShopClick.shopDpc3 = (15000 * resetMultiplier);
                ShopClick.shopDpc4 = (50000 * resetMultiplier);
                MainActivity.patrimonyBonus = 0;
                MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                savePref(MainActivity.dotKeyString, MainActivity.dot);
                savePref(MainActivity.totalDotKeyString, MainActivity.totalDot);
                savePref(MainActivity.totalDpcKeyString, MainActivity.totalDpc);
                savePref(MainActivity.totalDpsKeyString, MainActivity.totalDps);
                savePref(MainActivity.dpcKeyString, MainActivity.dpc);
                savePref(MainActivity.dpsKeyString, MainActivity.dps);
                savePref(ShopClick.shopDpcKeyString, ShopClick.shopDpc);
                savePref(ShopClick.shopDpc2KeyString, ShopClick.shopDpc2);
                savePref(ShopClick.shopDpc3KeyString, ShopClick.shopDpc3);
                savePref(ShopClick.shopDpc4KeyString, ShopClick.shopDpc4);
                savePref(ShopSecond.shopDpsKeyString, ShopSecond.shopDps);
                savePref(ShopSecond.shopDps2KeyString, ShopSecond.shopDps2);
                savePref(ShopSecond.patrimonyBoughtKeyString, ShopSecond.patrimonyBought);
                savePref(ShopSecond.shopDps3KeyString, ShopSecond.shopDps3);
                savePref(ShopSecond.shopDps4KeyString, ShopSecond.shopDps4);
                savePref(ShopDecorations.shopDecBoughtKeyString, ShopDecorations.shopDecBought);
                savePref(ShopDecorations.shopDec2BoughtKeyString, ShopDecorations.shopDec2Bought);
                savePref(ShopDecorations.shopDec3BoughtKeyString, ShopDecorations.shopDec3Bought);
                savePref(ShopDecorations.shopDec4BoughtKeyString, ShopDecorations.shopDec4Bought);
                savePref(ShopDecorations.shopDec2BoughtKeyString, ShopDecorations.shopDec2Bought);
                savePref(resetMultiplierKeyString, resetMultiplier);
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

    public void savePref(String key, long value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void onDestroy(){
        super.onDestroy();
        thread.interrupt();
    }
}
