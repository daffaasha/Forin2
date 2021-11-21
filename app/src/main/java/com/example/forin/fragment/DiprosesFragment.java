package com.example.forin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forin.DetailPesananActivity;
import com.example.forin.R;
import com.example.forin.adapter.FirebaseCashierAdapter;
import com.example.forin.datamodel.DBOrderDataModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DiprosesFragment extends Fragment {
    private RecyclerView rvPesanan;
    private DatabaseReference dbRef;
    private FirebaseCashierAdapter adapter;


    public DiprosesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diproses, container, false);
        dbRef = FirebaseDatabase.getInstance("https://forin-170e6-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference(DBOrderDataModel.class.getSimpleName());

        rvPesanan = view.findViewById(R.id.rv_pesanan);
        rvPesanan.setHasFixedSize(true);

        showFirebaseRecycler();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter.stopListening();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter.setOnItemClickCallback(dataModel -> {
            Intent moveToDetail = new Intent(requireActivity().getApplicationContext(), DetailPesananActivity.class);
            moveToDetail.putExtra(DetailPesananActivity.EXTRA_ITEM, dataModel);
            startActivity(moveToDetail);
        });
    }

    public void showFirebaseRecycler () {
        rvPesanan.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext()));

        FirebaseRecyclerOptions<DBOrderDataModel> options = new
                FirebaseRecyclerOptions.Builder<DBOrderDataModel>()
                .setQuery(dbRef, DBOrderDataModel.class)
                .build();
        adapter = new FirebaseCashierAdapter(options, 0);

        rvPesanan.setAdapter(adapter);
        adapter.startListening();
    }
}