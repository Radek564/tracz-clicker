package com.palusiniak.traczclicker;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.preference.PreferenceManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.Intent;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    public static long dot;
        public static String dotKeyString = "dots";

    public static long totalDpc;
        public static String totalDpcKeyString = "totalDpc";

    public static long totalDps;
        public static String totalDpsKeyString = "totalDps";

    public static long dpc;
        public static String dpcKeyString = "dpc";

    public static long dps;
        public static String dpsKeyString = "dps";

    public static double patrimonyBonus;

    public static double videoBonus;

    public static boolean adOpened = false;

    public static TextView dotsView;
    public static TextView dpsAndDpcView;
    public static TextView bonusView;
    public static TextView quoteText;
    public static ImageView tesc;
    public static ImageView panwalencik;
    public static ImageView wikary;
    public static ImageView czapka;
    public static ImageView woz;
    public ImageView background;

    int [] songs;
    int random_index;

    public static MediaPlayer mp1;
    public static MediaPlayer mp2;

    Random rnd = new Random();
    Random rnd2 = new Random();

    Timer timer = new Timer();

    public ImageButton dotBtn;

    private String[] quotes;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-1707292710475822~6599200626");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        quotes = getResources().getStringArray(R.array.quotes);

        songs = new int[] {R.raw.janusztracz,R.raw.remix,R.raw.mcclogg};
        random_index = songs.length;

        final int[] sounds = {R.raw.innemiejsce, R.raw.samaprzyszlas, R.raw.klatka, R.raw.wietnam, R.raw.zlyczlowiek, R.raw.andzela, R.raw.cena_nie_jest_taka_wazna, R.raw.czas_to_pieniadz,R.raw.czasem_moze_kosztowac_glowe,R.raw.jesli_mdleje_to_niech_robi_to_prywatnie,R.raw.jednak_whisky,R.raw.jestem_niewierzacy,R.raw.jestem_zly_brutalny_nikczemny,R.raw.moj_prestiz_opiera_sie_na_strachu,R.raw.nie_odmawia_sie_kiedy_pieniadz_wola,R.raw.nie_pracuje_na_godziny, R.raw.ojcowizna, R.raw.osa_odprowadz_ksiedza,R.raw.sadzisz_ze_nie_obchodzi_mnie_ich_los,R.raw.sprzeda_mi_swoja_dusze, R.raw.stukniemy_sie, R.raw.taka_dobra_religijna_kobieta,R.raw.to_przeciez_biedni_ludzie,R.raw.tortury_mie_uspokajaja, R.raw.weronika_pana_przekonala, R.raw.wzruszajace,R.raw.zabrac_dobytek,R.raw.zamknij_sie};
        final int random_index2 = sounds.length;

        loadPref();
        initialize();
        music();
        animationQuote();
        thread.start();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mp1 = MediaPlayer.create(getApplicationContext(), sounds[new Random().nextInt(random_index2)]);
                if (Config.checkingSound && !adOpened) {
                    mp1.setVolume(1, 1);
                } else {
                    mp1.setVolume(0, 0);
                }
                if (Config.checkingMusic && Config.checkingSound&& !adOpened) {
                    mp2.setVolume(0.3f, 0.3f);
                }
                mp1.start();
                mp1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        if (Config.checkingMusic && Config.checkingSound && !adOpened){
                            mp2.setVolume(1, 1);
                        }
                    }
                });

            }
        }, 0, 30000);

        final Handler handler = new Handler();
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                if (dot >= 100000) {
                    colortransition();
                } else {
                    background.clearColorFilter();
                }

                if (ShopDecorations.shopDecBought == 1) {
                    tesc.setVisibility(View.VISIBLE);
                } else {
                    tesc.setVisibility(View.INVISIBLE);
                }

                if (ShopDecorations.shopDec2Bought == 1) {
                    panwalencik.setVisibility(View.VISIBLE);
                } else {
                    panwalencik.setVisibility(View.INVISIBLE);
                }

                if (ShopDecorations.shopDec3Bought == 1) {
                    wikary.setVisibility(View.VISIBLE);
                } else {
                    wikary.setVisibility(View.INVISIBLE);
                }

                if (ShopDecorations.shopDec4Bought == 1) {
                    czapka.setVisibility(View.VISIBLE);
                } else {
                    czapka.setVisibility(View.INVISIBLE);
                }

                if (ShopDecorations.shopDec5Bought == 1) {
                    woz.setVisibility(View.VISIBLE);
                } else {
                    woz.setVisibility(View.INVISIBLE);
                }

                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnableCode);

        final Handler handler2 = new Handler();
        Runnable runnableCode2 = new Runnable() {
            final Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scalein);
            @Override
            public void run() {

                if (dot >= 50000) {
                    background.startAnimation(anim);
                } else {
                    background.clearAnimation();
                }

                handler2.postDelayed(this, 600);
            }
        };
        handler2.post(runnableCode2);

    }

    Thread thread = new Thread() {
        public void run() {
            try {
                while (!isInterrupted()) {
                    Thread.sleep(1000);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            dot += (dps * videoBonus);
                            totalDps += (dps * videoBonus);;

                            if (ShopDecorations.shopDec2Bought == 1) {
                                patrimonyBonus = ShopSecond.patrimonyBought*(50*Statistics.resetMultiplier)*0.1;
                                dot += patrimonyBonus;
                                totalDps += patrimonyBonus;
                            }

                            dotsView.setText(dot + "$");
                            savePref(dotKeyString, dot);
                            savePref(totalDpsKeyString, totalDps);
                        }
                    });
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    };

    public void loadPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        long dotKey = sharedPref.getLong(dotKeyString, 0);
            dot = dotKey;

        long totalDpcKey = sharedPref.getLong(totalDpcKeyString, 0);
            totalDpc = totalDpcKey;

        long totalDpsKey = sharedPref.getLong(totalDpsKeyString, 0);
            totalDps = totalDpsKey;

        long dpcKey = sharedPref.getLong(dpcKeyString, 1);
            dpc = dpcKey;

        long dpsKey = sharedPref.getLong(dpsKeyString, 0);
            dps = dpsKey;

        long shopDecBoughtKey = sharedPref.getLong(ShopDecorations.shopDecBoughtKeyString, 0);
            ShopDecorations.shopDecBought = shopDecBoughtKey;

        long shopDec2BoughtKey = sharedPref.getLong(ShopDecorations.shopDec2BoughtKeyString, 0);
        ShopDecorations.shopDec2Bought = shopDec2BoughtKey;

        long shopDec3BoughtKey = sharedPref.getLong(ShopDecorations.shopDec3BoughtKeyString, 0);
        ShopDecorations.shopDec3Bought = shopDec3BoughtKey;

        long shopDec4BoughtKey = sharedPref.getLong(ShopDecorations.shopDec4BoughtKeyString, 0);
        ShopDecorations.shopDec4Bought = shopDec4BoughtKey;

        long shopDec5BoughtKey = sharedPref.getLong(ShopDecorations.shopDec5BoughtKeyString, 0);
        ShopDecorations.shopDec5Bought = shopDec5BoughtKey;

        boolean checkingMusicKey = sharedPref.getBoolean(Config.checkingMusicKeyString, true);
        Config.checkingMusic = checkingMusicKey;

        boolean checkingSoundKey = sharedPref.getBoolean(Config.checkingSoundKeyString, true);
        Config.checkingSound = checkingSoundKey;

        long patrimonyBoughtKey = sharedPref.getLong(ShopSecond.patrimonyBoughtKeyString, 0);
        ShopSecond.patrimonyBought = patrimonyBoughtKey;

        long resetMultiplierKey = sharedPref.getLong(Statistics.resetMultiplierKeyString, 1);
        Statistics.resetMultiplier = resetMultiplierKey;
    }

    public void savePref(String key, long value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void initialize() {

        videoBonus = 1;

        dotsView = (TextView) findViewById(R.id.dotsView);
            dotsView.setText(dot + "$");

        dpsAndDpcView = (TextView) findViewById(R.id.dpsAndDpcView);
            dpsAndDpcView.setText(dps +"$/sek" + System.getProperty ("line.separator") + dpc + "$/klik");

        bonusView = (TextView) findViewById(R.id.bonusView);

        quoteText = (TextView) findViewById(R.id.quoteText);

        tesc = (ImageView) findViewById(R.id.tesc);

        panwalencik = (ImageView) findViewById(R.id.panwalencik);

        wikary = (ImageView) findViewById(R.id.wikary);

        czapka = (ImageView) findViewById(R.id.czapka);

        woz = (ImageView) findViewById(R.id.woz);

        dotBtn = (ImageButton) findViewById(R.id.dotBtn);

        background = (ImageView) findViewById(R.id.mainbackground);

        final Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);

        dotBtn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == (MotionEvent.ACTION_UP)){
                    dotBtn.startAnimation(anim);

                    dot += (dpc * videoBonus);
                    totalDpc += (dpc * videoBonus);

                    dotsView.setText(dot + "$");

                    savePref(dotKeyString, dot);
                    savePref(totalDpcKeyString, totalDpc);
                }
                else{
                }
                return true;
            }
        });

        final Button shopBtn = (Button) findViewById(R.id.shopBtn);

        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShopClick.class));
            }
        });

        final Button shopBtn2 = (Button) findViewById(R.id.shopBtn2);

        shopBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShopSecond.class));
            }
        });

        final Button shopBtn3 = (Button) findViewById(R.id.shopBtn3);

        shopBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShopDecorations.class));
            }
        });

        ImageButton configBtn = (ImageButton) findViewById(R.id.configBtn);

        configBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Config.class));
            }
        });

        ImageButton statsBtn = (ImageButton) findViewById(R.id.statsBtn);

        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Statistics.class));
            }
        });

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)shopBtn.getLayoutParams();
                RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams)shopBtn2.getLayoutParams();
                RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams)shopBtn3.getLayoutParams();

                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
                params2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
                params3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);

                shopBtn.setLayoutParams(params);
                shopBtn2.setLayoutParams(params2);
                shopBtn3.setLayoutParams(params3);
            }
        });
    }

    int colorFrom = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

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
        if (Config.checkingMusic && !adOpened) {
            mp2.setVolume(1, 1);
        } else {
            mp2.setVolume(0, 0);
        }
        mp2.start();

        mp2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp2.release();
                music();
            }
        });
    }

    float minStartX = 0.0f;
    float maxStartX = 1.0f;
    float minX = -1.0f;
    float maxX = 2.0f;
    float minStartY = 0.0f;
    float maxStartY = 1.0f;
    float minY = -1.0f;
    float maxY = 2.0f;
    Animation move;
    Animation fadein;
    Animation fadeout;

    public void animationQuote(){

        int randomIndex = new Random().nextInt(quotes.length);
        String randomName = quotes[randomIndex];
        quoteText.setText(randomName);
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        quoteText.setTextColor(color);

        fadein = new AlphaAnimation(0, 1);
        fadein.setInterpolator(new DecelerateInterpolator());
        fadein.setDuration(1000);

        fadeout = new AlphaAnimation(1, 0);
        fadeout.setInterpolator(new AccelerateInterpolator());
        fadeout.setStartOffset(5000);
        fadeout.setDuration(1000);

        move = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT,rnd.nextFloat() * (maxStartX - minStartX) + minStartX,
                TranslateAnimation.RELATIVE_TO_PARENT,rnd.nextFloat() * (maxX - minX) + minX,
                TranslateAnimation.RELATIVE_TO_PARENT,rnd.nextFloat() * (maxStartY - minStartY) + minStartY,
                TranslateAnimation.RELATIVE_TO_PARENT,rnd.nextFloat() * (maxY - minY) + minY);

        move.setDuration(6000);
        move.setFillAfter(true);
        move.setInterpolator(new LinearInterpolator());

        AnimationSet set = new AnimationSet(false);
        set.addAnimation(fadein);
        set.addAnimation(move);
        set.addAnimation(fadeout);

        quoteText.startAnimation(set);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                animationQuote();
            }
        });
    }

    public void onDestroy(){
        super.onDestroy();
        mp1.release();
        mp2.release();
        thread.interrupt();
        timer.cancel();
    }

}
