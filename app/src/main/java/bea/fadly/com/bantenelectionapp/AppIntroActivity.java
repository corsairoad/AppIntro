package bea.fadly.com.bantenelectionapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import bea.fadly.com.bantenelectionapp.fragments.FragmentIntro1;
import bea.fadly.com.bantenelectionapp.fragments.FragmentIntro2;
import bea.fadly.com.bantenelectionapp.fragments.FragmentIntro3;
import bea.fadly.com.bantenelectionapp.service.PrefManager;

/**
 * Created by DIGIKOM-EX4 on 12/5/2016.
 */

public class AppIntroActivity extends AppIntro {

    PrefManager prefManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.

        prefManager = PrefManager.getInstance(this);
        if (prefManager.isFirstLaunch()){
            startMainActivity();
            return;
        }
        addSlide(FragmentIntro1.newInstance(null, null));
        addSlide(FragmentIntro2.newInstance(null,null));
        addSlide(FragmentIntro3.newInstance(null, null));

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        //addSlide(AppIntroFragment.newInstance("Debus App", "Informasi seputar Pilkada Banten 2017", R.mipmap.my_launcher, getResources().getColor(R.color.colorPrimary)));
        // OPTIONAL METHODS
        // Override bar/separator color.
        //setBarColor(Color.parseColor("#3F51B5"));
        //setSeparatorColor(Color.parseColor("#2196F3"));
        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startMainActivity();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startMainActivity();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

    public void startMainActivity(){
        prefManager.setFirstLaunch(false);
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
