package bea.fadly.com.bantenelectionapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

import bea.fadly.com.bantenelectionapp.R;
import bea.fadly.com.bantenelectionapp.adapter.ProfileAdapter;
import bea.fadly.com.bantenelectionapp.adapter.RecyclerProgramAdapter;
import bea.fadly.com.bantenelectionapp.adapter.TestRecyclerViewAdapter;
import bea.fadly.com.bantenelectionapp.domain.Cagub;
import bea.fadly.com.bantenelectionapp.domain.Program;
import bea.fadly.com.bantenelectionapp.domain.VisiMisi;
import bea.fadly.com.bantenelectionapp.service.ApiClient;
import bea.fadly.com.bantenelectionapp.service.ApiInterface;
import bea.fadly.com.bantenelectionapp.service.ProgramResponse;
import bea.fadly.com.bantenelectionapp.service.VisiMisiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {

    static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 100;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Object> mContentItems = new ArrayList<>();
    int param;
    private static final String KEY_FRAGMENT = "key_fragment";
    private static final String KEY_OBJECT_CAGUB = "key_cagub";
    ApiInterface apiInterface;
    Cagub cagub;

    public static RecyclerViewFragment newInstance(int key, Cagub cagub) {
       RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_FRAGMENT, key);
        bundle.putParcelable(KEY_OBJECT_CAGUB, cagub);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager;

        if (GRID_LAYOUT) {
            layoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            layoutManager = new LinearLayoutManager(getActivity());
        }
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        if (getArguments() != null) {
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            param = getArguments().getInt(KEY_FRAGMENT);
            cagub = getArguments().getParcelable(KEY_OBJECT_CAGUB);
            setUpContentBaseOnKey();
        }
    }

    private void setUpContentBaseOnKey() {
        switch (param) {
            case ApiClient.KEY_VISI_WH:
                case ApiClient.KEY_MISI_WH:
                    getVisiMisi(ApiClient.PARAM_VISI_MISI_WH);
                    break;
            case ApiClient.KEY_VISI_RANO:
                case ApiClient.KEY_MISI_RANO:
                getVisiMisi(ApiClient.PARAM_VISI_MISI_RANO);
                break;
            case ApiClient.KEY_PROGRAM_WH:
                getProgram(ApiClient.PARAM_PROGRAM_WH);
                break;
            case ApiClient.KEY_PROGRAM_RANO:
                getProgram(ApiClient.PARAM_PROGRAM_RANO);
                break;
            case ApiClient.KEY_CAGUB_WH:
                getProfileCagub(cagub, ApiClient.KEY_CAGUB_WH);
                break;
            case ApiClient.KEY_CAGUB_RANO:
                getProfileCagub(cagub, ApiClient.KEY_CAGUB_RANO);
                break;
            case ApiClient.KEY_CAWAGUB_ANDIKA:
                getProfileCagub(cagub, ApiClient.KEY_CAWAGUB_ANDIKA);
                break;
            case ApiClient.KEY_CAWAGUB_EMBAY:
                getProfileCagub(cagub, ApiClient.KEY_CAWAGUB_EMBAY);
                break;
            default:
                break;
        }
    }

    private void getProfileCagub(Cagub cagub, int param) {
        ProfileAdapter adapter = new ProfileAdapter(cagub, param);
        mRecyclerView.setAdapter(adapter);

    }

    private void getProgram(String param) {
        Call<ProgramResponse> call = apiInterface.getProgram(param);
        call.enqueue(new Callback<ProgramResponse>() {
            @Override
            public void onResponse(Call<ProgramResponse> call, Response<ProgramResponse> response) {
                if (response !=null || response.body() != null){
                    List<Program> programs = response.body().getPrograms();
                    RecyclerProgramAdapter adapter = new RecyclerProgramAdapter(programs,getContext(), param);
                    mRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ProgramResponse> call, Throwable t) {

            }
        });
    }

    private void getVisiMisi(String paramVisiMisi) {
        Call<VisiMisiResponse> call = apiInterface.getVisiMisi(paramVisiMisi);
        call.enqueue(new Callback<VisiMisiResponse>() {
            @Override
            public void onResponse(Call<VisiMisiResponse> call, Response<VisiMisiResponse> response) {
                if (response != null || response.body() !=null) {
                    List<VisiMisi> listVisiMisi = response.body().getListVisiMisi();
                    mAdapter = new TestRecyclerViewAdapter(listVisiMisi, param);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<VisiMisiResponse> call, Throwable t) {

            }
        });
    }

}
