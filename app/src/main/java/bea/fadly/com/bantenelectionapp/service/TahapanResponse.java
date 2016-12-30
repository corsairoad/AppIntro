package bea.fadly.com.bantenelectionapp.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bea.fadly.com.bantenelectionapp.domain.TahapanPilkada;

/**
 * Created by DIGIKOM-EX4 on 12/5/2016.
 */

public class TahapanResponse {

    @SerializedName("tahapan_pilkada")
    List<TahapanPilkada> listTahapan;

    public List<TahapanPilkada> getListTahapan() {
        return listTahapan;
    }

    public void setListTahapan(List<TahapanPilkada> listTahapan) {
        this.listTahapan = listTahapan;
    }
}
