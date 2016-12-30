package bea.fadly.com.bantenelectionapp.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bea.fadly.com.bantenelectionapp.domain.Program;

/**
 * Created by DIGIKOM-EX4 on 12/3/2016.
 */

public class ProgramResponse {

    @SerializedName(value = "program_unggulan", alternate = "prioritas_program")
    List<Program> programs;

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }
}
