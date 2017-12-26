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

public class ShopClick extends Activity {

    //Values

    public static long shopDpc;
    public static String shopDpcKeyString = "shopDpc";
    public static long shopDpc2;
    public static String shopDpc2KeyString = "shopDpc2";
    public static long shopDpc3;
    public static String shopDpc3KeyString = "shopDpc3";
    public static long shopDpc4;
    public static String shopDpc4KeyString = "shopDpc4";

    private CustomListAdapter2 adapter;

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

        ArrayList image_details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.guardsList);
        lv1.setAdapter(adapter = new CustomListAdapter2(this, image_details));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                GuardsItem guardsData = (GuardsItem) o;
            }
        });

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                GuardsItem guardsData = (GuardsItem) o;
                if (id == 0) {
                    if(MainActivity.dot >= shopDpc){
                        MainActivity.dot -= shopDpc;
                        MainActivity.dpc += (10 * Statistics.resetMultiplier);

                        shopDpc *= 1.5;

                        guardsData.setDescription(shopDpc+"$ | +" + (10 * Statistics.resetMultiplier) + "$ za klik");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDpcKeyString, shopDpc);
                        savePref(MainActivity.dpcKeyString, MainActivity.dpc);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 1) {
                    if(MainActivity.dot >= shopDpc2){
                        MainActivity.dot -= shopDpc2;
                        MainActivity.dpc += (20 * Statistics.resetMultiplier);

                        shopDpc2 *= 1.5;

                        guardsData.setDescription(shopDpc2+"$ | +" + (20 * Statistics.resetMultiplier) + "$ za klik");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDpc2KeyString, shopDpc2);
                        savePref(MainActivity.dpcKeyString, MainActivity.dpc);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 2) {
                    if(MainActivity.dot >= shopDpc3){
                        MainActivity.dot -= shopDpc3;
                        MainActivity.dpc += (30 * Statistics.resetMultiplier);

                        shopDpc3 *= 1.5;

                        guardsData.setDescription(shopDpc3+"$ | +" + (30 * Statistics.resetMultiplier) + "$ za klik");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDpc3KeyString, shopDpc3);
                        savePref(MainActivity.dpcKeyString, MainActivity.dpc);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 3) {
                    if(MainActivity.dot >= shopDpc4){
                        MainActivity.dot -= shopDpc4;
                        MainActivity.dpc += (40 * Statistics.resetMultiplier);

                        shopDpc4 *= 1.5;

                        guardsData.setDescription(shopDpc4+"$ | +" + (40 * Statistics.resetMultiplier) + "$ za klik");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDpc4KeyString, shopDpc4);
                        savePref(MainActivity.dpcKeyString, MainActivity.dpc);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public ArrayList getListData() {
        ArrayList<GuardsItem> results = new ArrayList<GuardsItem>();
        GuardsItem guardsData = new GuardsItem();
        guardsData.setName("Osa");
        guardsData.setDescription(shopDpc+"$ | +" + (10 * Statistics.resetMultiplier) + "$ za klik");
        results.add(guardsData);

        guardsData = new GuardsItem();
        guardsData.setName("Rumcajs");
        guardsData.setDescription(shopDpc2+"$ | +" + (20 * Statistics.resetMultiplier) + "$ za klik");
        results.add(guardsData);

        guardsData = new GuardsItem();
        guardsData.setName("Włoska mafia");
        guardsData.setDescription(shopDpc3+"$ | +" + (30 * Statistics.resetMultiplier) + "$ za klik");
        results.add(guardsData);

        guardsData = new GuardsItem();
        guardsData.setName("Ukraińscy gangsterzy");
        guardsData.setDescription(shopDpc4+"$ | +" + (40 * Statistics.resetMultiplier) + "$ za klik");
        results.add(guardsData);

        return results;
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
    }

}
