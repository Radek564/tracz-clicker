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

public class ShopClick extends Activity {

    //Values

    public long shopDpc;
    public String shopDpcKeyString = "shopDpc";
    public long shopDpc2;
    public String shopDpc2KeyString = "shopDpc2";
    public long shopDpc3;
    public String shopDpc3KeyString = "shopDpc3";
    public long shopDpc4;
    public String shopDpc4KeyString = "shopDpc4";

    public Button shopDpcBtn;
    public Button shopDpc2Btn;
    public Button shopDpc3Btn;
    public Button shopDpc4Btn;

    public TextView shopDpcBtnText;
    public TextView shopDpc2BtnText;
    public TextView shopDpc3BtnText;
    public TextView shopDpc4BtnText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopclickwindow);

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

        long shopDpcKey = sharedPref.getLong(shopDpcKeyString, 1000);
        shopDpc = shopDpcKey;

        long shopDpc2Key = sharedPref.getLong(shopDpc2KeyString, 5000);
        shopDpc2 = shopDpc2Key;

        long shopDpc3Key = sharedPref.getLong(shopDpc3KeyString, 15000);
        shopDpc3 = shopDpc3Key;

        long shopDpc4Key = sharedPref.getLong(shopDpc4KeyString, 50000);
        shopDpc4 = shopDpc4Key;

    }

    public void savePref(String key, long value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void initialize() {
        //Views
        shopDpcBtn = (Button) findViewById(R.id.shopDpc);
        shopDpcBtnText = (TextView) findViewById(R.id.shopDpctext);
        shopDpcBtnText.setText(shopDpc + "$ | +10$ za klik");

        shopDpc2Btn = (Button) findViewById(R.id.shopDpc2);
        shopDpc2BtnText = (TextView) findViewById(R.id.shopDpc2text);
        shopDpc2BtnText.setText(shopDpc2 + "$ | +20$ za klik");

        shopDpc3Btn = (Button) findViewById(R.id.shopDpc3);
        shopDpc3BtnText = (TextView) findViewById(R.id.shopDpc3text);
        shopDpc3BtnText.setText(shopDpc3 + "$ | +30$ za klik");

        shopDpc4Btn = (Button) findViewById(R.id.shopDpc4);
        shopDpc4BtnText = (TextView) findViewById(R.id.shopDpc4text);
        shopDpc4BtnText.setText(shopDpc4 + "$ | +40$ za klik");
    }

    public void shopDpc(View v){
        if(MainActivity.dot >= shopDpc){
            MainActivity.dot -= shopDpc;
            MainActivity.dpc += 10;

            shopDpc *= 1.5;

            shopDpcBtnText.setText(shopDpc + "$ | +10$ za klik");
            MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
            MainActivity.dotsView.setText(MainActivity.dot + "$");

            savePref(shopDpcKeyString, shopDpc);
            savePref(MainActivity.dpcKeyString, MainActivity.dpc);
            savePref(MainActivity.dotKeyString, MainActivity.dot);

        }else{
            Toast.makeText(this, "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
        }
    }

    public void shopDpc2(View v){
        if(MainActivity.dot >= shopDpc2){
            MainActivity.dot -= shopDpc2;
            MainActivity.dpc += 20;

            shopDpc2 *= 1.5;

            shopDpc2BtnText.setText(shopDpc2 + "$ | +20$ za klik");
            MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
            MainActivity.dotsView.setText(MainActivity.dot + "$");

            savePref(shopDpc2KeyString, shopDpc2);
            savePref(MainActivity.dpcKeyString, MainActivity.dpc);
            savePref(MainActivity.dotKeyString, MainActivity.dot);

        }else{
            Toast.makeText(this, "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
        }
    }

    public void shopDpc3(View v){
        if(MainActivity.dot >= shopDpc3){
            MainActivity.dot -= shopDpc3;
            MainActivity.dpc += 30;

            shopDpc3 *= 1.5;

            shopDpc3BtnText.setText(shopDpc3 + "$ | +30$ za klik");
            MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
            MainActivity.dotsView.setText(MainActivity.dot + "$");

            savePref(shopDpc3KeyString, shopDpc3);
            savePref(MainActivity.dpcKeyString, MainActivity.dpc);
            savePref(MainActivity.dotKeyString, MainActivity.dot);

        }else{
            Toast.makeText(this, "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
        }
    }

    public void shopDpc4(View v){
        if(MainActivity.dot >= shopDpc4){
            MainActivity.dot -= shopDpc4;
            MainActivity.dpc += 40;

            shopDpc4 *= 1.5;

            shopDpc4BtnText.setText(shopDpc4 + "$ | +40$ za klik");
            MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
            MainActivity.dotsView.setText(MainActivity.dot + "$");

            savePref(shopDpc4KeyString, shopDpc4);
            savePref(MainActivity.dpcKeyString, MainActivity.dpc);
            savePref(MainActivity.dotKeyString, MainActivity.dot);

        }else{
            Toast.makeText(this, "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
        }
    }

}
