package com.clicker.sd.myclicker;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

public class Sounds extends Service {

    private MediaPlayer mp1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int[] sounds={R.raw.andzela, R.raw.cena_nie_jest_taka_wazna, R.raw.czas_to_pieniadz,R.raw.czasem_moze_kosztowac_glowe,R.raw.jesli_mdleje_to_niech_robi_to_prywatnie,R.raw.jednak_whisky,R.raw.jestem_niewierzacy,R.raw.jestem_zly_brutalny_nikczemny,R.raw.moj_prestiz_opiera_sie_na_strachu,R.raw.nie_odmawia_sie_kiedy_pieniadz_wola,R.raw.nie_pracuje_na_godziny, R.raw.ojcowizna, R.raw.osa_odprowadz_ksiedza,R.raw.sadzisz_ze_nie_obchodzi_mnie_ich_los,R.raw.sprzeda_mi_swoja_dusze, R.raw.stukniemy_sie, R.raw.taka_dobra_religijna_kobieta,R.raw.to_przeciez_biedni_ludzie,R.raw.tortury_mie_uspokajaja, R.raw.weronika_pana_przekonala, R.raw.wzruszajace,R.raw.zabrac_dobytek,R.raw.zamknij_sie};
        Random r = new Random();
        int Low = 0;
        int High = 23;
        int rndm = r.nextInt(High-Low) + Low;
        mp1 = MediaPlayer.create(getApplicationContext(),sounds[rndm]);
        mp1.start();

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mp1.stop();
    }
}