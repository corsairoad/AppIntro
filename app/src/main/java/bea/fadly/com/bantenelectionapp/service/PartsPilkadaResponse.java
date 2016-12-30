package bea.fadly.com.bantenelectionapp.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bea.fadly.com.bantenelectionapp.domain.PratisipasiPilkada;

/**
 * Created by DIGIKOM-EX4 on 12/5/2016.
 */

public class PartsPilkadaResponse {

    @SerializedName("partisipasi_pilkada")
    List<PratisipasiPilkada> results;

    public List<PratisipasiPilkada> getResults() {
        return results;
    }

    public void setResults(List<PratisipasiPilkada> results) {
        this.results = results;
    }
}
