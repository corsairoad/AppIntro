package bea.fadly.com.bantenelectionapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by DIGIKOM-EX4 on 11/23/2016.
 */

public class IndoDateParser {

    private static final String[] haris = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};
    private static final String[] bulans = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September",
                                                "Novomber", "Desember"};

    public static String convertToIndoFormat(String date){
        String mHari = null;
        String[] subDate = date.split(",");
        if (subDate[0].equalsIgnoreCase("sun")){
            mHari = haris[6];
        }else if(subDate[0].equalsIgnoreCase("mon")){
            mHari = haris[0];
        }else if(subDate[0].equalsIgnoreCase("Tue")){
            mHari = haris[1];
        }else if(subDate[0].equalsIgnoreCase("Wed")) {
            mHari = haris[2];
        }else if(subDate[0].equalsIgnoreCase("Thu")) {
            mHari = haris[3];
        }else if (subDate[0].equalsIgnoreCase("Fri")) {
            mHari = haris[4];
        }else if (subDate[0].equalsIgnoreCase("sat")) {
            mHari = haris[5];
        }
        if (mHari != null) {
            subDate[0] = mHari;
            return new StringBuilder(subDate[0]).append(", " + subDate[1]).toString();
        }
        return null;
    }
}
