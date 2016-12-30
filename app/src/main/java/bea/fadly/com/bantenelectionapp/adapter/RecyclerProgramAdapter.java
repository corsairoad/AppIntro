package bea.fadly.com.bantenelectionapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bea.fadly.com.bantenelectionapp.R;
import bea.fadly.com.bantenelectionapp.domain.Program;
import bea.fadly.com.bantenelectionapp.domain.VisiMisi;
import bea.fadly.com.bantenelectionapp.service.ApiClient;

/**
 * Created by DIGIKOM-EX4 on 12/3/2016.
 */

public class RecyclerProgramAdapter extends RecyclerView.Adapter<RecyclerProgramAdapter.ViewHolder> {

    List<Program> programs;
    Context context;
    String param;

    public RecyclerProgramAdapter(List<Program> programs, Context context, String param) {
        this.programs = programs;
        this.context = context;
        this.param = param;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card_small,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (param.equalsIgnoreCase(ApiClient.PARAM_PROGRAM_RANO))  {

            holder.textDesc.setText(programs.get(position+1).getDeskripis());

        }else {
            holder.textDesc.setText(programs.get(position).getProgramWh());
        }
    }

    @Override
    public int getItemCount() {
        if (param.equalsIgnoreCase(ApiClient.PARAM_PROGRAM_RANO))  {
            return programs.size()-1;
        }
        return programs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            textDesc = (TextView) itemView.findViewById(R.id.text_content);
        }
    }
}
