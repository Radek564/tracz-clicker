package com.sd.traczclicker;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class Reset extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*0.3));

        Button confirmReset = (Button) findViewById(R.id.confirmReset);

        confirmReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Statistics.resetMultiplier += 1;
                MainActivity.dot = 0;
                MainActivity.totalDot = 0;
                MainActivity.totalDpc = 0;
                MainActivity.totalDps = 0;
                MainActivity.dpc = (1 * Statistics.resetMultiplier);
                MainActivity.dps = 0;
                ShopDecorations.shopDecBought = 0;
                ShopDecorations.shopDec2Bought = 0;
                ShopDecorations.shopDec3Bought = 0;
                ShopDecorations.shopDec4Bought = 0;
                ShopSecond.patrimonyBought = 0;
                ShopSecond.shopDps = 500;
                ShopSecond.shopDps2 = 4000;
                ShopSecond.shopDps3 = 10000;
                ShopSecond.shopDps4 = 25000;
                ShopSecond.shopDps5 = 60000;
                ShopSecond.shopDps6 = 100000;
                ShopSecond.shopDps7 = 150000;
                ShopClick.shopDpc = 1000;
                ShopClick.shopDpc2 = 5000;
                ShopClick.shopDpc3 = 15000;
                ShopClick.shopDpc4 = 50000;
                ShopClick.shopDpc5 = 80000;
                ShopClick.shopDpc6 = 100000;
                ShopClick.shopDpc7 = 150000;
                ShopClick.shopDpc8 = 200000;
                ShopClick.shopDpc9 = 260000;
                ShopClick.shopDpc10 = 300000;
                ShopDecorations.shopDec = (10000 * Statistics.resetMultiplier);
                ShopDecorations.shopDec2 = (50000 * Statistics.resetMultiplier);
                ShopDecorations.shopDec3 = (100000 * Statistics.resetMultiplier);
                ShopDecorations.shopDec4 = (500000 * Statistics.resetMultiplier);
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
                savePref(ShopClick.shopDpc5KeyString, ShopClick.shopDpc5);
                savePref(ShopClick.shopDpc6KeyString, ShopClick.shopDpc6);
                savePref(ShopClick.shopDpc7KeyString, ShopClick.shopDpc7);
                savePref(ShopClick.shopDpc8KeyString, ShopClick.shopDpc8);
                savePref(ShopClick.shopDpc9KeyString, ShopClick.shopDpc9);
                savePref(ShopClick.shopDpc10KeyString, ShopClick.shopDpc10);
                savePref(ShopSecond.shopDpsKeyString, ShopSecond.shopDps);
                savePref(ShopSecond.shopDps2KeyString, ShopSecond.shopDps2);
                savePref(ShopSecond.patrimonyBoughtKeyString, ShopSecond.patrimonyBought);
                savePref(ShopSecond.shopDps3KeyString, ShopSecond.shopDps3);
                savePref(ShopSecond.shopDps4KeyString, ShopSecond.shopDps4);
                savePref(ShopSecond.shopDps5KeyString, ShopSecond.shopDps5);
                savePref(ShopSecond.shopDps6KeyString, ShopSecond.shopDps6);
                savePref(ShopSecond.shopDps7KeyString, ShopSecond.shopDps7);
                savePref(ShopDecorations.shopDecBoughtKeyString, ShopDecorations.shopDecBought);
                savePref(ShopDecorations.shopDec2BoughtKeyString, ShopDecorations.shopDec2Bought);
                savePref(ShopDecorations.shopDec3BoughtKeyString, ShopDecorations.shopDec3Bought);
                savePref(ShopDecorations.shopDec4BoughtKeyString, ShopDecorations.shopDec4Bought);
                savePref(ShopDecorations.shopDec2BoughtKeyString, ShopDecorations.shopDec2Bought);
                savePref(ShopDecorations.shopDecKeyString, ShopDecorations.shopDec);
                savePref(ShopDecorations.shopDec2KeyString, ShopDecorations.shopDec2);
                savePref(ShopDecorations.shopDec3KeyString, ShopDecorations.shopDec3);
                savePref(ShopDecorations.shopDec4KeyString, ShopDecorations.shopDec4);
                savePref(Statistics.resetMultiplierKeyString, Statistics.resetMultiplier);
                finish();
            }
        });

        loadPref();

    }

    public void loadPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        long resetMultiplierKey = sharedPref.getLong(Statistics.resetMultiplierKeyString, 1);
        Statistics.resetMultiplier = resetMultiplierKey;
    }

    public void savePref(String key, long value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.apply();
    }
}
