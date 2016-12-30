package bea.fadly.com.bantenelectionapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.adobe.creativesdk.aviary.AdobeImageIntent;

import uk.co.senab.photoview.PhotoView;

public class ResultImageActivity extends AppCompatActivity {

    PhotoView photoView;
    Toolbar toolbar;
    Uri editedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        toolbar = (Toolbar) findViewById(R.id.toolbarResult);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        photoView = (PhotoView) findViewById(R.id.iv_photo);

        if(getIntent() != null) {
            editedImageUri = getIntent().getParcelableExtra(AdobeImageIntent.EXTRA_OUTPUT_URI);
            photoView.setImageURI(editedImageUri);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_share:
                share();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void share() {
        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.putExtra(Intent.EXTRA_STREAM,editedImageUri);
        intentShare.setType("image/jpeg");
        Intent chooser = Intent.createChooser(intentShare,getResources().getString(R.string.send_to));
        startActivity(chooser);
    }
}
