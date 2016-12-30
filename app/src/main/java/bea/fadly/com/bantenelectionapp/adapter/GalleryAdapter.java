package bea.fadly.com.bantenelectionapp.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import bea.fadly.com.bantenelectionapp.R;
import bea.fadly.com.bantenelectionapp.domain.Image;

/**
 * Created by DIGIKOM-EX4 on 11/28/2016.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private List<Image> images;
    private Context mContext;
    private TypedArray typedArray;
    private ClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            thumbnail.setFocusable(true);
        }

        public void setClickListener(ClickListener clickListener, int posistion) {
            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(thumbnail, posistion);
                }
            });
        }

    }


    public GalleryAdapter(Context context, List<Image> images, TypedArray typedArray, ClickListener clickListener) {
        mContext = context;
        this.images = images;
        this.typedArray = typedArray;
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_thumbnail, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Glide.with(mContext).load(typedArray.getString(position))
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);
        holder.setClickListener(clickListener, position);
    }

    @Override
    public int getItemCount() {
        return typedArray.length();
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }


}
