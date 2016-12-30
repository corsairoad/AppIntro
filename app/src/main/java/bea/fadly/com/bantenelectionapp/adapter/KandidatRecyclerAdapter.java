package bea.fadly.com.bantenelectionapp.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import bea.fadly.com.bantenelectionapp.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by DIGIKOM-EX4 on 11/30/2016.
 */

public class KandidatRecyclerAdapter extends RecyclerView.Adapter<KandidatRecyclerAdapter.ViewHolder> {

    Context context;
    TypedArray typedArray;
    TypedArray typedArrayNama;
    TypedArray typedArrayPartaiWh;
    TypedArray typedArrayPartaiRano;
    TypedArray typedArrayColorWh;
    TypedArray typedArrayColorRano;
    OnClickKandidatListener onClickKandidatListener;


    public interface OnClickKandidatListener{
         void onClickKandidat(int position);
    }

    public KandidatRecyclerAdapter(Context context, TypedArray typedArray, TypedArray typedArrayNama, OnClickKandidatListener listener) {
        this.context = context;
        this.typedArray = typedArray;
        this.typedArrayNama = typedArrayNama;
        this.onClickKandidatListener = listener;
        typedArrayPartaiWh = context.getResources().obtainTypedArray(R.array.image_partai_array_wh);
        typedArrayPartaiRano = context.getResources().obtainTypedArray(R.array.image_partai_array_rano);
        typedArrayColorWh = context.getResources().obtainTypedArray(R.array.color_partai_wh);
        typedArrayColorRano = context.getResources().obtainTypedArray(R.array.color_partai_rano);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_kandidat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(typedArray.getResourceId(position, -1))
                .into(holder.imgKandidat);
        holder.textKandidat.setText(typedArrayNama.getText(position));
        holder.textKandidat.setContentDescription(typedArrayNama.getText(position));
        holder.createImageParty(position);
        holder.setListener(position, onClickKandidatListener);
    }

    @Override
    public int getItemCount() {
        return typedArray.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgKandidat;
        TextView textKandidat;
        CardView cardView;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view_kandidat);
            cardView.setFocusable(true);
            imgKandidat = (ImageView) cardView.findViewById(R.id.thumbnail);
            textKandidat = (TextView) cardView.findViewById(R.id.title);
            linearLayout = (LinearLayout) cardView.findViewById(R.id.layout_partai);
        }

        public void createImageParty(int position) {
            TypedArray typedArray = null;
            TypedArray typedArrayColor = null;
            switch (position) {
                case 0:
                    typedArray = typedArrayPartaiWh;
                    typedArrayColor = typedArrayColorWh;
                    break;
                case 1:
                    typedArray = typedArrayPartaiRano;
                    typedArrayColor = typedArrayColorRano;
                    break;
                default:
                    break;
            }
            create(typedArray, typedArrayColor);
        }

        public void create(TypedArray typedArray, TypedArray typedArrayColor) {
            if (typedArray == null || typedArrayColor == null)
                return;
            try{
                for(int a = 0; a<typedArray.length(); a++) {
                    CircleImageView imageView = new CircleImageView(context);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(75,75);

                    imageView.setPadding(0,0,16,0);
                    imageView.setLayoutParams(params);
                    imageView.setBorderColor(typedArrayColor.getColor(a, -1));
                    imageView.setImageResource(typedArray.getResourceId(a, -1));

                    linearLayout.addView(imageView);
                }
            }catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

        }

        public void setListener(int position, OnClickKandidatListener listener) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickKandidat(position);
                }
            });
            imgKandidat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickKandidat(position);
                }
            });

            textKandidat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickKandidat(position);
                }
            });

        }
    }
}
