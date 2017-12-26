package com.clicker.sd.myclicker;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CheckBox;

import java.util.Random;

public class Config extends Activity implements View.OnClickListener {

    public static boolean checkingMusic;
    public static String checkingMusicKeyString = "checkingMusic";

    public static boolean checkingSound;
    public static String checkingSoundKeyString = "checkingSound";

    public CheckBox music;
    public CheckBox sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.5));

        music = (CheckBox) findViewById(R.id.musicBox);
        music.setOnClickListener(this);
        sound = (CheckBox) findViewById(R.id.soundBox);
        sound.setOnClickListener(this);

        loadPref();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.musicBox:
                if (music.isChecked())
                {
                    MainActivity.mp2.setVolume(1,1);
                    checkingMusic = true;
                } else {
                    MainActivity.mp2.setVolume(0,0);
                    checkingMusic = false;
                }
                savePref(checkingMusicKeyString, music.isChecked());
                break;
            case R.id.soundBox:
                if (sound.isChecked())
                {
                    MainActivity.mp1.setVolume(1,1);
                    checkingSound = true;
                } else {
                    MainActivity.mp1.setVolume(0,0);
                    checkingSound = false;
                }
                savePref(checkingSoundKeyString, sound.isChecked());
                break;
        }
    }
    int [] songs = new int[] {R.raw.janusztracz,R.raw.remix,R.raw.mcclogg};
    int random_index = songs.length;

    int[] sounds = {R.raw.andzela, R.raw.cena_nie_jest_taka_wazna, R.raw.czas_to_pieniadz,R.raw.czasem_moze_kosztowac_glowe,R.raw.jesli_mdleje_to_niech_robi_to_prywatnie,R.raw.jednak_whisky,R.raw.jestem_niewierzacy,R.raw.jestem_zly_brutalny_nikczemny,R.raw.moj_prestiz_opiera_sie_na_strachu,R.raw.nie_odmawia_sie_kiedy_pieniadz_wola,R.raw.nie_pracuje_na_godziny, R.raw.ojcowizna, R.raw.osa_odprowadz_ksiedza,R.raw.sadzisz_ze_nie_obchodzi_mnie_ich_los,R.raw.sprzeda_mi_swoja_dusze, R.raw.stukniemy_sie, R.raw.taka_dobra_religijna_kobieta,R.raw.to_przeciez_biedni_ludzie,R.raw.tortury_mie_uspokajaja, R.raw.weronika_pana_przekonala, R.raw.wzruszajace,R.raw.zabrac_dobytek,R.raw.zamknij_sie};
    int random_index2 = sounds.length;

    public void randomSongBtn(View v) {
        randommusic();
    }

    public void randommusic(){
        MainActivity.mp2.release();
        MainActivity.mp2 = MediaPlayer.create(getApplicationContext(), songs[new Random().nextInt(random_index)]);
        if (music.isChecked()) {
            MainActivity.mp2.setVolume(1, 1);
        } else {
            MainActivity.mp2.setVolume(0,0);
        }
        MainActivity.mp2.start();

        MainActivity.mp2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                randommusic();
            }
        });
    }

    public void randomsound() {
        MainActivity.mp1.release();
        MainActivity.mp1 = MediaPlayer.create(getApplicationContext(), sounds[new Random().nextInt(random_index2)]);
        if (sound.isChecked()) {
            MainActivity.mp1.setVolume(1, 1);
        } else {
            MainActivity.mp1.setVolume(0,0);
        }
        if (music.isChecked() && sound.isChecked()) {
            MainActivity.mp2.setVolume(0.3f, 0.3f);
        }
        MainActivity.mp1.start();
        MainActivity.mp1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                if (music.isChecked() && sound.isChecked()) {
                    MainActivity.mp2.setVolume(1, 1);
                }
            }
        });
    }

    public void randomSoundBtn(View v) {
        randomsound();
    }

    public void loadPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        boolean checkingMusicKey = sharedPref.getBoolean(checkingMusicKeyString, true);
        music.setChecked(checkingMusicKey);

        boolean checkingSoundKey = sharedPref.getBoolean(checkingSoundKeyString, true);
        sound.setChecked(checkingSoundKey);
    }

    public void savePref(String key, boolean value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
}
