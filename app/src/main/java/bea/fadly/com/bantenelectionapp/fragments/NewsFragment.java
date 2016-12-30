package bea.fadly.com.bantenelectionapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import bea.fadly.com.bantenelectionapp.NewsActivity;
import bea.fadly.com.bantenelectionapp.R;
import bea.fadly.com.bantenelectionapp.adapter.NewsRecyclerAdapter;
import bea.fadly.com.bantenelectionapp.domain.News;
import bea.fadly.com.bantenelectionapp.service.PrefManager;
import bea.fadly.com.bantenelectionapp.util.MyHtmlParser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment implements View.OnClickListener, NewsRecyclerAdapter.OnRecyclerItemClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String KEY_LIST_NEWS="list_news";
    public static final String KEY_SINGLE_NEWS = "single.news";

    private String mParam1;
    private String mParam2;

    RecyclerView newsRecyclerView;
    NewsRecyclerAdapter newsAdapter;
    ImageView imgNoConnection;
    LinearLayout layoutNoConnection;
    Button btnTryConnection;
    ProgressBar progressBar;
    List<News> newsList;
    PrefManager prefManager;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = PrefManager.getInstance(getContext());
        if (savedInstanceState != null){
            newsList = savedInstanceState.getParcelableArrayList(KEY_LIST_NEWS);
        }

        if (prefManager.getListNews() != null) {
            newsList = prefManager.getListNews();
        }

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        //progressBar.setIndeterminateDrawable(new FoldingCirclesDrawable.Builder(getContext()).build());
        btnTryConnection = (Button) view.findViewById(R.id.btn_try_connection);
        btnTryConnection.setOnClickListener(this);
        newsRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_news);
        layoutNoConnection = (LinearLayout) view.findViewById(R.id.layout_no_connection);
        imgNoConnection = (ImageView) view.findViewById(R.id.img_no_connection);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        newsRecyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        accessFeed();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // listener for btn try connection
    @Override
    public void onClick(View v) {
        accessFeed();
    }

    @Override
    public void onRecyclerClick(News news) {
        onSaveInstanceState(new Bundle());
        Intent i = new Intent(getContext(), NewsActivity.class);
        i.putExtra(KEY_SINGLE_NEWS, news);
        startActivity(i);
    }

    public void accessFeed() {
        progressBar.setVisibility(View.VISIBLE);
        if (newsList != null){
            hideViews();
            setUpAdapter(newsList);
            return;
        }
        if (isConnectToInternet()) {
            //http://www.bantenhits.com/mega-pilgub/read?limit=14&format=feed
            //http://www.pilarbanten.com/index.php/pilgub.feed
            new GetRssNewsTask().execute("http://www.bantenhits.com/mega-pilgub/read?limit=14&format=feed",
                    "http://www.pilarbanten.com/index.php/pilgub.feed");
        }
        else {
            progressBar.setVisibility(View.GONE);
            layoutNoConnection.setVisibility(View.VISIBLE);
        }
    }

    class GetRssNewsTask extends AsyncTask<String,Void, List<News>> {

        @Override
        protected List<News> doInBackground(String... params) {
            List<News> containerNews = new ArrayList<>();
            for (String s : params.clone()) {
                List<News> newses = getNews(s);
                if (newses != null && !newses.isEmpty()) {
                    containerNews.addAll(newses);
                }
            }
            return containerNews;
        }

        @Override
        protected void onPostExecute(List<News> s) {
            if (s != null && !s.isEmpty()) {
                newsList = s;
                prefManager.setListOfNews(newsList);
                hideViews();
                setUpAdapter(s);
            }
        }
    }

    public void setUpAdapter(List<News> s){
        newsAdapter = new NewsRecyclerAdapter(getContext(), s, this);
        newsRecyclerView.setAdapter(newsAdapter);
    }

    public boolean isConnectToInternet(){
        final ConnectivityManager connectivityManager = ((ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public List<News> getNews(String urlParam) {
        List<News> news = new ArrayList<>();
        try {
            URL url = new URL(urlParam);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            // We will get the XML from an input stream
            InputStream is = getInputStream(url);
            if (is ==null)
                return null;

            xpp.setInput(is, "UTF_8");

        /* We will parse the XML content looking for the "<title>" tag which appears inside the "<item>" tag.
         * However, we should take in consideration that the rss feed name also is enclosed in a "<title>" tag.
         * As we know, every feed begins with these lines: "<channel><title>Feed_Name</title>...."
         * so we should skip the "<title>" tag which is a child of "<channel>" tag,
         * and take in consideration only "<title>" tag which is a child of "<item>"
         *
         * In order to achieve this, we will make use of a boolean variable.
         */
            boolean insideItem = false;

            // Returns the type of current event: START_TAG, END_TAG, etc..
            int eventType = xpp.getEventType();
            News berita = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = true;
                        berita = new News();
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (insideItem) {
                            berita.setTitle(xpp.nextText());
                        }
                    } else if (xpp.getName().equalsIgnoreCase("link")) {
                        if (insideItem) {
                            berita.setLink(xpp.nextText());
                        }
                    }else if(xpp.getName().equalsIgnoreCase("guid")){
                        if(insideItem){
                            berita.setGuid(xpp.nextText());
                        }
                    }else if(xpp.getName().equalsIgnoreCase("description")){
                        if (insideItem){
                            String desc = xpp.nextText();
                            MyHtmlParser htmlParser = MyHtmlParser.newInstance(desc);
                            String imageUrl = htmlParser.getNews().getImageUrl();
                            String descrtiption = htmlParser.getNews().getDescription();
                            berita.setImageUrl(imageUrl);
                            berita.setDescription(descrtiption);
                        }
                    }else if(xpp.getName().equalsIgnoreCase("author")){
                        if(insideItem){
                            berita.setAuthor(xpp.nextText());
                        }
                    }else if(xpp.getName().equalsIgnoreCase("pubdate")){
                        if(insideItem){
                            berita.setDate(xpp.nextText());
                        }
                    }


                }else if(eventType== XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                    if (berita != null){
                        news.add(berita);
                    }

                    insideItem=false;
                    berita = null;
                }

                eventType = xpp.next(); //move to next element
            }

            return news;

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    public void hideViews(){
        layoutNoConnection.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

}
