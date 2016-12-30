package bea.fadly.com.bantenelectionapp.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DIGIKOM-EX4 on 12/3/2016.
 */

public class Program {

    @SerializedName("id")
    private int id;
    @SerializedName("deskripsi")
    private String deskripis;
    @SerializedName("prioritas_program")
    private String programWh;

    public Program(int id, String deskripis, String programWh) {
        this.id = id;
        this.deskripis = deskripis;
        this.programWh = programWh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeskripis() {
        return deskripis;
    }

    public void setDeskripis(String deskripis) {
        this.deskripis = deskripis;
    }

    public String getProgramWh() {
        return programWh;
    }

    public void setProgramWh(String programWh) {
        this.programWh = programWh;
    }
}
