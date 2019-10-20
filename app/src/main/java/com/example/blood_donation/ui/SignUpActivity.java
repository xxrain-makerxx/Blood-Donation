package com.example.blood_donation.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blood_donation.CreateProfileActivity;
import com.example.blood_donation.MainActivity;
import com.example.blood_donation.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private static final String ADMIN_ID ="Admin" ;
    private Button buttonSignUp;
    private FirebaseAuth mAuth;
    private TextInputEditText tvEmail, tvPassword;
    private String email, password;

    private void startMain(FirebaseUser currentUser) {
        Intent intent=new Intent(this, CreateProfileActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();


        tvEmail = findViewById(R.id.login_email);
        tvPassword = findViewById(R.id.login_password);
        buttonSignUp=findViewById(R.id.signup);


        buttonSignUp.setOnClickListener(v -> {

            email=tvEmail.getText().toString().trim();
            password=tvPassword.getText().toString();
            if(!email.isEmpty() && !password.isEmpty())
            {
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        startMain(mAuth.getCurrentUser());
                    } else {
                        Toast.makeText(getApplicationContext(), "Error:"+task.getException(), Toast.LENGTH_LONG).show();
                    }
                });
            }else{
                Toast.makeText(getApplicationContext(), "Email and Password cannot be Empty", Toast.LENGTH_LONG).show();
            }
        });

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}
