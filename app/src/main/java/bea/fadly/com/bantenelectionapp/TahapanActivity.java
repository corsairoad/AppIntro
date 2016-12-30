package bea.fadly.com.bantenelectionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable;

import java.util.ArrayList;
import java.util.List;

import bea.fadly.com.bantenelectionapp.adapter.TimeLineAdapter;
import bea.fadly.com.bantenelectionapp.domain.TahapanPilkada;
import bea.fadly.com.bantenelectionapp.service.ApiClient;
import bea.fadly.com.bantenelectionapp.service.ApiInterface;
import bea.fadly.com.bantenelectionapp.service.TahapanResponse;
import bea.fadly.com.bantenelectionapp.util.Orientation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TahapanActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    List<TahapanPilkada> listTahapan;
    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private Orientation mOrientation = Orientation.vertical;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahapan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar_tahapan);
        mProgressBar.setIndeterminateDrawable(new FoldingCirclesDrawable.Builder(this)
                .build());

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TahapanResponse> call = apiInterface.getTahapanPilkada();
        call.enqueue(new Callback<TahapanResponse>() {
            @Override
            public void onResponse(Call<TahapanResponse> call, Response<TahapanResponse> response) {
                if (response != null && response.body() != null) {
                    mProgressBar.setVisibility(View.GONE);
                    listTahapan = response.body().getListTahapan();
                    mTimeLineAdapter = new TimeLineAdapter(listTahapan, mOrientation);
                    mRecyclerView.setAdapter(mTimeLineAdapter);
                }
            }

            @Override
            public void onFailure(Call<TahapanResponse> call, Throwable t) {
                    mProgressBar.setVisibility(View.GONE);
                Toast.makeText(TahapanActivity.this, "Maaf koneksi gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private LinearLayoutManager getLinearLayoutManager() {

        if (mOrientation == Orientation.horizontal) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            return linearLayoutManager;
        } else {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            return linearLayoutManager;
        }
    }
}
