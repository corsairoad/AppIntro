package bea.fadly.com.bantenelectionapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import java.util.List;
import bea.fadly.com.bantenelectionapp.R;
import bea.fadly.com.bantenelectionapp.domain.News;
import bea.fadly.com.bantenelectionapp.service.MySingleton;

/**
 * Created by DIGIKOM-EX4 on 11/22/2016.
 */

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<News> news;
    private OnRecyclerItemClickListener mListener;

    public interface OnRecyclerItemClickListener {
        void onRecyclerClick(News news);
    }

    public NewsRecyclerAdapter(Context context, List<News> news, OnRecyclerItemClickListener listener) {
        this.context = context;
        this.news = news;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.imageView.setImageUrl(news.get(position).getImageUrl(), MySingleton.getInstance(context).getImageLoader());
        //holder.title.setText(news.get(position).getTitle());
        //holder.pubDate.setText(news.get(position).getDate());
        holder.bind(news.get(position), mListener );

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public NetworkImageView imageView;
        public TextView title;
        public TextView pubDate;
        public CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (NetworkImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.titleNews);
            pubDate = (TextView) itemView.findViewById(R.id.txt_pubdate);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            cardView.setFocusable(true);
        }

        public void bind(final News news, final OnRecyclerItemClickListener listener) {
            title.setText(news.getTitle());
            title.setContentDescription(news.getTitle());
            imageView.setImageUrl(news.getImageUrl(), MySingleton.getInstance(context).getImageLoader());
            pubDate.setText(news.getDate());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRecyclerClick(news);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRecyclerClick(news);
                }
            });
        }
    }


}


