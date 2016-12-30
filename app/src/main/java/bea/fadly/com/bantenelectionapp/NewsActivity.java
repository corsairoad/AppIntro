package bea.fadly.com.bantenelectionapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import bea.fadly.com.bantenelectionapp.domain.News;
import bea.fadly.com.bantenelectionapp.fragments.NewsFragment;
import bea.fadly.com.bantenelectionapp.service.MySingleton;
import de.hdodenhof.circleimageview.CircleImageView;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener{

    News news;
    NetworkImageView imgNews;
    TextView textTitleNews;
    TextView textAuthorNews;
    TextView textPortal;
    TextView textPubDate;
    TextView textContentNews;
    CircleImageView imgShareFb;
    CircleImageView imgShareTwitter;
    CircleImageView imgShareWhatsapp;
    CircleImageView imgShareLine;
    CircleImageView imgShare;

    private static final String KEY_WHATSAPP = "com.whatsapp";
    private static final String KEY_FACEBOOK = "com.facebook.katana";
    private static final String KEY_LINE = "jp.naver.line.android";
    private static final String KEY_TWITTER = "com.twitter.android";
    private static final String KEY_SHARE = "share";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        final Drawable upArrow = ContextCompat.getDrawable(this,android.support.design.R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        imgNews = (NetworkImageView) findViewById(R.id.backdrop);
        textTitleNews = (TextView) findViewById(R.id.text_title_news);
        textAuthorNews = (TextView) findViewById(R.id.text_author_news);
        textPortal = (TextView) findViewById(R.id.text_portal);
        textPubDate = (TextView) findViewById(R.id.text_pubdate_news);
        textContentNews = (TextView) findViewById(R.id.text_content_news);

        imgShareFb = (CircleImageView) findViewById(R.id.img_share_fb);
        imgShareFb.setTag(KEY_FACEBOOK);
        imgShareLine = (CircleImageView) findViewById(R.id.img_share_line);
        imgShareLine.setTag(KEY_LINE);
        imgShareTwitter = (CircleImageView) findViewById(R.id.img_share_twitter);
        imgShareTwitter.setTag(KEY_TWITTER);
        imgShareWhatsapp = (CircleImageView) findViewById(R.id.img_share_whatsapp);
        imgShareWhatsapp.setTag(KEY_WHATSAPP);
        imgShare = (CircleImageView) findViewById(R.id.img_share_);
        imgShare.setTag(KEY_SHARE);

        if (getIntent() != null) {
            news = getIntent().getParcelableExtra(NewsFragment.KEY_SINGLE_NEWS);
            imgNews.setImageUrl(news.getImageUrl(), MySingleton.getInstance(this).getImageLoader());
            textTitleNews.setText(news.getTitle());
            textAuthorNews.setText("by " + news.getAuthor());
            textPubDate.setText(news.getDate());

            String[] splits = news.getDescription().substring(0,9).trim().split("-");
            String head = "<b>" + splits[0] + "</b>";
            String text =  news.getDescription().trim().replace(splits[0] + " - ", "");

            textContentNews.setText(Html.fromHtml(news.getDescription()));

            imgShare.setOnClickListener(this);
            imgShareFb.setOnClickListener(this);
            imgShareWhatsapp.setOnClickListener(this);
            imgShareTwitter.setOnClickListener(this);
            imgShareLine.setOnClickListener(this);
        }

    }


    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TITLE, getString(R.string.app_name));
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_name) + "\n" +  news.getLink());
        shareIntent.setType("text/plain");
        switch (tag){
            case KEY_WHATSAPP:
                shareIntent.setPackage(KEY_WHATSAPP);
                break;
            case KEY_TWITTER:
                shareIntent.setPackage(KEY_TWITTER);
                break;
            case KEY_LINE:
                shareIntent.setPackage(KEY_LINE);
                break;
            case KEY_FACEBOOK:
                shareIntent.setPackage(KEY_FACEBOOK);
                break;
            default:
                break;
        }
        try {
            startActivity(shareIntent);
        }catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + shareIntent.getPackage())));
        }

    }
}
