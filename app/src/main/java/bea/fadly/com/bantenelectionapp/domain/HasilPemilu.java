package bea.fadly.com.bantenelectionapp.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DIGIKOM-EX4 on 12/1/2016.
 */

public class HasilPemilu {

    @SerializedName("id")
    private int id;
    @SerializedName("jumlah_dukungan")
    private int jumlahDukungan;
    @SerializedName("perolehan_suara")
    private int perOlehanSuara;

    public HasilPemilu() {

    }

    public HasilPemilu(int jumlahDukungan, int perOlehanSuara, int id) {
        this.jumlahDukungan = jumlahDukungan;
        this.perOlehanSuara = perOlehanSuara;
        this.id = id;
    }

    public int getJumlahDukungan() {
        return jumlahDukungan;
    }

    public void setJumlahDukungan(int jumlahDukungan) {
        this.jumlahDukungan = jumlahDukungan;
    }

    public int getPerOlehanSuara() {
        return perOlehanSuara;
    }

    public void setPerOlehanSuara(int perOlehanSuara) {
        this.perOlehanSuara = perOlehanSuara;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
