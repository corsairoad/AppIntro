package bea.fadly.com.bantenelectionapp;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.adobe.creativesdk.aviary.AdobeImageIntent;
import com.adobe.creativesdk.aviary.internal.filters.ToolLoaderFactory;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;

import bea.fadly.com.bantenelectionapp.adapter.MyPagerAdapter;
import bea.fadly.com.bantenelectionapp.fragments.KandidatFragment;
import bea.fadly.com.bantenelectionapp.fragments.NewsFragment;
import bea.fadly.com.bantenelectionapp.fragments.PilkadaFragment;
import bea.fadly.com.bantenelectionapp.service.ClearNewsService;
import bea.fadly.com.bantenelectionapp.service.PrefManager;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private static final String LOGO_PILGUB = "com.fadly.logopilgub";
    private static final String LOGO_PASLON = "com.fadly.logopaslon";
    private static final String LOGO_WH_ANDIKA = "com.fadly.whandika";
    private static final String LOGO_RANO_EMBAY = "com.fadly.ranoembay";

    private SliderLayout mDemoSlider;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);

        startService(new Intent(this, ClearNewsService.class));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        getSupportActionBar().setHomeButtonEnabled(true);

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        setUpSlider();
        setUpViewPager();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_campaign:
                startActivity(new Intent(this, GalleryActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpViewPager() {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(NewsFragment.newInstance(null, null), getResources().getString(R.string.title_pager_news));
        adapter.addFragment(KandidatFragment.newInstance(null, null), getResources().getString(R.string.title_pager_kandidat));
        adapter.addFragment(PilkadaFragment.newInstance(null, null), getResources().getString(R.string.title_pager_pilkada));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setUpSlider() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put(LOGO_PILGUB, R.drawable.pilgublogo);
        map.put(LOGO_PASLON, R.drawable.paslon);
        map.put(LOGO_WH_ANDIKA, R.drawable.wh_andika);
        map.put(LOGO_RANO_EMBAY, R.drawable.rano_embay_2);

        HashMap<String, String> descriptions = new HashMap<>();
        descriptions.put(LOGO_WH_ANDIKA, getResources().getString(R.string.wh_andika_desc));
        descriptions.put(LOGO_PILGUB, getResources().getString(R.string.pilgub_desc));
        descriptions.put(LOGO_PASLON, getResources().getString(R.string.paslon_desc));
        descriptions.put(LOGO_RANO_EMBAY, getResources().getString(R.string.rano_embay_desc));

        for (String name : map.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.description(descriptions.get(name))
                    .image(map.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            mDemoSlider.addSlider(textSliderView);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
