package bea.fadly.com.bantenelectionapp.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bea.fadly.com.bantenelectionapp.domain.Cagub;
import bea.fadly.com.bantenelectionapp.domain.Cawagub;
import bea.fadly.com.bantenelectionapp.domain.HasilPemilu;

/**
 * Created by DIGIKOM-EX4 on 12/1/2016.
 */

public class ProfileCalonResponse {

    @SerializedName("result")
    List<Cagub> profileCagubs;

    public List<Cagub> getProfileCagubs() {
        return profileCagubs;
    }

    public void setProfileCagubs(List<Cagub> profileCagubs) {
        this.profileCagubs = profileCagubs;
    }
}
