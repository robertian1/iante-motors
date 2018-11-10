package com.example.iante.iantemotors;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;


public class FuelFragment extends Fragment {
    private String FuelId;
    EditText inputOdometer, inputLitres, inputAmount;
    Button btn_fuel;
    FirebaseDatabase database;
    DatabaseReference ref;
    Fuel fuel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_fuel, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Fuel");

        inputOdometer = (EditText) inputOdometer.findViewById(R.id.odometer);
        inputLitres = (EditText) inputLitres.findViewById(R.id.litres);
        inputAmount = (EditText) inputAmount.findViewById(R.id.amount);
        btn_fuel = (Button) btn_fuel.findViewById(R.id.btn_fuel);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("fuel");
        database.getReference("94054mycars").setValue("Fuel");
        database.getReference("94054mycars").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, "Fuel updated");
                String fuel = dataSnapshot.getValue(String.class);
                getSupportActionBar().setTitle(fuel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to update fuel.", error.toException());
            }


        });
        btn_fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String odometer = inputOdometer.getText().toString();
                String litres = inputLitres.getText().toString();
                String amount = inputAmount.getText().toString();


                if (TextUtils.isEmpty(FuelId)) {
                    createFuel(odometer, litres, amount);
                }
            }
        });


    }

    private MenuItem getSupportActionBar() {
        return null;
    }


    private void createFuel(String odometer, String litres, String amount) {
        if (TextUtils.isEmpty(FuelId)) {
            FuelId = ref.push().getKey();
        }
        Fuel fuel = new Fuel(odometer, litres, amount);

        ref.child(FuelId).setValue(fuel);

        addCarChangeListener();
    }

    private void addCarChangeListener() {
        ref.child(FuelId).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Cars cars = dataSnapshot.getValue(Cars.class);
                if (cars == null) {
                    Log.e(TAG, "Fuel data is null!");
                    return;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to read ", error.toException());
            }
        });
    }
    }


