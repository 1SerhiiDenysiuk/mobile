package com.example.mobiletasks;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mobiletasks.ApiService;
import com.example.mobiletasks.ApplicationEx;
import com.example.mobiletasks.Equipment;
import com.example.mobiletasks.EquipmentAdapter;
import com.example.mobiletasks.NetworkChangeReceiver;
import com.example.mobiletasks.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataListFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout linearLayout;
    private EquipmentAdapter adapter;

    public DataListFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(Objects.requireNonNull(getView()));
        registerNetworkMonitoring();
        loadEquipment();
    }

    private void registerNetworkMonitoring() {
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        NetworkChangeReceiver receiver = new NetworkChangeReceiver(linearLayout);
        Objects.requireNonNull(getActivity()).registerReceiver(receiver, filter);
    }

    private void initViews(View root){
        recyclerView = root.findViewById(R.id.data_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        linearLayout = root.findViewById(R.id.linearLayout);
        swipeRefreshLayout = root.findViewById(R.id.data_list_swipe_refresh);
        setupSwipeToRefresh();
    }

    private void setupSwipeToRefresh(){
        swipeRefreshLayout.setOnRefreshListener(
                () -> {
                    loadEquipment();
                    swipeRefreshLayout.setRefreshing(false);
                }
        );
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    }

    private void loadEquipment(){
        swipeRefreshLayout.setRefreshing(true);
        final ApiService apiService = getApplicationEx().getApiService();
        final Call<List<Equipment>> call = apiService.getEquipment();

        call.enqueue(new Callback<List<Equipment>>() {
            @Override
            public void onResponse(final Call<List<Equipment>> call,
                                   final Response<List<Equipment>> response) {
                adapter = new EquipmentAdapter(getActivity(), response.body());
                recyclerView.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Equipment>> call, Throwable t) {
                Snackbar.make(linearLayout, R.string.failure, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private ApplicationEx getApplicationEx(){
        return ((ApplicationEx) Objects.requireNonNull(getActivity()).getApplication());
    }
}