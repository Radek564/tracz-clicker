package com.clicker.sd.myclicker;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.DisplayMetrics;
import android.view.View;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
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

    //Views
    public TextView shopDecBtnText;

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

        if (shopDecBought == 1) {
            shopDecBtn.setAlpha(.5f);
            shopDecBtn.setEnabled(false);
            shopDecBtnText.setText("Wykupiono!");
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

}
