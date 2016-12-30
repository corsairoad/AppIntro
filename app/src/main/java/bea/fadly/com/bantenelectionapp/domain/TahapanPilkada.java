package bea.fadly.com.bantenelectionapp.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DIGIKOM-EX4 on 12/5/2016.
 */

public class TahapanPilkada {

    @SerializedName("id")
    private int id;
    @SerializedName("tahapan_pilkada")
    private String deskripsi;
    @SerializedName("awal")
    private String awal;
    @SerializedName("akhir")
    private String akhir;

    public TahapanPilkada(int id, String deskripsi, String awal, String akhir) {
        this.id = id;
        this.deskripsi = deskripsi;
        this.awal = awal;
        this.akhir = akhir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getAwal() {
        return awal;
    }

    public void setAwal(String awal) {
        this.awal = awal;
    }

    public String getAkhir() {
        return akhir;
    }

    public void setAkhir(String akhir) {
        this.akhir = akhir;
    }

    @Override
    public String toString() {
        return getDeskripsi() + "\n" + getAkhir() + "-" + getAkhir();
    }
}
