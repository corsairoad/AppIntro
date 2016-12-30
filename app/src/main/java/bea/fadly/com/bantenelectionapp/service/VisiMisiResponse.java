package bea.fadly.com.bantenelectionapp.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bea.fadly.com.bantenelectionapp.domain.VisiMisi;

/**
 * Created by DIGIKOM-EX4 on 12/1/2016.
 */

public class VisiMisiResponse {

    @SerializedName(value = "visi_misi_wahidin_andika", alternate = "visi_misi_rano_embay")
    List<VisiMisi> listVisiMisi;

    public List<VisiMisi> getListVisiMisi() {
        return listVisiMisi;
    }

    public void setListVisiMisi(List<VisiMisi> listVisiMisi) {
        this.listVisiMisi = listVisiMisi;
    }
}
