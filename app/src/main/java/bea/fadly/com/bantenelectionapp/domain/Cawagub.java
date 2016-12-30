package bea.fadly.com.bantenelectionapp.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DIGIKOM-EX4 on 12/1/2016.
 */

public class Cawagub extends HasilPemilu {

    @SerializedName("nama_wakil_kepala_daerah")
    private String namaWakil;
    @SerializedName("gender_wakil_kepala_daerah")
    private String genderWakil;
    @SerializedName("tempat_lahir_wakil")
    private String tempatLahirWakil;
    @SerializedName("tanggal_lahir_wakil")
    private String tanggalLahirWakil;
    @SerializedName("pekerjaan_wakilkada")
    private String pekerjaanWakil;


    public Cawagub() {
        super();
    }

    public Cawagub(int jumlahDukungan, int perOlehanSuara, int id, String namaWakil, String genderWakil, String tempatLahirWakil, String tanggalLahirWakil, String pekerjaanWakil, int id1, int jumlahDukungan1, int perOlehanSuara1) {
        super(jumlahDukungan, perOlehanSuara, id);
        this.namaWakil = namaWakil;
        this.genderWakil = genderWakil;
        this.tempatLahirWakil = tempatLahirWakil;
        this.tanggalLahirWakil = tanggalLahirWakil;
        this.pekerjaanWakil = pekerjaanWakil;

    }

    public String getNamaWakil() {
        return namaWakil;
    }

    public void setNamaWakil(String namaWakil) {
        this.namaWakil = namaWakil;
    }

    public String getGenderWakil() {
        return genderWakil;
    }

    public void setGenderWakil(String genderWakil) {
        this.genderWakil = genderWakil;
    }

    public String getTempatLahirWakil() {
        return tempatLahirWakil;
    }

    public void setTempatLahirWakil(String tempatLahirWakil) {
        this.tempatLahirWakil = tempatLahirWakil;
    }

    public String getTanggalLahirWakil() {
        return tanggalLahirWakil;
    }

    public void setTanggalLahirWakil(String tanggalLahirWakil) {
        this.tanggalLahirWakil = tanggalLahirWakil;
    }

    public String getPekerjaanWakil() {
        return pekerjaanWakil;
    }

    public void setPekerjaanWakil(String pekerjaanWakil) {
        this.pekerjaanWakil = pekerjaanWakil;
    }
}
