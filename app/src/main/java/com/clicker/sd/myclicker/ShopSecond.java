package com.clicker.sd.myclicker;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.DisplayMetrics;
import android.view.View;
import android.preference.PreferenceManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ShopSecond extends Activity {

    //Values

    public static long shopDps;
    public static String shopDpsKeyString = "shopDps";
    public static long shopDps2;
    public static String shopDps2KeyString = "shopDps2";
    public static long shopDps3;
    public static String shopDps3KeyString = "shopDps3";
    public static long shopDps4;
    public static String shopDps4KeyString = "shopDps4";
    public static long patrimonyBought;
    public static String patrimonyBoughtKeyString = "patrimonyBought";

    private CustomListAdapter adapter;

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

        ArrayList image_details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.holdingsList);
        lv1.setAdapter(adapter = new CustomListAdapter(this, image_details));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                HoldingsItem holdingsData = (HoldingsItem) o;
            }
        });

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                HoldingsItem holdingsData = (HoldingsItem) o;
                if (id == 0) {
                    if(MainActivity.dot >= shopDps){
                        MainActivity.dot -= shopDps;
                        MainActivity.dps += (10 * Statistics.resetMultiplier);

                        shopDps *= 1.5;

                        holdingsData.setDescription(shopDps+"$ | +" + (10 * Statistics.resetMultiplier) + "$ na sekundę");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDpsKeyString, shopDps);
                        savePref(MainActivity.dpsKeyString, MainActivity.dps);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 1) {
                    if(MainActivity.dot >= shopDps2){
                        MainActivity.dot -= shopDps2;
                        MainActivity.dps += (50 * Statistics.resetMultiplier);
                        patrimonyBought += 1;

                        shopDps2 *= 1.5;

                        holdingsData.setDescription(shopDps2+"$ | +" + (50 * Statistics.resetMultiplier) + "$ na sekundę");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDps2KeyString, shopDps2);
                        savePref(patrimonyBoughtKeyString, patrimonyBought);
                        savePref(MainActivity.dpsKeyString, MainActivity.dps);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 2) {
                    if(MainActivity.dot >= shopDps3){
                        MainActivity.dot -= shopDps3;
                        MainActivity.dps += (100 * Statistics.resetMultiplier);

                        shopDps3 *= 1.5;

                        holdingsData.setDescription(shopDps3+"$ | +" + (100 * Statistics.resetMultiplier) + "$ na sekundę");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDps3KeyString, shopDps3);
                        savePref(MainActivity.dpsKeyString, MainActivity.dps);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 3) {
                    if(MainActivity.dot >= shopDps4){
                        MainActivity.dot -= shopDps4;
                        MainActivity.dps += (150 * Statistics.resetMultiplier);

                        shopDps4 *= 1.5;

                        holdingsData.setDescription(shopDps4+"$ | +" + (150 * Statistics.resetMultiplier) + "$ na sekundę");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDps4KeyString, shopDps4);
                        savePref(MainActivity.dpsKeyString, MainActivity.dps);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public ArrayList getListData() {
        ArrayList<HoldingsItem> results = new ArrayList<HoldingsItem>();
        HoldingsItem holdingsData = new HoldingsItem();
        holdingsData.setName("Browar");
        holdingsData.setDescription(shopDps+"$ | +" + (10 * Statistics.resetMultiplier) + "$ na sekundę");

        results.add(holdingsData);

        holdingsData = new HoldingsItem();
        holdingsData.setName("Ojcowizna");
        holdingsData.setDescription(shopDps2+"$ | +" + (50 * Statistics.resetMultiplier) + "$ na sekundę");
        results.add(holdingsData);

        holdingsData = new HoldingsItem();
        holdingsData.setName("Pałacyk");
        holdingsData.setDescription(shopDps3+"$ | +" + (100 * Statistics.resetMultiplier) + "$ na sekundę");
        results.add(holdingsData);

        holdingsData = new HoldingsItem();
        holdingsData.setName("Plebania");
        holdingsData.setDescription(shopDps4+"$ | +" + (150 * Statistics.resetMultiplier) + "$ na sekundę");
        results.add(holdingsData);

        return results;
    }

    public void loadPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        long shopDpsKey = sharedPref.getLong(shopDpsKeyString, 500);
        shopDps = shopDpsKey;

        long shopDps2Key = sharedPref.getLong(shopDps2KeyString, 4000);
        shopDps2 = shopDps2Key;

        long patrimonyBoughtKey = sharedPref.getLong(patrimonyBoughtKeyString, 0);
        patrimonyBought = patrimonyBoughtKey;

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

    }

}
