package bea.fadly.com.bantenelectionapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bea.fadly.com.bantenelectionapp.R;
import bea.fadly.com.bantenelectionapp.domain.Cagub;
import bea.fadly.com.bantenelectionapp.service.ApiClient;
import de.hdodenhof.circleimageview.CircleImageView;
/**
 * Created by DIGIKOM-EX4 on 12/3/2016.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    Cagub cagub;
    int key;

    public ProfileAdapter(Cagub cagub, int param) {
        this.cagub = cagub;
        this.key = param;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(bea.fadly.com.bantenelectionapp.R.layout.list_item_card_big,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (key){
            case ApiClient.KEY_CAGUB_WH:
                holder.circleImageView.setImageResource(R.drawable.wahidin);
                holder.bind(cagub, ApiClient.KEY_CAGUB_WH);
                break;
            case ApiClient.KEY_CAGUB_RANO:
                holder.circleImageView.setImageResource(R.drawable.rano);
                holder.bind(cagub, ApiClient.KEY_CAGUB_RANO);
                break;
            case ApiClient.KEY_CAWAGUB_ANDIKA:
                holder.circleImageView.setImageResource(R.drawable.andika);
                holder.bind(cagub, ApiClient.KEY_CAWAGUB_ANDIKA);
                break;
            case ApiClient.KEY_CAWAGUB_EMBAY:
                holder.circleImageView.setImageResource(R.drawable.embay);
                holder.bind(cagub, ApiClient.KEY_CAWAGUB_EMBAY);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView textNama;
        TextView textGender;
        TextView textTtl;
        TextView textPekerjaan;

        public ViewHolder(View view) {
            super(view);
            circleImageView = (CircleImageView) view.findViewById(R.id.img_profile);
            textNama = (TextView) view.findViewById(R.id.text_nama);
            textGender = (TextView) view.findViewById(R.id.text_gender);
            textTtl = (TextView) view.findViewById(R.id.text_ttl);
            textPekerjaan = (TextView) view.findViewById(R.id.text_pekerjaan);
        }

        public void bind(Cagub cagub, int keyCagubWh) {
            switch (keyCagubWh){
                case ApiClient.KEY_CAGUB_WH:
                case ApiClient.KEY_CAGUB_RANO:
                    textNama.setText(cagub.getNama());
                    textGender.setText(cagub.getGender());
                    textTtl.setText(cagub.getTtlCagub());
                    textPekerjaan.setText(cagub.getPekerjaan());
                    break;
                case ApiClient.KEY_CAWAGUB_ANDIKA:
                case ApiClient.KEY_CAWAGUB_EMBAY:
                    textNama.setText(cagub.getNamaWakil());
                    textGender.setText(cagub.getGenderWakil());
                    textTtl.setText(cagub.getTtlCawagub());
                    textPekerjaan.setText(cagub.getPekerjaanWakil());
                    break;
            }
        }
    }
}
