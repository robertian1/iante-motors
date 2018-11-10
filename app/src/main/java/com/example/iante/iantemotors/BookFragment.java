package com.example.iante.iantemotors;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment  {
    private String BookId;
    EditText inputfull_name, inputemail, inputtelphone, inputmodel,inputReg,inputDate;
    Button btn_book;
    FirebaseDatabase database;
    DatabaseReference ref;
    Book book;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_book, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Book car");


        inputfull_name = (EditText) inputfull_name.findViewById(R.id.full_name);
        inputmodel = (EditText) inputmodel.findViewById(R.id.model);
        inputemail = (EditText) inputemail.findViewById(R.id.email);
        inputtelphone = (EditText) inputtelphone.findViewById(R.id.telphone);
        inputDate = (EditText) inputDate.findViewById(R.id.tvDate);
        inputReg = (EditText) inputReg.findViewById(R.id.Reg);
        btn_book = (Button) btn_book.findViewById(R.id.btn_book);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("cars");


        database.getReference("94054mycars").setValue("Book car");
        database.getReference("94054mycars").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, "Book car updated");
                String myCars = dataSnapshot.getValue(String.class);
                getSupportActionBar().setTitle(myCars);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to update Book car.", error.toException());
            }


        });
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String make = inputfull_name.getText().toString();
                String model = inputmodel.getText().toString();
                String email = inputemail.getText().toString();
                String Reg = inputReg.getText().toString();
                String telphone =  inputtelphone.getText().toString();
                String Date =  inputDate.getText().toString();


                if (TextUtils.isEmpty(BookId)) {
                    createCar(model, make, email, Reg,telphone,Date);
                }
            }
        });


    }

    private MenuItem getSupportActionBar() {
        return null;
    }


    private void createCar(String model, String make, String email, String Reg,String telphone,String Date) {
        if (TextUtils.isEmpty(BookId)) {
            BookId = ref.push().getKey();
        }
        Book book = new Book(make, model, email, Reg,telphone,Date);

        ref.child(BookId).setValue(book);

        addCarChangeListener();
    }

    private void addCarChangeListener() {
        ref.child(BookId).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Book book = dataSnapshot.getValue(Book.class);
                if (book == null) {
                    Log.e(TAG, "Book data is null!");
                    return;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to read Book car", error.toException());
            }
        });
    }
}