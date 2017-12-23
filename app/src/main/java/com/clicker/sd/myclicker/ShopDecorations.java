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

public class ShopDecorations extends Activity {

    //Values

    public long shopDec;
    public static long shopDecBought;
    public String shopDecKeyString = "shopDec";
    public static String shopDecBoughtKeyString = "shopDecBought";
    public Button shopDecBtn;

    public long shopDec2;
    public static long shopDec2Bought;
    public String shopDec2KeyString = "shopDec2";
    public static String shopDec2BoughtKeyString = "shopDec2Bought";
    public Button shopDec2Btn;

    public long shopDec3;
    public static long shopDec3Bought;
    public String shopDec3KeyString = "shopDec3";
    public static String shopDec3BoughtKeyString = "shopDec3Bought";
    public Button shopDec3Btn;

    public long shopDec4;
    public static long shopDec4Bought;
    public String shopDec4KeyString = "shopDec4";
    public static String shopDec4BoughtKeyString = "shopDec4Bought";
    public Button shopDec4Btn;

    //Views
    public TextView shopDecBtnText;
    public TextView shopDec2BtnText;
    public TextView shopDec3BtnText;
    public TextView shopDec4BtnText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopdecorationswindow);

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

        long shopDecKey = sharedPref.getLong(shopDecKeyString, 10000);
        shopDec = shopDecKey;
        long shopDecBoughtKey = sharedPref.getLong(shopDecBoughtKeyString, 0);
        shopDecBought = shopDecBoughtKey;

        long shopDec2Key = sharedPref.getLong(shopDec2KeyString, 50000);
        shopDec2 = shopDec2Key;
        long shopDec2BoughtKey = sharedPref.getLong(shopDec2BoughtKeyString, 0);
        shopDec2Bought = shopDec2BoughtKey;

        long shopDec3Key = sharedPref.getLong(shopDec3KeyString, 100000);
        shopDec3 = shopDec3Key;
        long shopDec3BoughtKey = sharedPref.getLong(shopDec3BoughtKeyString, 0);
        shopDec3Bought = shopDec3BoughtKey;

        long shopDec4Key = sharedPref.getLong(shopDec4KeyString, 500000);
        shopDec4 = shopDec4Key;
        long shopDec4BoughtKey = sharedPref.getLong(shopDec4BoughtKeyString, 0);
        shopDec4Bought = shopDec4BoughtKey;
    }

    public void savePref(String key, long value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void initialize() {
        //Views
        shopDecBtn = (Button) findViewById(R.id.shopDec);
        shopDecBtnText = (TextView) findViewById(R.id.shopDectext);

        shopDec2Btn = (Button) findViewById(R.id.shopDec2);
        shopDec2BtnText = (TextView) findViewById(R.id.shopDec2text);

        shopDec3Btn = (Button) findViewById(R.id.shopDec3);
        shopDec3BtnText = (TextView) findViewById(R.id.shopDec3text);

        shopDec4Btn = (Button) findViewById(R.id.shopDec4);
        shopDec4BtnText = (TextView) findViewById(R.id.shopDec4text);

        if (shopDecBought == 1) {
            shopDecBtn.setAlpha(.5f);
            shopDecBtnText.setAlpha(.5f);
            shopDecBtn.setEnabled(false);
            shopDecBtnText.setText("Wykupiono!");
        }

        if (shopDec2Bought == 1) {
            shopDec2Btn.setAlpha(.5f);
            shopDec2BtnText.setAlpha(.5f);
            shopDec2Btn.setEnabled(false);
            shopDec2BtnText.setText("Wykupiono!");
        }

        if (shopDec3Bought == 1) {
            shopDec3Btn.setAlpha(.5f);
            shopDec3BtnText.setAlpha(.5f);
            shopDec3Btn.setEnabled(false);
            shopDec3BtnText.setText("Wykupiono!");
        }

        if (shopDec4Bought == 1) {
            shopDec4Btn.setAlpha(.5f);
            shopDec4BtnText.setAlpha(.5f);
            shopDec4Btn.setEnabled(false);
            shopDec4BtnText.setText("Wykupiono!");
        }
    }

    public void shopDec(View v){
        if(MainActivity.dot >= shopDec){
            MainActivity.dot -= shopDec;

            shopDecBought = 1;
            MainActivity.tesc.setVisibility(View.VISIBLE);
            shopDecBtn.setAlpha(.5f);
            shopDecBtn.setEnabled(false);
            shopDecBtnText.setText("Wykupiono!");

            MainActivity.dotsView.setText(MainActivity.dot + "$");

            savePref(shopDecBoughtKeyString, shopDecBought);
            savePref(MainActivity.dotKeyString, MainActivity.dot);

        }else{
            Toast.makeText(this, "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
        }
    }

    public void shopDec2(View v){
        if(MainActivity.dot >= shopDec2){
            MainActivity.dot -= shopDec2;

            shopDec2Bought = 1;
            MainActivity.panwalencik.setVisibility(View.VISIBLE);
            shopDec2Btn.setAlpha(.5f);
            shopDec2BtnText.setAlpha(.5f);
            shopDec2Btn.setEnabled(false);
            shopDec2BtnText.setText("Wykupiono!");

            MainActivity.dotsView.setText(MainActivity.dot + "$");

            savePref(shopDec2BoughtKeyString, shopDec2Bought);
            savePref(MainActivity.dotKeyString, MainActivity.dot);

        }else{
            Toast.makeText(this, "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
        }
    }

    public void shopDec3(View v){
        if(MainActivity.dot >= shopDec3){
            MainActivity.dot -= shopDec3;

            shopDec3Bought = 1;
            MainActivity.wikary.setVisibility(View.VISIBLE);
            shopDec3Btn.setAlpha(.5f);
            shopDec3BtnText.setAlpha(.5f);
            shopDec3Btn.setEnabled(false);
            shopDec3BtnText.setText("Wykupiono!");

            MainActivity.dotsView.setText(MainActivity.dot + "$");

            savePref(shopDec3BoughtKeyString, shopDec3Bought);
            savePref(MainActivity.dotKeyString, MainActivity.dot);

        }else{
            Toast.makeText(this, "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
        }
    }

    public void shopDec4(View v){
        if(MainActivity.dot >= shopDec4){
            MainActivity.dot -= shopDec4;

            shopDec4Bought = 1;
            MainActivity.czapka.setVisibility(View.VISIBLE);
            shopDec4Btn.setAlpha(.5f);
            shopDec4BtnText.setAlpha(.5f);
            shopDec4Btn.setEnabled(false);
            shopDec4BtnText.setText("Wykupiono!");

            MainActivity.dotsView.setText(MainActivity.dot + "$");

            savePref(shopDec4BoughtKeyString, shopDec4Bought);
            savePref(MainActivity.dotKeyString, MainActivity.dot);

        }else{
            Toast.makeText(this, "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
        }
    }

}
