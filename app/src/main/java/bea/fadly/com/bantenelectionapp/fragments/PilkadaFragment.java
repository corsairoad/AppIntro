package bea.fadly.com.bantenelectionapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bcgdv.asia.lib.ticktock.TickTockView;

import java.util.Calendar;

import bea.fadly.com.bantenelectionapp.PartsPilkadaActivity;
import bea.fadly.com.bantenelectionapp.R;
import bea.fadly.com.bantenelectionapp.TahapanActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PilkadaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PilkadaFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View viewTahapan;
    View viewParts;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public PilkadaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PilkadaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PilkadaFragment newInstance(String param1, String param2) {
        PilkadaFragment fragment = new PilkadaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pilkada, container, false);

        viewTahapan = view.findViewById(R.id.view_tahapan);
        viewTahapan.setTag(ARG_PARAM1);
        viewParts = view.findViewById(R.id.view_partisipasi);
        viewParts.setTag(ARG_PARAM2);
        viewParts.setOnClickListener(this);
        viewTahapan.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        switch (tag){
            case ARG_PARAM1:
                startActivity(new Intent(getContext(), TahapanActivity.class));
                break;
            case ARG_PARAM2:
                startActivity(new Intent(getContext(), PartsPilkadaActivity.class));
                break;

        }
    }
}
