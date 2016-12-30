package bea.fadly.com.bantenelectionapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable;
import java.util.ArrayList;
import java.util.List;
import bea.fadly.com.bantenelectionapp.adapter.SimpleListAdapter;
import bea.fadly.com.bantenelectionapp.domain.PratisipasiPilkada;
import bea.fadly.com.bantenelectionapp.service.ApiClient;
import bea.fadly.com.bantenelectionapp.service.ApiInterface;
import bea.fadly.com.bantenelectionapp.service.PartsPilkadaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartsPilkadaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    ApiInterface apiInterface;
    ProgressBar progressBar;
    LinearLayout layoutNoConnection;
    Button btnTryConnection;
    List<PratisipasiPilkada> listPartisipasi = new ArrayList<>();
    SimpleListAdapter adapter;
    ListView listNamaDaerah;
    BarChart chart;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parts_pilkada);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        chart = (BarChart) findViewById(R.id.chart);

        listNamaDaerah = (ListView) findViewById(R.id.list_nama_daerah);
        listNamaDaerah.setOnItemClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.google_progress);
        progressBar.setIndeterminateDrawable(new FoldingCirclesDrawable.Builder(this).build());
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        layoutNoConnection = (LinearLayout) findViewById(R.id.layout_no_connection);
        btnTryConnection = (Button) findViewById(R.id.btn_try_connection);
        btnTryConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadData();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        downloadData();
    }

    private void setupChart(PratisipasiPilkada pratisipasiPilkada) {
        getSupportActionBar().setTitle(pratisipasiPilkada.getNamaWilaha());
        BarData data = new BarData(PratisipasiPilkada.getXaxisPartsPilkada(), getDataSet(pratisipasiPilkada));
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();
        chart.setVisibility(View.VISIBLE);
    }

    private List<BarDataSet> getDataSet(PratisipasiPilkada parts) {
        List<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(Float.valueOf(parts.getDataPemilihLaki()), 0);
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(Float.valueOf(parts.getPenggHakPilihLaki()), 1);
        valueSet1.add(v1e2);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v1e22 = new BarEntry(Float.valueOf(parts.getDataPemilihPerempuan()), 0);
        valueSet2.add(v1e22);
        BarEntry v1e23 = new BarEntry(Float.valueOf(parts.getPenggHakPilihPerempuan()), 1);
        valueSet2.add(v1e23);

        ArrayList<BarEntry> valueSet3 = new ArrayList<>();
        BarEntry b1 = new BarEntry(Float.valueOf(parts.getJumDisabilitas()), 0);
        valueSet3.add(b1);
        BarEntry b2 = new BarEntry(Float.valueOf(parts.getPartsDisabilitas()), 1);
        valueSet3.add(b2);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Laki-laki");
        barDataSet1.setColor(Color.rgb(245, 14, 74));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Perempuan");
        barDataSet2.setColor(Color.rgb(0, 155, 20));
        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "Disabilitas");
        barDataSet3.setColor(Color.rgb(74, 40, 120));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);
        return dataSets;

    }

    private void setUpAdapter() {
        adapter = new SimpleListAdapter(this,0, listPartisipasi);
        listNamaDaerah.setAdapter(adapter);
    }

    private void downloadData() {
        progressBar.setVisibility(View.VISIBLE);
        showLayoutConnection(false);
        Call<PartsPilkadaResponse> call = apiInterface.getPartisipasiPilkada();
        call.enqueue(new Callback<PartsPilkadaResponse>() {
            @Override
            public void onResponse(Call<PartsPilkadaResponse> call, Response<PartsPilkadaResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response == null && response.body()== null) {
                    showLayoutConnection(true);
                    return;
                }

                listPartisipasi = response.body().getResults();
                setUpAdapter();
                setupChart(listPartisipasi.get(1));
            }

            @Override
            public void onFailure(Call<PartsPilkadaResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                showLayoutConnection(true);
            }
        });
    }

    public void showLayoutConnection(boolean show){
        if (!show){
            layoutNoConnection.setVisibility(View.GONE);
            return;
        }
        layoutNoConnection.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.parts_pilkada, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setupChart(listPartisipasi.get(position));
        drawer.closeDrawer(Gravity.LEFT);
    }
}
