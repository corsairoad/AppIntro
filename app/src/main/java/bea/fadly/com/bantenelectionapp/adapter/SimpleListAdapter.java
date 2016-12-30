package bea.fadly.com.bantenelectionapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bea.fadly.com.bantenelectionapp.R;
import bea.fadly.com.bantenelectionapp.domain.PratisipasiPilkada;

/**
 * Created by DIGIKOM-EX4 on 12/5/2016.
 */

public class SimpleListAdapter extends ArrayAdapter<PratisipasiPilkada> {


    public SimpleListAdapter(Context context, int resource, List<PratisipasiPilkada> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PratisipasiPilkada pratisipasiPilkada = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        }
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(pratisipasiPilkada.getNamaWilaha());
        return convertView;
    }
}
