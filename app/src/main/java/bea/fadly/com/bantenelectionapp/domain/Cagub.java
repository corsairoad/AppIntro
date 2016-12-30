package bea.fadly.com.bantenelectionapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DIGIKOM-EX4 on 12/1/2016.
 */

public class Cagub implements Parcelable {

    @SerializedName("nama_kepala_daerah")
    private String nama;
    @SerializedName("gender_kepala_daerah")
    private String gender;
    @SerializedName("tempat_lahir_kepala")
    private String tempatLahir;
    @SerializedName("tanggal_lahir_kepala")
    private String tanggalLahir;
    @SerializedName("pekerjaan_kepala")
    private String pekerjaan;

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

    @SerializedName("jumlah_dukungan")
    private int jumlahDukungan;
    @SerializedName("perolehan_suara")
    private int perOlehanSuara;
    @SerializedName("id")
    private int id;

    public Cagub(String nama, String gender, String tempatLahir, String tanggalLahir, String pekerjaan, String namaWakil, String genderWakil, String tempatLahirWakil, String tanggalLahirWakil, String pekerjaanWakil, int jumlahDukungan, int perOlehanSuara, int id) {
        this.nama = nama;
        this.gender = gender;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.pekerjaan = pekerjaan;
        this.namaWakil = namaWakil;
        this.genderWakil = genderWakil;
        this.tempatLahirWakil = tempatLahirWakil;
        this.tanggalLahirWakil = tanggalLahirWakil;
        this.pekerjaanWakil = pekerjaanWakil;
        this.jumlahDukungan = jumlahDukungan;
        this.perOlehanSuara = perOlehanSuara;
        this.id = id;
    }

    protected Cagub(Parcel in) {
        nama = in.readString();
        gender = in.readString();
        tempatLahir = in.readString();
        tanggalLahir = in.readString();
        pekerjaan = in.readString();
        namaWakil = in.readString();
        genderWakil = in.readString();
        tempatLahirWakil = in.readString();
        tanggalLahirWakil = in.readString();
        pekerjaanWakil = in.readString();
        jumlahDukungan = in.readInt();
        perOlehanSuara = in.readInt();
        id = in.readInt();
    }

    public static final Creator<Cagub> CREATOR = new Creator<Cagub>() {
        @Override
        public Cagub createFromParcel(Parcel in) {
            return new Cagub(in);
        }

        @Override
        public Cagub[] newArray(int size) {
            return new Cagub[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(gender);
        dest.writeString(tempatLahir);
        dest.writeString(tanggalLahir);
        dest.writeString(pekerjaan);
        dest.writeString(namaWakil);
        dest.writeString(genderWakil);
        dest.writeString(tempatLahirWakil);
        dest.writeString(tanggalLahirWakil);
        dest.writeString(pekerjaanWakil);
        dest.writeInt(jumlahDukungan);
        dest.writeInt(perOlehanSuara);
        dest.writeInt(id);
    }

    public String getTtlCagub(){
        return getTempatLahir() + ", " + getTanggalLahir();
    }

    public String getTtlCawagub() {
        return getTempatLahirWakil() + ", " + getTanggalLahirWakil();
    }
}
