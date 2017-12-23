package com.clicker.sd.myclicker;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.preference.PreferenceManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ShopDecorations extends Activity {

    //Values

    public long shopDec;
    public static long shopDecBought;
    public String shopDecKeyString = "shopDec";
    public static String shopDecBoughtKeyString = "shopDecBought";

    public long shopDec2;
    public static long shopDec2Bought;
    public String shopDec2KeyString = "shopDec2";
    public static String shopDec2BoughtKeyString = "shopDec2Bought";

    public long shopDec3;
    public static long shopDec3Bought;
    public String shopDec3KeyString = "shopDec3";
    public static String shopDec3BoughtKeyString = "shopDec3Bought";

    public long shopDec4;
    public static long shopDec4Bought;
    public String shopDec4KeyString = "shopDec4";
    public static String shopDec4BoughtKeyString = "shopDec4Bought";

    private CustomListAdapter3 adapter;

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

        ArrayList image_details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.decorationsList);

        lv1.setAdapter(adapter = new CustomListAdapter3(this, image_details));

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                DecorationsItem decorationsData = (DecorationsItem) o;
            }


        });

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                DecorationsItem decorationsData = (DecorationsItem) o;
                lv1.isEnabled();
                if (id == 0) {
                    if(MainActivity.dot >= shopDec){
                        MainActivity.dot -= shopDec;

                        shopDecBought = 1;
                        MainActivity.tesc.setVisibility(View.VISIBLE);
                        decorationsData.setDescription("Wykupiono!");
                        adapter.notifyDataSetChanged();

                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDecBoughtKeyString, shopDecBought);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 1) {
                    if(MainActivity.dot >= shopDec2){
                        MainActivity.dot -= shopDec2;

                        shopDec2Bought = 1;
                        MainActivity.panwalencik.setVisibility(View.VISIBLE);
                        decorationsData.setDescription("Wykupiono!");
                        adapter.notifyDataSetChanged();

                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDec2BoughtKeyString, shopDec2Bought);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 2) {
                    if(MainActivity.dot >= shopDec3){
                        MainActivity.dot -= shopDec3;

                        shopDec3Bought = 1;
                        MainActivity.wikary.setVisibility(View.VISIBLE);
                        decorationsData.setDescription("Wykupiono!");
                        adapter.notifyDataSetChanged();

                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDec3BoughtKeyString, shopDec3Bought);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 3) {
                    if(MainActivity.dot >= shopDec4){
                        MainActivity.dot -= shopDec4;

                        shopDec4Bought = 1;
                        MainActivity.czapka.setVisibility(View.VISIBLE);
                        lv1.getChildAt(3).setEnabled(false);
                        decorationsData.setDescription("Wykupiono!");
                        adapter.notifyDataSetChanged();

                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDec4BoughtKeyString, shopDec4Bought);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public ArrayList getListData() {
        ArrayList<DecorationsItem> results = new ArrayList<DecorationsItem>();
        DecorationsItem decorationsData = new DecorationsItem();
        decorationsData.setName("Roślinka");
        decorationsData.setDescription("10000$");
        results.add(decorationsData);

        decorationsData = new DecorationsItem();
        decorationsData.setName("Pan Walencik");
        decorationsData.setDescription("50000$");
        results.add(decorationsData);

        decorationsData = new DecorationsItem();
        decorationsData.setName("Wikary");
        decorationsData.setDescription("100000$");
        results.add(decorationsData);

        decorationsData = new DecorationsItem();
        decorationsData.setName("Czapka świąteczna");
        decorationsData.setDescription("500000$");
        results.add(decorationsData);

        return results;
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

}
