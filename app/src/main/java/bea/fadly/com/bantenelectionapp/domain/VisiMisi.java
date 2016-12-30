package bea.fadly.com.bantenelectionapp.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DIGIKOM-EX4 on 12/1/2016.
 */

public class VisiMisi {

    @SerializedName("id")
    private int id;
    @SerializedName("visi")
    private String visi;
    @SerializedName("misi")
    private String misi;

    public VisiMisi(int id, String visi, String misi) {
        this.id = id;
        this.visi = visi;
        this.misi = misi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVisi() {
        return visi;
    }

    public void setVisi(String visi) {
        this.visi = visi;
    }

    public String getMisi() {
        return misi;
    }

    public void setMisi(String misi) {
        this.misi = misi;
    }
}
