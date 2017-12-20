package com.clicker.sd.myclicker;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.media.MediaPlayer;
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
    public static ImageView tesc;
    public ImageView background;

    int [] songs;
    int random_index;

    private MediaPlayer mp1;
    private MediaPlayer mp2;

    Random rnd = new Random();
    Random rnd2 = new Random();

    Timer timer = new Timer();

    public ImageButton dotBtn;

    private String[] quotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quotes = getResources().getStringArray(R.array.quotes);

        songs = new int[] {R.raw.theme,R.raw.janusztracz,R.raw.remix};
        random_index = songs.length;

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

        final View view2 = findViewById(R.id.quoteText);

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
        music();

        if (dot >= 100000) {
            background();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int[] sounds={R.raw.andzela, R.raw.cena_nie_jest_taka_wazna, R.raw.czas_to_pieniadz,R.raw.czasem_moze_kosztowac_glowe,R.raw.jesli_mdleje_to_niech_robi_to_prywatnie,R.raw.jednak_whisky,R.raw.jestem_niewierzacy,R.raw.jestem_zly_brutalny_nikczemny,R.raw.moj_prestiz_opiera_sie_na_strachu,R.raw.nie_odmawia_sie_kiedy_pieniadz_wola,R.raw.nie_pracuje_na_godziny, R.raw.ojcowizna, R.raw.osa_odprowadz_ksiedza,R.raw.sadzisz_ze_nie_obchodzi_mnie_ich_los,R.raw.sprzeda_mi_swoja_dusze, R.raw.stukniemy_sie, R.raw.taka_dobra_religijna_kobieta,R.raw.to_przeciez_biedni_ludzie,R.raw.tortury_mie_uspokajaja, R.raw.weronika_pana_przekonala, R.raw.wzruszajace,R.raw.zabrac_dobytek,R.raw.zamknij_sie};
                Random r = new Random();
                int Low = 0;
                int High = 23;
                int rndm = r.nextInt(High-Low) + Low;
                mp1 = MediaPlayer.create(getApplicationContext(),sounds[rndm]);
                mp1.start();
            }
        }, 0, 30000);

        Thread thread = new Thread() {
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (dot >= 100000) {
                                    colortransition();
                                }
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

        quoteText = (TextView) findViewById(R.id.quoteText);

        tesc = (ImageView) findViewById(R.id.tesc);

        Button shopBtn = (Button) findViewById(R.id.shopBtn);

        background = (ImageView) findViewById(R.id.mainbackground);

        if (dot >= 100000) {
            final Animation anim5 = AnimationUtils.loadAnimation(this, R.anim.scalein);
            final View view = findViewById(R.id.mainbackground);
            view.startAnimation(anim5);
        }

        if (ShopDecorations.shopDecBought == 1) {
            tesc.setVisibility(View.VISIBLE);
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

    int colorFrom = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

    public void background() {
        background.setColorFilter(colorFrom, android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    public void colortransition() {
        int colorTo = Color.argb(255, rnd2.nextInt(256), rnd2.nextInt(256), rnd2.nextInt(256));
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorFrom = colorTo;
        colorAnimation.setDuration(1000);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                background.setColorFilter((int) animator.getAnimatedValue(), android.graphics.PorterDuff.Mode.MULTIPLY);
            }

        });
        colorAnimation.start();
    }

    public void music(){

        mp2 = MediaPlayer.create(getApplicationContext(), songs[rnd.nextInt(random_index)]);
        mp2.start();

        mp2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                music();
            }
        });
    }

    public void dotBtn(View v){
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        final View view = findViewById(R.id.dotBtn);

        view.startAnimation(anim);

        dot += dpc;
        dotsView.setText(dot + "$");

        savePref(dotKeyString, dot);
    }

    public void onDestroy(){
        super.onDestroy();
        mp1.release();
        mp2.release();
    }

}
