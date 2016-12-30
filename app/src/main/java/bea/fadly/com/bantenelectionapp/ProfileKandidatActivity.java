package bea.fadly.com.bantenelectionapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable;
import java.util.ArrayList;
import java.util.List;
import bea.fadly.com.bantenelectionapp.domain.Cagub;
import bea.fadly.com.bantenelectionapp.fragments.KandidatFragment;
import bea.fadly.com.bantenelectionapp.fragments.RecyclerViewFragment;
import bea.fadly.com.bantenelectionapp.service.ApiClient;
import bea.fadly.com.bantenelectionapp.service.ApiInterface;
import bea.fadly.com.bantenelectionapp.service.ProfileCalonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileKandidatActivity extends AppCompatActivity {

    private MaterialViewPager mViewPager;
    List<Cagub> cagubs;
    List<RecyclerViewFragment> fragments = new ArrayList<>();
    int position = -1;
    ProgressBar mProgressBar;
    LinearLayout layoutConnection;
    Button btnTryConnection;
    Drawable drawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_kandidat);

        setTitle("");

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        Toolbar toolbar = mViewPager.getToolbar();
        layoutConnection = (LinearLayout) findViewById(R.id.layout_no_connection);
        btnTryConnection = (Button) findViewById(R.id.btn_try_connection);
        btnTryConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position>=0){
                    connect(position);
                    layoutConnection.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        mProgressBar = (ProgressBar) findViewById(R.id.google_progress);
        mProgressBar.setIndeterminateDrawable(new FoldingCirclesDrawable.Builder(this).build());
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if(getIntent() != null) {
             position = getIntent().getIntExtra(KandidatFragment.KEY_SELECTED_KANDIDAT, -1);
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
                int[] keys = new int[5];
                if (response == null || response.body() == null) {
                    showConnectionFailure();
                    return;
                }
                cagubs = response.body().getProfileCagubs();
                if (cagubs != null && !cagubs.isEmpty()){
                    Cagub cagub = cagubs.get(index);
                    if (cagub.getId() == 1) {
                        keys[0] = ApiClient.KEY_VISI_WH;
                        keys[1] = ApiClient.KEY_MISI_WH;
                        keys[2] = ApiClient.KEY_PROGRAM_WH;
                        keys[3] = ApiClient.KEY_CAGUB_WH;
                        keys[4] = ApiClient.KEY_CAWAGUB_ANDIKA;
                    }else {
                        keys[0] = ApiClient.KEY_VISI_RANO;
                        keys[1] = ApiClient.KEY_MISI_RANO;
                        keys[2] = ApiClient.KEY_PROGRAM_RANO;
                        keys[3] = ApiClient.KEY_CAGUB_RANO;
                        keys[4] = ApiClient.KEY_CAWAGUB_EMBAY;
                    }
                    mViewPager.setVisibility(View.VISIBLE);
                    setUpFagments(keys, cagub);
                    mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ProfileCalonResponse> call, Throwable t) {
               showConnectionFailure();
                Log.e("RETROFIT ERROR", t.toString());
            }
        });
    }

    private void showConnectionFailure() {
        mProgressBar.setVisibility(View.GONE);
        layoutConnection.setVisibility(View.VISIBLE);
    }

    public void setUpFagments(int[] keys, Cagub cagub) {
        for (int i : keys) {
            RecyclerViewFragment fragment = RecyclerViewFragment.newInstance(i, cagub);
            fragments.add(fragment);
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Visi";
                    case 1:
                        return "Misi";
                    case 2:
                        return "Program";
                    case 3:
                        return "Profil Cagub";
                    case 4:
                        return "Profile Cawagub";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {

                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        if (position == 0){
                            drawable = getResources().getDrawable(R.drawable.banner_wh);
                        }else {
                            drawable = getResources().getDrawable(R.drawable.banner_rano);
                        }
                        return HeaderDesign.fromColorResAndDrawable(R.color.com_adobe_image_white_semi_transparent, drawable);
                    /*
                    case 3:
                        if (position == 0){
                           return HeaderDesign.fromColorResAndUrl(R.color.com_adobe_image_white_semi_transparent, "http://www.beritacilegon.co.id/wp-content/uploads/2016/08/IMG_20160709_125108305-655x360.jpg");
                        }else {
                            return HeaderDesign.fromColorResAndUrl(R.color.com_adobe_image_white_semi_transparent, "http://www.netralnews.com/foto/l/46820311388-rano_karno_(suaranasional.com)");
                        }
                    */
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        View logo = findViewById(R.id.logo_white);
    }
}
