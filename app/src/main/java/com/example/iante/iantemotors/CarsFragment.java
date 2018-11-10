package com.example.iante.iantemotors;


import android.app.Activity;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class CarsFragment extends Fragment {

    private String CarId;
    EditText inputmake, inputmodel, inputEngine, inputReg;
    Button btn_add_car;
    FirebaseDatabase database;
    DatabaseReference ref;
     Cars cars;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_cars, container, false);
    }
        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            //you can set the title for your toolbar here for different fragments different titles
            getActivity().setTitle("My cars");



        inputmake = (EditText) inputmake.findViewById(R.id.make);
        inputmodel = (EditText) inputmodel.findViewById(R.id.model);
        inputEngine = (EditText) inputEngine.findViewById(R.id.Engine);
        inputReg = (EditText) inputReg.findViewById(R.id.Reg);
        btn_add_car = (Button) btn_add_car.findViewById(R.id.btn_add_car);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("cars");

        database.getReference("94054mycars").setValue("my cars");
        database.getReference("94054mycars").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, "my cars updated");
                String myCars = dataSnapshot.getValue(String.class);
                getSupportActionBar().setTitle(myCars);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to update my cars.", error.toException());
            }


        });
        btn_add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String make = inputmake.getText().toString();
                String model = inputmodel.getText().toString();
                String Engine = inputEngine.getText().toString();
                String Reg = inputReg.getText().toString();

                if (TextUtils.isEmpty(CarId)) {
                    createCar(model, make, Engine, Reg);
                }
            }
        });


    }

    private MenuItem getSupportActionBar() {
        return null;
    }


    private void createCar(String model, String make, String Engine, String Reg) {
        if (TextUtils.isEmpty(CarId)) {
            CarId = ref.push().getKey();
        }
        Cars cars = new Cars(make, model, Engine, Reg);

        ref.child(CarId).setValue(cars);

        addCarChangeListener();
    }

    private void addCarChangeListener() {
        ref.child(CarId).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Cars cars = dataSnapshot.getValue(Cars.class);
                if (cars == null) {
                    Log.e(TAG, "Car data is null!");
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
