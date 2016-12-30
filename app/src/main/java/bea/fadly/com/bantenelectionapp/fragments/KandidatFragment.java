package bea.fadly.com.bantenelectionapp.fragments;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bea.fadly.com.bantenelectionapp.ProfilKandidatActivity;
import bea.fadly.com.bantenelectionapp.ProfileKandidatActivity;
import bea.fadly.com.bantenelectionapp.R;
import bea.fadly.com.bantenelectionapp.adapter.KandidatRecyclerAdapter;
import bea.fadly.com.bantenelectionapp.domain.Cagub;
import bea.fadly.com.bantenelectionapp.service.ApiClient;
import bea.fadly.com.bantenelectionapp.service.ApiInterface;
import bea.fadly.com.bantenelectionapp.service.ProfileCalonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KandidatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KandidatFragment extends Fragment implements KandidatRecyclerAdapter.OnClickKandidatListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String KEY_SELECTED_KANDIDAT = "com.fadly.kandidat.terpilih";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TypedArray typedArray;
    TypedArray typedArrayNama;
    RecyclerView recyclerView;
    KandidatRecyclerAdapter adapter;

    public KandidatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KandidatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KandidatFragment newInstance(String param1, String param2) {
        KandidatFragment fragment = new KandidatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typedArray = getResources().obtainTypedArray(R.array.kandidat_array);
        typedArrayNama = getResources().obtainTypedArray(R.array.nama_kandidat_array);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_kandidat, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_kandidat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new KandidatRecyclerAdapter(getContext(), typedArray, typedArrayNama, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickKandidat(int position) {
        Intent i = new Intent(getContext(), ProfileKandidatActivity.class);
        i.putExtra(KEY_SELECTED_KANDIDAT, position);

        startActivity(i);
    }
}
