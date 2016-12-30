package bea.fadly.com.bantenelectionapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vipul.hp_hp.timelineview.TimelineView;

import bea.fadly.com.bantenelectionapp.R;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView waktu;
    public TimelineView mTimelineView;

    public TimeLineViewHolder(View itemView, int viewType) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.text_desc_tahapan);
        waktu = (TextView) itemView.findViewById(R.id.text_waktu_tahapan);
        mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
        mTimelineView.initLine(viewType);
    }
}
