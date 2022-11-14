package com.example.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{
    private  TextView banner,registerUser;
    private EditText editTextFullName, editTextNicNo,editTextCampusId, editTextEmail,editTextPassword;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.signIn);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.adminnfullName);
        editTextNicNo = (EditText) findViewById(R.id.adminnnicNo);
        editTextCampusId = (EditText) findViewById(R.id.campusID);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);


    }

    public String email;
    public String password;
    public String fullname;
    public String nicno;
    public String campusid;



    public void signIn() {

         email = editTextEmail.getText().toString().trim();
         password = editTextPassword.getText().toString().trim();
         fullname = editTextFullName.getText().toString().trim();
         nicno = editTextNicNo.getText().toString().trim();
         campusid = editTextCampusId.getText().toString().trim();


        if(fullname.isEmpty()){
            editTextFullName.setError("Full Name is Required!");
            editTextFullName.requestFocus();
            return;
        }

        if(nicno.isEmpty()){
            editTextNicNo.setError("NIC NO is Required!");
            editTextNicNo.requestFocus();
            return;
        }

        if(campusid.isEmpty()){
            editTextCampusId.setError("Campus ID is Required!");
            editTextCampusId.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email is Required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Provide a valid email!");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is Required!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Min password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }



        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {

                        if (fullname.length() != 0 && email.length() != 0 && password.length() != 0 && campusid.length() != 0 && nicno.length() != 0 ){
                            if (task.isSuccessful()) {
                                User user = new User(fullname, nicno, campusid, email);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(RegisterUser.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);

                                            // redirected to login layout
                                        } else {
                                            Toast.makeText(RegisterUser.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(RegisterUser.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    }
                });


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.signIn:
                signIn();
                if (fullname.length() != 0 && email.length() != 0 && password.length() != 0 && campusid.length() != 0 && nicno.length() != 0 ) {
                    startActivity(new Intent(RegisterUser.this, Update.class));
                }
                break;
        }

    }
}