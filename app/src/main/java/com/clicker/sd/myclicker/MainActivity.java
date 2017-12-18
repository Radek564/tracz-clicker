package com.clicker.sd.myclicker;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.view.View;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.Intent;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
    public static TextView quoteText;
    public static ImageView czapka;

    Random rnd = new Random();
    Timer timer = new Timer();

    public ImageButton dotBtn;

    private String[] quotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, Music.class));

        quotes = getResources().getStringArray(R.array.quotes);

        /*
        final Animation animation;
        animation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE,rnd.nextInt(1000),
                TranslateAnimation.ABSOLUTE,rnd.nextInt(1000),
                TranslateAnimation.ABSOLUTE,rnd.nextInt(1000),
                TranslateAnimation.ABSOLUTE,rnd.nextInt(1000));
        animation.setDuration(6000);
        animation.setFillAfter(true);
        */

        final Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.move);
        final Animation anim3 = AnimationUtils.loadAnimation(this, R.anim.move2);
        final Animation anim4 = AnimationUtils.loadAnimation(this, R.anim.move3);
        final View view2 = findViewById(R.id.cytat1);

        view2.startAnimation(anim2);

        anim2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                int randomIndex = new Random().nextInt(quotes.length);
                String randomName = quotes[randomIndex];
                quoteText.setText(randomName);
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                quoteText.setTextColor(color);
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                view2.startAnimation(anim3);
            }
        });

        anim3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                int randomIndex = new Random().nextInt(quotes.length);
                String randomName = quotes[randomIndex];
                quoteText.setText(randomName);
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                quoteText.setTextColor(color);
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                view2.startAnimation(anim4);
            }
        });

        anim4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                int randomIndex = new Random().nextInt(quotes.length);
                String randomName = quotes[randomIndex];
                quoteText.setText(randomName);
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                quoteText.setTextColor(color);
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                view2.startAnimation(anim2);
            }
        });

        loadPref();
        initialize();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startService(new Intent(getApplicationContext(), Sounds.class));
            }
        }, 0, 90000);

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

    @Override
    protected void onStop()
    {
        super.onStop();
        stopService(new Intent(this, Music.class));
        stopService(new Intent(this, Sounds.class));
    }

    public void loadPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        long dotKey = sharedPref.getLong(dotKeyString, 0);
            dot = dotKey;

        long dpcKey = sharedPref.getLong(dpcKeyString, 1);
            dpc = dpcKey;

        long dpsKey = sharedPref.getLong(dpsKeyString, 0);
            dps = dpsKey;

        long shopDecBoughtKey = sharedPref.getLong(ShopDecorations.shopDecBoughtKeyString, 0);
            ShopDecorations.shopDecBought = shopDecBoughtKey;

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

        quoteText = (TextView) findViewById(R.id.cytat1);

        czapka = (ImageView) findViewById(R.id.czapka);

        Button shopBtn = (Button) findViewById(R.id.shopBtn);

        if (ShopDecorations.shopDecBought == 1) {
            czapka.setVisibility(View.VISIBLE);
        }

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
                //Toast.makeText(getApplicationContext(), "Strona w budowie!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, ShopDecorations.class));
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
