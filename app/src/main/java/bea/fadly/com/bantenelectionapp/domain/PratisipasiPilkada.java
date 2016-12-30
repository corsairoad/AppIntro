package bea.fadly.com.bantenelectionapp.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DIGIKOM-EX4 on 12/5/2016.
 */

public class PratisipasiPilkada {

    @SerializedName("id")
    private int id;
    @SerializedName("kabupaten_kota")
    private String kabKota;
    @SerializedName("administrasi_daerah")
    private String admnDaerah;
    @SerializedName("data_pemilihan_lakilaki")
    private String dataPemilihLaki;
    @SerializedName("data_pemilihan_perempuan")
    private String dataPemilihPerempuan;
    @SerializedName("data_pemilihan_total")
    private String dataPemilihTotal;
    @SerializedName("penggunaan_hak_pilih_lakilaki")
    private String penggHakPilihLaki;
    @SerializedName("penggunaan_hak_pilih_perempuan")
    private String penggHakPilihPerempuan;
    @SerializedName("penggunaan_hak_pilih_total")
    private String penggHakPilihTotal;
    @SerializedName("persentase_pemilih")
    private String presentasePemilih;
    @SerializedName("suara_sah")
    private String suaraSah;
    @SerializedName("suara_tidak_sah")
    private String suaraTidakSah;
    @SerializedName("suara_total")
    private String suaraTotal;
    @SerializedName("jumlah_disabilitas")
    private String jumDisabilitas;
    @SerializedName("partisipasi_disabilitas")
    private String partsDisabilitas;
    @SerializedName("persentase_pengguna_disabilitas")
    private int presPenggDisabilitas;

    public PratisipasiPilkada(int id, String kabKota, String admnDaerah, String dataPemilihLaki, String dataPemilihPerempuan, String dataPemilihTotal, String penggHakPilihLaki, String penggHakPilihPerempuan, String penggHakPilihTotal, String presentasePemilih, String suaraSah, String suaraTidakSah, String suaraTotal, String jumDisabilitas, String partsDisabilitas, int presPenggDisabilitas) {
        this.id = id;
        this.kabKota = kabKota;
        this.admnDaerah = admnDaerah;
        this.dataPemilihLaki = dataPemilihLaki;
        this.dataPemilihPerempuan = dataPemilihPerempuan;
        this.dataPemilihTotal = dataPemilihTotal;
        this.penggHakPilihLaki = penggHakPilihLaki;
        this.penggHakPilihPerempuan = penggHakPilihPerempuan;
        this.penggHakPilihTotal = penggHakPilihTotal;
        this.presentasePemilih = presentasePemilih;
        this.suaraSah = suaraSah;
        this.suaraTidakSah = suaraTidakSah;
        this.suaraTotal = suaraTotal;
        this.jumDisabilitas = jumDisabilitas;
        this.partsDisabilitas = partsDisabilitas;
        this.presPenggDisabilitas = presPenggDisabilitas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKabKota() {
        return kabKota;
    }

    public void setKabKota(String kabKota) {
        this.kabKota = kabKota;
    }

    public String getAdmnDaerah() {
        return admnDaerah;
    }

    public void setAdmnDaerah(String admnDaerah) {
        this.admnDaerah = admnDaerah;
    }

    public String getDataPemilihLaki() {
        if (dataPemilihLaki == null) {
            return "0";
        }
        return dataPemilihLaki;
    }

    public void setDataPemilihLaki(String dataPemilihLaki) {
        this.dataPemilihLaki = dataPemilihLaki;
    }

    public String getDataPemilihPerempuan() {
        if (dataPemilihPerempuan == null) {
            return "0";
        }
        return dataPemilihPerempuan;
    }

    public void setDataPemilihPerempuan(String dataPemilihPerempuan) {
        this.dataPemilihPerempuan = dataPemilihPerempuan;
    }

    public String getDataPemilihTotal() {
        if (dataPemilihTotal == null) {
            return "0";
        }
        return dataPemilihTotal;
    }

    public void setDataPemilihTotal(String dataPemilihTotal) {
        this.dataPemilihTotal = dataPemilihTotal;
    }

    public String getPenggHakPilihLaki() {
        if (penggHakPilihLaki == null) {
            return "0";
        }
        return penggHakPilihLaki;
    }

    public void setPenggHakPilihLaki(String penggHakPilihLaki) {
        this.penggHakPilihLaki = penggHakPilihLaki;
    }

    public String getPenggHakPilihPerempuan() {
        if (penggHakPilihPerempuan == null) {
            return "0";
        }
        return penggHakPilihPerempuan;
    }

    public void setPenggHakPilihPerempuan(String penggHakPilihPerempuan) {
        this.penggHakPilihPerempuan = penggHakPilihPerempuan;
    }

    public String getPenggHakPilihTotal() {
        if (penggHakPilihTotal == null) {
            return "0";
        }
        return penggHakPilihTotal;
    }

    public void setPenggHakPilihTotal(String penggHakPilihTotal) {
        this.penggHakPilihTotal = penggHakPilihTotal;
    }

    public String getPresentasePemilih() {
        if (presentasePemilih == null) {
            return "0";
        }
        return presentasePemilih;
    }

    public void setPresentasePemilih(String presentasePemilih) {
        this.presentasePemilih = presentasePemilih;
    }

    public String getSuaraSah() {
        if (suaraSah == null) {
            return "0";
        }
        return suaraSah;
    }

    public void setSuaraSah(String suaraSah) {
        this.suaraSah = suaraSah;
    }

    public String getSuaraTidakSah() {
        if (suaraTidakSah == null) {
            return "0";
        }
        return suaraTidakSah;
    }

    public void setSuaraTidakSah(String suaraTidakSah) {
        this.suaraTidakSah = suaraTidakSah;
    }

    public String getSuaraTotal() {
        if (suaraTotal == null) {
            return "0";
        }
        return suaraTotal;
    }

    public void setSuaraTotal(String suaraTotal) {
        this.suaraTotal = suaraTotal;
    }

    public String getJumDisabilitas() {
        if (jumDisabilitas == null) {
            return "0";
        }
        return jumDisabilitas;
    }

    public void setJumDisabilitas(String jumDisabilitas) {
        this.jumDisabilitas = jumDisabilitas;
    }

    public String getPartsDisabilitas() {
        if (partsDisabilitas == null) {
            return "0";
        }
        return partsDisabilitas;
    }

    public void setPartsDisabilitas(String partsDisabilitas) {
        this.partsDisabilitas = partsDisabilitas;
    }

    public int getPresPenggDisabilitas() {
        return presPenggDisabilitas;
    }

    public void setPresPenggDisabilitas(int presPenggDisabilitas) {
        this.presPenggDisabilitas = presPenggDisabilitas;
    }

    public static List<String> getXaxisPartsPilkada() {
        List<String> list = new ArrayList<>();
        list.add("Data Pemilih");
        list.add("Penggunaan Hak Pilih");

        return list;
    }

    public String getNamaWilaha() {
        return getAdmnDaerah() + " " + getKabKota();
    }
}
