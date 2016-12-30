package bea.fadly.com.bantenelectionapp.domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;

import bea.fadly.com.bantenelectionapp.util.IndoDateParser;
import bea.fadly.com.bantenelectionapp.util.MyHtmlParser;

/**
 * Created by DIGIKOM-EX4 on 11/22/2016.
 */

public class News implements Parcelable {

    private int id;
    private String title;
    private String link;
    private String guid;
    private String description;
    private String imageUrl;
    private String author;
    private String date;

    public News() {
    }

    protected News(Parcel in) {
        id = in.readInt();
        title = in.readString();
        link = in.readString();
        guid = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        author = in.readString();
        date = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        String convertedDate = IndoDateParser.convertToIndoFormat(date.substring(0, 17));
        this.date = (convertedDate == null? "":convertedDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(link);
        dest.writeString(guid);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeString(author);
        dest.writeString(date);
    }
}
