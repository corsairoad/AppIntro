package bea.fadly.com.bantenelectionapp.service;

import bea.fadly.com.bantenelectionapp.domain.TahapanPilkada;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by DIGIKOM-EX4 on 12/1/2016.
 */

public interface ApiInterface {

    @GET("profile_calon")
    Call<ProfileCalonResponse> getProfileKandidat();
    @GET("{visimisi}")
    Call<VisiMisiResponse> getVisiMisi(@Path("visimisi") String param);
    @GET("{program}")
    Call<ProgramResponse> getProgram(@Path("program") String programParam);
    @GET("partisipasi_pilkada")
    Call<PartsPilkadaResponse> getPartisipasiPilkada();
    @GET("tahapan_pilkada")
    Call<TahapanResponse> getTahapanPilkada();
}
