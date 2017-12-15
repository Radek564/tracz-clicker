package com.clicker.sd.myclicker;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.view.View;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.Intent;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Values
    public static long dot;
        public static String dotKeyString = "dots";

    public static long dpc;
        public static String dpcKeyString = "dpc";

    public static long dps;
        public static String dpsKeyString = "dps";

    //Views
    public static TextView dotsView;
    public static TextView dpsAndDpcView;
    public static TextView cytat1;

    Random rnd = new Random();

    public ImageButton dotBtn;


    private String[] cytaty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, Music.class));

        cytaty = getResources().getStringArray(R.array.cytaty);
        final Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.move);
        final Animation anim3 = AnimationUtils.loadAnimation(this, R.anim.move2);
        final View view2 = findViewById(R.id.cytat1);
        view2.startAnimation(anim2);

        anim2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                int randomIndex = new Random().nextInt(cytaty.length);
                String randomName = cytaty[randomIndex];
                cytat1.setText(randomName);
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                cytat1.setTextColor(color);
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                int randomIndex = new Random().nextInt(cytaty.length);
                String randomName = cytaty[randomIndex];
                cytat1.setText(randomName);
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                cytat1.setTextColor(color);
                view2.startAnimation(anim3);
            }
        });

        anim3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                int randomIndex = new Random().nextInt(cytaty.length);
                String randomName = cytaty[randomIndex];
                cytat1.setText(randomName);
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                cytat1.setTextColor(color);
                startService(new Intent(getApplicationContext(), Sounds.class));
                view2.startAnimation(anim2);
            }
        });

        loadPref();
        initialize();

        Thread thread = new Thread() {
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dot += dps;
                                dotsView.setText(dot + "$");

                                savePref(dotKeyString, dot);
                            }
                        });
                    }
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void loadPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        long dotKey = sharedPref.getLong(dotKeyString, 0);
            dot = dotKey;

        long dpcKey = sharedPref.getLong(dpcKeyString, 1);
            dpc = dpcKey;

        long dpsKey = sharedPref.getLong(dpsKeyString, 0);
            dps = dpsKey;

    }

    public void savePref(String key, long value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void initialize() {
        //Views
        dotsView = (TextView) findViewById(R.id.dotsView);
            dotsView.setText(dot + "$");

        dpsAndDpcView = (TextView) findViewById(R.id.dpsAndDpcView);
            dpsAndDpcView.setText(dps +"$/sek" + System.getProperty ("line.separator") + dpc + "$/klik");

        cytat1 = (TextView) findViewById(R.id.cytat1);

        Button shopBtn = (Button) findViewById(R.id.shopBtn);

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShopClick.class));
            }
        });

        Button shopBtn2 = (Button) findViewById(R.id.shopBtn2);

        shopBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShopSecond.class));
            }
        });

        Button shopBtn3 = (Button) findViewById(R.id.shopBtn3);

        shopBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Strona w budowie!", Toast.LENGTH_SHORT).show();
            }
        });

        dotBtn = (ImageButton) findViewById(R.id.dotBtn);

    }

    public void dotBtn(View v){
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        final View view = findViewById(R.id.dotBtn);
        view.startAnimation(anim);
        dot += dpc;
        dotsView.setText(dot + "$");

        savePref(dotKeyString, dot);
    }

}
