package com.example.iante.iantemotors;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


import com.firebase.client.Firebase;

import static android.support.constraint.Constraints.TAG;
import static android.widget.Toast.LENGTH_SHORT;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordsFragment extends Fragment {
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Records> list;
    MyAdapter adapter;

    public RecordsFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_records, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Records");

        recyclerView = (RecyclerView) recyclerView.findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        reference = FirebaseDatabase.getInstance().getReference().child("94054mycars");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Records>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Records p = dataSnapshot1.getValue(Records.class);
                    list.add(p);
                }
                adapter = new MyAdapter( getActivity(), list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Something went wrong.", databaseError.toException());
            }
        });

    }
}
