package bea.fadly.com.bantenelectionapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bea.fadly.com.bantenelectionapp.R;
import bea.fadly.com.bantenelectionapp.domain.VisiMisi;
import bea.fadly.com.bantenelectionapp.service.ApiClient;

/**
 * Created by DIGIKOM-EX4 on 12/2/2016.
 */

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<VisiMisi> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private int key;

    public TestRecyclerViewAdapter(List<VisiMisi> contents, int key) {
        this.contents = contents;
        this.key = key;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_CELL;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_CELL:
                break;
        }
        TextView textView = (TextView)  holder.itemView.findViewById(R.id.text_content);
        VisiMisi visiMisi  = contents.get(position);
        switch (key){
            case ApiClient.KEY_VISI_WH:
            case ApiClient.KEY_VISI_RANO:
                textView.setText(visiMisi.getVisi());
                break;
            case ApiClient.KEY_MISI_WH:
            case ApiClient.KEY_MISI_RANO:
                textView.setText(visiMisi.getMisi());
                break;
            default:
                break;
        }
    }
}
