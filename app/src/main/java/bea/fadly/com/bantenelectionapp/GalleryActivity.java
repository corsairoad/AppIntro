package bea.fadly.com.bantenelectionapp;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.adobe.creativesdk.aviary.AdobeImageIntent;
import com.adobe.creativesdk.aviary.internal.filters.ToolLoaderFactory;
import com.adobe.creativesdk.aviary.internal.headless.utils.MegaPixels;

import java.util.ArrayList;

import bea.fadly.com.bantenelectionapp.adapter.GalleryAdapter;
import bea.fadly.com.bantenelectionapp.domain.Image;

public class GalleryActivity extends AppCompatActivity implements GalleryAdapter.ClickListener {

    private ArrayList<Image> images;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;
    private TypedArray imgsKamapanye;
    private static final ToolLoaderFactory.Tools[] tools = {ToolLoaderFactory.Tools.TEXT, ToolLoaderFactory.Tools.MEME, ToolLoaderFactory.Tools.CROP};
    private static final int SELECT_PICTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_gallery);

        imgsKamapanye = getResources().obtainTypedArray(R.array.url_gallery_kampanye);
        images = new ArrayList<>();
        mAdapter = new GalleryAdapter(getApplicationContext(), images, imgsKamapanye, this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view, int pos) {
        //Toast.makeText(this,"Image clicked", Toast.LENGTH_SHORT).show();
        //Uri imageUri = Uri.parse("content://media/external/images/media/####");
        //Uri uri = Uri.parse("android.resource://bea.fadly.com.bantenelectionapp/drawable/gallery_rano.png");
        Uri uri = Uri.parse(imgsKamapanye.getString(pos));
        Intent imageEditorIntent = new AdobeImageIntent.Builder(this)
                .setData(uri)
                .withOutputSize(MegaPixels.Mp15)
                .withToolList(tools)
                .build();

        startActivityForResult(imageEditorIntent, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_load_gallery:
                loadImageFromGallery();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadImageFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih gambar.."), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                /* 4) Make a case for the request code we passed to startActivityForResult() */
                case 1:
                    /* 5) Show the image! */
                    data.setClass(this, ResultImageActivity.class);
                    startActivity(new Intent(data));
                    break;
                case SELECT_PICTURE:
                    Uri uri = data.getData();
                    Intent imageEditorIntent = new AdobeImageIntent.Builder(this)
                            .setData(uri)
                            .withOutputSize(MegaPixels.Mp15)
                            .withToolList(tools)
                            .build();

                    startActivityForResult(imageEditorIntent, 1);

            }
    }
}}
