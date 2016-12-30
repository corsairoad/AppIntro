package bea.fadly.com.bantenelectionapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.vipul.hp_hp.timelineview.TimelineView;

import java.util.List;

import bea.fadly.com.bantenelectionapp.R;
import bea.fadly.com.bantenelectionapp.domain.TahapanPilkada;
import bea.fadly.com.bantenelectionapp.util.Orientation;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {

    private List<TahapanPilkada> mFeedList;
    private Context mContext;
    private Orientation mOrientation;

    public TimeLineAdapter(List<TahapanPilkada> feedList, Orientation orientation) {
        mFeedList = feedList;
        mOrientation = orientation;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext = parent.getContext();

        View view;

        if(mOrientation == Orientation.horizontal) {
            view = View.inflate(parent.getContext(), R.layout.item_timeline_horizontal, null);
        } else {
            view = View.inflate(parent.getContext(), R.layout.item_timeline, null);
        }

        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        TahapanPilkada timeLineModel = mFeedList.get(position);
        holder.name.setText(timeLineModel.getDeskripsi());
        holder.waktu.setText(timeLineModel.getAwal() + " s/d " + timeLineModel.getAkhir());

    }

    @Override
    public int getItemCount() {
        return (mFeedList!=null? mFeedList.size():0);
    }

}
