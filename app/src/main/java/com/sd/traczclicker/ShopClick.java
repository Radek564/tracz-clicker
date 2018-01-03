package com.sd.traczclicker;

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
    public static long shopDpc5;
    public static String shopDpc5KeyString = "shopDpc5";
    public static long shopDpc6;
    public static String shopDpc6KeyString = "shopDpc6";
    public static long shopDpc7;
    public static String shopDpc7KeyString = "shopDpc7";
    public static long shopDpc8;
    public static String shopDpc8KeyString = "shopDpc8";
    public static long shopDpc9;
    public static String shopDpc9KeyString = "shopDpc9";
    public static long shopDpc10;
    public static String shopDpc10KeyString = "shopDpc10";

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

                if (id == 4) {
                    if(MainActivity.dot >= shopDpc5){
                        MainActivity.dot -= shopDpc5;
                        MainActivity.dpc += (50 * Statistics.resetMultiplier);

                        shopDpc5 *= 1.5;

                        guardsData.setDescription(shopDpc5+"$ | +" + (50 * Statistics.resetMultiplier) + "$ za klik");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDpc5KeyString, shopDpc5);
                        savePref(MainActivity.dpcKeyString, MainActivity.dpc);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 5) {
                    if(MainActivity.dot >= shopDpc6){
                        MainActivity.dot -= shopDpc6;
                        MainActivity.dpc += (60 * Statistics.resetMultiplier);

                        shopDpc6 *= 1.5;

                        guardsData.setDescription(shopDpc6+"$ | +" + (60 * Statistics.resetMultiplier) + "$ za klik");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDpc6KeyString, shopDpc6);
                        savePref(MainActivity.dpcKeyString, MainActivity.dpc);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 6) {
                    if(MainActivity.dot >= shopDpc7){
                        MainActivity.dot -= shopDpc7;
                        MainActivity.dpc += (70 * Statistics.resetMultiplier);

                        shopDpc7 *= 1.5;

                        guardsData.setDescription(shopDpc7+"$ | +" + (70 * Statistics.resetMultiplier) + "$ za klik");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDpc7KeyString, shopDpc7);
                        savePref(MainActivity.dpcKeyString, MainActivity.dpc);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 7) {
                    if(MainActivity.dot >= shopDpc8){
                        MainActivity.dot -= shopDpc8;
                        MainActivity.dpc += (80 * Statistics.resetMultiplier);

                        shopDpc8 *= 1.5;

                        guardsData.setDescription(shopDpc8+"$ | +" + (80 * Statistics.resetMultiplier) + "$ za klik");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDpc8KeyString, shopDpc8);
                        savePref(MainActivity.dpcKeyString, MainActivity.dpc);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 8) {
                    if(MainActivity.dot >= shopDpc9){
                        MainActivity.dot -= shopDpc9;
                        MainActivity.dpc += (90 * Statistics.resetMultiplier);

                        shopDpc9 *= 1.5;

                        guardsData.setDescription(shopDpc9+"$ | +" + (90 * Statistics.resetMultiplier) + "$ za klik");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDpc9KeyString, shopDpc9);
                        savePref(MainActivity.dpcKeyString, MainActivity.dpc);
                        savePref(MainActivity.dotKeyString, MainActivity.dot);

                    }else{
                        Toast.makeText(getApplicationContext(), "Potrzebujesz więcej dolarów!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == 9) {
                    if(MainActivity.dot >= shopDpc10){
                        MainActivity.dot -= shopDpc10;
                        MainActivity.dpc += (100 * Statistics.resetMultiplier);

                        shopDpc10 *= 1.5;

                        guardsData.setDescription(shopDpc10+"$ | +" + (100 * Statistics.resetMultiplier) + "$ za klik");
                        adapter.notifyDataSetChanged();
                        MainActivity.dpsAndDpcView.setText(MainActivity.dps +"$/sek" + System.getProperty ("line.separator") + MainActivity.dpc + "$/klik");
                        MainActivity.dotsView.setText(MainActivity.dot + "$");

                        savePref(shopDpc10KeyString, shopDpc10);
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
        guardsData.setName("Pani Marylka");
        guardsData.setDescription(shopDpc+"$ | +" + (10 * Statistics.resetMultiplier) + "$ za klik");
        results.add(guardsData);

        guardsData = new GuardsItem();
        guardsData.setName("Pani Joanka");
        guardsData.setDescription(shopDpc2+"$ | +" + (20 * Statistics.resetMultiplier) + "$ za klik");
        results.add(guardsData);

        guardsData = new GuardsItem();
        guardsData.setName("Pani Weronika");
        guardsData.setDescription(shopDpc3+"$ | +" + (30 * Statistics.resetMultiplier) + "$ za klik");
        results.add(guardsData);

        guardsData = new GuardsItem();
        guardsData.setName("Gosposia Mai");
        guardsData.setDescription(shopDpc4+"$ | +" + (40 * Statistics.resetMultiplier) + "$ za klik");
        results.add(guardsData);

        guardsData = new GuardsItem();
        guardsData.setName("Niewolnica Milada");
        guardsData.setDescription(shopDpc5+"$ | +" + (50 * Statistics.resetMultiplier) + "$ za klik");
        results.add(guardsData);

        guardsData = new GuardsItem();
        guardsData.setName("Osa");
        guardsData.setDescription(shopDpc6+"$ | +" + (60 * Statistics.resetMultiplier) + "$ za klik");
        results.add(guardsData);

        guardsData = new GuardsItem();
        guardsData.setName("Rumcajs");
        guardsData.setDescription(shopDpc7+"$ | +" + (70 * Statistics.resetMultiplier) + "$ za klik");
        results.add(guardsData);

        guardsData = new GuardsItem();
        guardsData.setName("Marysia");
        guardsData.setDescription(shopDpc8+"$ | +" + (80 * Statistics.resetMultiplier) + "$ za klik");
        results.add(guardsData);

        guardsData = new GuardsItem();
        guardsData.setName("Włoska mafia");
        guardsData.setDescription(shopDpc9+"$ | +" + (90 * Statistics.resetMultiplier) + "$ za klik");
        results.add(guardsData);

        guardsData = new GuardsItem();
        guardsData.setName("Ukraińscy gangsterzy");
        guardsData.setDescription(shopDpc10+"$ | +" + (100 * Statistics.resetMultiplier) + "$ za klik");
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

        long shopDpc5Key = sharedPref.getLong(shopDpc5KeyString, 80000);
        shopDpc5 = shopDpc5Key;

        long shopDpc6Key = sharedPref.getLong(shopDpc6KeyString, 100000);
        shopDpc6 = shopDpc6Key;

        long shopDpc7Key = sharedPref.getLong(shopDpc7KeyString, 150000);
        shopDpc7 = shopDpc7Key;

        long shopDpc8Key = sharedPref.getLong(shopDpc8KeyString, 200000);
        shopDpc8 = shopDpc8Key;

        long shopDpc9Key = sharedPref.getLong(shopDpc9KeyString, 260000);
        shopDpc9 = shopDpc9Key;

        long shopDpc10Key = sharedPref.getLong(shopDpc9KeyString, 300000);
        shopDpc10 = shopDpc10Key;

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
