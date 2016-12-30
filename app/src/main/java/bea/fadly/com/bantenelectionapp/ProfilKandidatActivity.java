package bea.fadly.com.bantenelectionapp;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import bea.fadly.com.bantenelectionapp.domain.Cagub;
import bea.fadly.com.bantenelectionapp.fragments.KandidatFragment;
import bea.fadly.com.bantenelectionapp.service.ApiClient;
import bea.fadly.com.bantenelectionapp.service.ApiInterface;
import bea.fadly.com.bantenelectionapp.service.ProfileCalonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilKandidatActivity extends AppCompatActivity {

    ImageView imgKandidat;
    FloatingActionButton fabNomor;
    List<Cagub> cagubs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_kandidat);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = ContextCompat.getDrawable(this,android.support.design.R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        imgKandidat = (ImageView) findViewById(R.id.img_kandidat);
        fabNomor = (FloatingActionButton) findViewById(R.id.fab_nomor);

        if(getIntent() != null) {
            int position = getIntent().getIntExtra(KandidatFragment.KEY_SELECTED_KANDIDAT, -1);
            if(position>=0){
                connect(position);
            }
        }

    }

    public void connect(int index){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProfileCalonResponse> profileCalonResponseCall = apiInterface.getProfileKandidat();
        profileCalonResponseCall.enqueue(new Callback<ProfileCalonResponse>() {
            @Override
            public void onResponse(Call<ProfileCalonResponse> call, Response<ProfileCalonResponse> response) {
                cagubs = response.body().getProfileCagubs();
                if (cagubs != null && !cagubs.isEmpty()){
                    Cagub cagub = cagubs.get(index);
                    if (cagub.getId() == 1) {
                        imgKandidat.setImageResource(R.drawable.banner_wh);
                        fabNomor.setImageResource(R.drawable.number_one);
                    }else {
                        imgKandidat.setImageResource(R.drawable.banner_rano);
                        fabNomor.setImageResource(R.drawable.number_two);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileCalonResponse> call, Throwable t) {
                Log.e("RETROFIT ERROR", t.toString());
            }
        });
    }

}
