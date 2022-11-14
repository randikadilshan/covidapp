package com.example.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference,reference1;

    private  String userID;
    private Button Logout,Info;


    TextView covidview;
    TextView vaccineview;
    TextView quarentineview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Logout=(Button)findViewById(R.id.signOut);
        Info=(Button)findViewById(R.id.btnAddInfo);







       /* reference = FirebaseDatabase.getInstance().getReference().child("Users").child(campusid + "c");
        reference.addValueEventListener(new ValueEventListener()

        {
            @Override
            public void onDataChange(DataSnapshot datasnapshot) {

                String hello = "Yes";
                String name = datasnapshot.child("DoYouHaveCovid").getValue().toString();
                switch (name)
                {
                    case "Yes":
                        covidview.setText(name + " You have covid");
                        break;

                    case "No":
                        covidview.setText(name + " You haven't covid");
                        break;

                    case "Early had":
                        covidview.setText("Yes, But not now");
                        break;

                }



            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        Logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this,MainActivity.class));
            }
        });

        Info.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this,Update.class));
            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView greetingTextView = (TextView)findViewById(R.id.admingreeting);
        final TextView fullNameTextView = (TextView)findViewById(R.id.adminnfullName);
        final TextView emailTextView = (TextView)findViewById(R.id.adminemailAddress);
        final TextView nicNoTextView = (TextView)findViewById(R.id.adminnnicNo);
        final TextView campusidTextView = (TextView)findViewById(R.id.admincampusid);
        //final TextView covidTextView = (TextView)findViewById(R.id.admincampusid2);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                //Covid usercovid = snapshot.getValue(Covid.class);

                if(userProfile != null) {
                    String fullName = userProfile.FullName;
                    String email = userProfile.Email;
                    String nicNo = userProfile.NIC_No;
                    String campusid = userProfile.CampusID;

                    greetingTextView.setText("Welcome, " + campusid + "!");
                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);
                    nicNoTextView.setText(nicNo);
                    campusidTextView.setText(campusid);


                    covidview = (TextView) findViewById(R.id.admincampusid2);

                    quarentineview = (TextView)findViewById(R.id.admincampusid4);

                    reference = FirebaseDatabase.getInstance().getReference().child("Users CovidInfo").child(campusid + "c");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {

                            String name = datasnapshot.child("DoYouHaveCovid").getValue().toString();
                            switch (name) {
                                case "Yes":
                                    covidview.setText(name + " You have covid");
                                    break;

                                case "No":
                                    covidview.setText(name + " You haven't covid");
                                    break;

                                case "Early had":
                                    covidview.setText("Yes, But not now");
                                    break;

                            }


                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    vaccineview = (TextView)findViewById(R.id.admincampusid3);

                    reference = FirebaseDatabase.getInstance().getReference().child("Users VaccineInfo").child(campusid + "v");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {

                            String name = datasnapshot.child("DidYouGetAVaciine").getValue().toString();
                            switch (name) {
                                case "Only One":
                                    vaccineview.setText(" I'm Only One Vaccine Get");
                                    break;

                                case "Fully Vaccinated":
                                    vaccineview.setText(" I'm Fully Vaccinated");
                                    break;

                                case "Not Yet":
                                    vaccineview.setText("I'm Not Yet Get Any Vaccine");
                                    break;

                            }


                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    reference = FirebaseDatabase.getInstance().getReference().child("Users VaccineInfo").child(campusid + "q");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot datasnapshot) {

                            String name = datasnapshot.child("NowYouAreQuarentine").getValue().toString();
                            switch (name) {
                                case "Yes":
                                    quarentineview.setText("Yes, I'm Quarentine");
                                    break;

                                case "No":
                                    quarentineview.setText("No, I'm Not Quarentine");
                                    break;


                            }


                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(ProfileActivity.this,"Something wrong happened!",Toast.LENGTH_LONG).show();

            }
        });



    }
}