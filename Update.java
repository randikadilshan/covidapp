package com.example.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update extends AppCompatActivity {

    //Button submit;
    Button b;
    Covid covid;
    Vaccine vaccine;
    Quarentine quarentine;
    RadioButton cyes,cno, early_had;
    RadioButton Only_One,Fully_Vaccinated, Not_Yet;
    RadioButton qyes,qno;
    FirebaseDatabase database;
    DatabaseReference reference,reference1;
    int i = 0;
    EditText CampusID1;
    EditText ContactNo1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        b = findViewById(R.id.Submit);
        //submit=(Button)findViewById(R.id.Submit);


        covid = new Covid();
        cyes = findViewById(R.id.hyes);
        cno = findViewById(R.id.hno);
        early_had = findViewById(R.id.hearly);

        vaccine = new Vaccine();
        Only_One = findViewById(R.id.onlyone);
        Fully_Vaccinated= findViewById(R.id.fullyvaccine);
        Not_Yet = findViewById(R.id.notyet);

        quarentine = new Quarentine();
        qyes = findViewById(R.id.quyes);
        qno= findViewById(R.id.quno);


        CampusID1 = findViewById(R.id.campusID2);
        ContactNo1 = findViewById(R.id.contactNo);

        reference = database.getInstance().getReference().child("Users CovidInfo");
        reference1 = database.getInstance().getReference().child("Users VaccineInfo");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                /*if (snapshot.exists()){
                    i = (int)snapshot.getChildrenCount();
                }*/
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                /*if (snapshot.exists()){
                    i = (int)snapshot.getChildrenCount();
                }*/
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });


        b.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                String m1 = cyes.getText().toString();
                String m2 = cno.getText().toString();
                String m3 = early_had.getText().toString();
                String campusidst = CampusID1.getText().toString().trim();
                String contactno = ContactNo1.getText().toString().trim();

                if(contactno.length() != 0 && campusidst.length() != 0 && qyes.isChecked() || qno.isChecked() && cyes.isChecked() ||
                        cno.isChecked() || early_had.isChecked() && Only_One.isChecked() || Fully_Vaccinated.isChecked() || Not_Yet.isChecked()) {

                    covid.setName(CampusID1.getText().toString());
                    reference.child(String.valueOf(campusidst + "c")).setValue(covid);

                    covid.setContactNo(ContactNo1.getText().toString());
                    reference.child(String.valueOf(campusidst + "c")).setValue(covid);


                    if (cyes.isChecked()) {
                        covid.setcovid(m1);
                        reference.child(String.valueOf(campusidst + "c")).setValue(covid);
                    } else if (cno.isChecked()) {
                        covid.setcovid(m2);
                        reference.child(String.valueOf(campusidst + "c")).setValue(covid);
                    } else {
                        covid.setcovid(m3);
                        reference.child(String.valueOf(campusidst + "c")).setValue(covid);
                    }


                    String m4 = Only_One.getText().toString();
                    String m5 = Fully_Vaccinated.getText().toString();
                    String m6 = Not_Yet.getText().toString();


                    if (Only_One.isChecked()) {
                        vaccine.setvaccine(m4);
                        reference1.child(String.valueOf(campusidst + "v")).setValue(vaccine);
                    } else if (Fully_Vaccinated.isChecked()) {
                        vaccine.setvaccine(m5);
                        reference1.child(String.valueOf(campusidst + "v")).setValue(vaccine);
                    } else {
                        vaccine.setvaccine(m6);
                        reference1.child(String.valueOf(campusidst + "v")).setValue(vaccine);
                    }

                    String m7 = qyes.getText().toString();
                    String m8 = qno.getText().toString();



                    if (qyes.isChecked()) {
                        quarentine.setquarentine(m7);
                        reference1.child(String.valueOf(campusidst + "q")).setValue(quarentine);
                    } else {
                        quarentine.setquarentine(m8);
                        reference1.child(String.valueOf(campusidst + "q")).setValue(quarentine);
                    }


                    startActivity(new Intent(Update.this, MainActivity.class));
                }



            }
        });





        /*c.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                String m4 = Only_One.getText().toString();
                String m5 = Fully_Vaccinated.getText().toString();
                String m6 = Not_Yet.getText().toString();


                if(Only_One.isChecked()){
                    vaccine.setvaccine(m4);
                    reference.child(String.valueOf(i+2)).setValue(vaccine);
                }
                else if (Fully_Vaccinated.isChecked()){
                    vaccine.setvaccine(m5);
                    reference.child(String.valueOf(i+2)).setValue(vaccine);
                }
                else{
                    vaccine.setvaccine(m6);
                    reference.child(String.valueOf(i+2)).setValue(vaccine);
                }


            }
        });*/

        /*e.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                String m7 = qyes.getText().toString();
                String m8 = qno.getText().toString();

                if(qyes.isChecked()){
                    quarentine.setquarentine(m7);
                    reference.child(String.valueOf(i+3)).setValue(quarentine);
                }

                else{
                    quarentine.setquarentine(m8);
                    reference.child(String.valueOf(i+3)).setValue(quarentine);
                }


            }
        });*/
    }
}