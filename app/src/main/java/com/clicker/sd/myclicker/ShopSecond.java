package com.clicker.sd.myclicker;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.DisplayMetrics;
import android.view.View;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class ShopSecond extends Activity {

    //Values

    public long shopDps;
    public String shopDpsKeyString = "shopDps";
    public long shopDps2;
    public String shopDps2KeyString = "shopDps2";
    public long shopDps3;
    public String shopDps3KeyString = "shopDps3";
    public long shopDps4;
    public String shopDps4KeyString = "shopDps4";

    public Button shopDpsBtn;
    public Button shopDps2Btn;
    public Button shopDps3Btn;
    public Button shopDps4Btn;

    public TextView shopDpsBtnText;
    public TextView shopDps2BtnText;
    public TextView shopDps3BtnText;
    public TextView shopDps4BtnText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopsecondwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        loadPref();
        initialize();

    }

    public void loadPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        long shopDpsKey = sharedPref.getLong(shopDpsKeyString, 500);
        shopDps = shopDpsKey;

        long shopDps2Key = sharedPref.getLong(shopDps2KeyString, 4000);
        shopDps2 = shopDps2Key;

        long shopDps3Key = sharedPref.getLong(shopDps3KeyString, 10000);
        shopDps3 = shopDps3Key;

        long shopDps4Key = sharedPref.getLong(shopDps4KeyString, 25000);
        shopDps4 = shopDps4Key;

    }

    public void savePref(String key, long value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void initialize() {
        //Views
        shopDpsBtn = (Button) findViewById(R.id.shopDps);
        shopDpsBtnText = (TextView) findViewById(R.id.shopDpstext);
        shopDpsBtnText.setText(shopDps + "$ | +10$ na sekundę");

        shopDps2Btn = (Button) findViewById(R.id.shopDps2);
        shopDps2BtnText = (TextView) findViewById(R.id.shopDps2text);
        shopDps2BtnText.setText(shopDps2 + "$ | +50$ na sekundę");

        shopDps3Btn = (Button) findViewById(R.id.shopDps3);
        shopDps3BtnText = (TextView) findViewById(R.id.shopDps3text);
        shopDps3BtnText.setText(shopDps3 + "$ | +100$ na sekundę");

        shopDps4Btn = (Button) findViewById(R.id.shopDps4);
        shopDps4BtnText = (TextView) findViewById(R.id.shopDps4text);
        shopDps4BtnText.setText(shopDps4 + "$ | +150$ na sekundę");

    }

    public void shopDps(View v){
        if(MainActivity.dot >= shopDps){
            MainActivity.dot -= shopDps;
            MainActivity.dps += 10;

            shopDps *= 1.5;

            shopDpsBtnText.setText(shopDps + "$ | +10$ na sekundę");
            MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
            MainActivity.dotsView.setText(MainActivity.dot + "$");

            savePref(shopDpsKeyString, shopDps);
            savePref(MainActivity.dpsKeyString, MainActivity.dps);
            savePref(MainActivity.dotKeyString, MainActivity.dot);

        }else{
            Toast.makeText(this, "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
        }
    }

    public void shopDps2(View v){
        if(MainActivity.dot >= shopDps2){
            MainActivity.dot -= shopDps2;
            MainActivity.dps += 50;

            shopDps2 *= 1.5;

            shopDps2BtnText.setText(shopDps2 + "$ | +50$ na sekundę");
            MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
            MainActivity.dotsView.setText(MainActivity.dot + "$");

            savePref(shopDps2KeyString, shopDps2);
            savePref(MainActivity.dpsKeyString, MainActivity.dps);
            savePref(MainActivity.dotKeyString, MainActivity.dot);

        }else{
            Toast.makeText(this, "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
        }
    }

    public void shopDps3(View v){
        if(MainActivity.dot >= shopDps3){
            MainActivity.dot -= shopDps3;
            MainActivity.dps += 100;

            shopDps3 *= 1.5;

            shopDps3BtnText.setText(shopDps3 + "$ | +100$ na sekundę");
            MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
            MainActivity.dotsView.setText(MainActivity.dot + "$");

            savePref(shopDps3KeyString, shopDps3);
            savePref(MainActivity.dpsKeyString, MainActivity.dps);
            savePref(MainActivity.dotKeyString, MainActivity.dot);

        }else{
            Toast.makeText(this, "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
        }
    }

    public void shopDps4(View v){
        if(MainActivity.dot >= shopDps4){
            MainActivity.dot -= shopDps4;
            MainActivity.dps += 150;

            shopDps4 *= 1.5;

            shopDps4BtnText.setText(shopDps4 + "$ | +150$ na sekundę");
            MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
            MainActivity.dotsView.setText(MainActivity.dot + "$");

            savePref(shopDps4KeyString, shopDps4);
            savePref(MainActivity.dpsKeyString, MainActivity.dps);
            savePref(MainActivity.dotKeyString, MainActivity.dot);

        }else{
            Toast.makeText(this, "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
        }
    }

}
