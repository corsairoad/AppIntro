package bea.fadly.com.bantenelectionapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.PUT;

/**
 * Created by DIGIKOM-EX4 on 12/1/2016.
 */

public class ApiClient {

    public static final String BASE_URL = "http://api.pemiluapi.org/pilkada_banten/api/";
    private static Retrofit retrofit = null;

    public static final int KEY_VISI_WH = 11;
    public static final int KEY_VISI_RANO = 21;
    public static final int KEY_MISI_WH = 12;
    public static final int KEY_MISI_RANO = 22;
    public static final int KEY_PROGRAM_WH = 13;
    public static final int KEY_PROGRAM_RANO = 23;
    public static final int KEY_CAGUB_WH = 14;
    public static final int KEY_CAGUB_RANO = 24;
    public static final int KEY_CAWAGUB_EMBAY = 25;
    public static final int KEY_CAWAGUB_ANDIKA = 15;

    public static final String PARAM_VISI_MISI_WH = "visi_misi_wahidin_andika";
    public static final String PARAM_VISI_MISI_RANO = "visi_misi_rano_embay";
    public static final String PARAM_PROGRAM_WH = "prioritas_program_wahidin_andika";
    public static final String PARAM_PROGRAM_RANO = "program_unggulan";

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
