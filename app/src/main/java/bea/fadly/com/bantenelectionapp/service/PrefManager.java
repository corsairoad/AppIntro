package bea.fadly.com.bantenelectionapp.service;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

import bea.fadly.com.bantenelectionapp.domain.News;

/**
 * Created by DIGIKOM-EX4 on 12/4/2016.
 */

public class PrefManager {

    public static SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private Context context;
    public static final String PREF_NAME = "pref_manager";
    public static final String KEY_NEWS_LIST = "news_list";
    public static final String KEY_LAUNCH = "key_launch";
    Gson gson = new Gson();
    static PrefManager prefManager;

    public PrefManager(Context context) {
        this.context = context;
        if (preferences == null) {
            preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
            editor = preferences.edit();
        }
    }

    public boolean isFirstLaunch() {
        return preferences.getBoolean(KEY_LAUNCH, true);
    }

    public void setFirstLaunch(boolean param) {
        editor.putBoolean(KEY_LAUNCH, param);
        editor.commit();
    }

    public static PrefManager getInstance(Context context) {
        if (prefManager == null) {
            prefManager = new PrefManager(context);
        }
        return prefManager;
    }

    public void setListOfNews(List<News> newsList) {
        editor.clear();
        editor.commit();
        String listNews = gson.toJson(newsList);
        editor.putString(KEY_NEWS_LIST, listNews);
        editor.commit();
    }

    public List<News> getListNews() {
        String s = preferences.getString(KEY_NEWS_LIST, "");
        Type type = new TypeToken<List<News>>(){}.getType();
        List<News> newses = gson.fromJson(s, type);

        if (newses == null) {
            return null;
        }
        return newses;
    }

    public void clearListNews() {
        editor.putString(KEY_NEWS_LIST, "");
        editor.commit();
    }

}
