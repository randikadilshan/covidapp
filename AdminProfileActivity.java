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

public class AdminProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private  String userID;
    private Button Logout,ShowDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        Logout=(Button)findViewById(R.id.signOut);
        ShowDetails=(Button)findViewById(R.id.btnShow);

        Logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AdminProfileActivity.this,MainActivity.class));
            }
        });

        ShowDetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AdminProfileActivity.this,StList.class));
            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Admins");
        userID = user.getUid();

        final TextView greetingTextView = (TextView)findViewById(R.id.admingreeting);
        final TextView fullNameTextView = (TextView)findViewById(R.id.adminnfullName);
        final TextView emailTextView = (TextView)findViewById(R.id.adminemailAddress);
        final TextView nicNoTextView = (TextView)findViewById(R.id.adminnnicNo);
        final TextView campusidTextView = (TextView)findViewById(R.id.admincampusid);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                Admin userProfile = snapshot.getValue(Admin.class);

                if(userProfile != null){
                    String fullName = userProfile.FullName;
                    String email = userProfile.Email;
                    String nicNo = userProfile.NIC_No;
                    String campusid = userProfile.Lecture_ID;

                    greetingTextView.setText("Welcome, " + campusid + "!");
                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);
                    nicNoTextView.setText(nicNo);
                    campusidTextView.setText(campusid);
                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(AdminProfileActivity.this,"Something wrong happened!",Toast.LENGTH_LONG).show();

            }
        });
    }
}