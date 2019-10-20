package com.example.blood_donation.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blood_donation.MainActivity;
import com.example.blood_donation.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private static final String ADMIN_ID = "Admin";
    private Button buttonLogin,buttonSignUp;
    private FirebaseAuth mAuth;
    private TextInputEditText tvEmail, tvPassword;
    private String email, password;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            startMain(currentUser);

        }
    }

    private void startMain(FirebaseUser currentUser) {
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();


        tvEmail = findViewById(R.id.login_email);
        tvPassword = findViewById(R.id.login_password);
        buttonLogin = findViewById(R.id.login);
        buttonSignUp=findViewById(R.id.signup);


        buttonLogin.setOnClickListener(v -> {
            email=tvEmail.getText().toString().trim();
            password=tvPassword.getText().toString();
            if(!email.isEmpty() && !password.isEmpty())
            {
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startMain(mAuth.getCurrentUser());
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_LONG).show();
                    }
                });}
            else {
                Toast.makeText(getApplicationContext(), "Email and Password cannot be empty", Toast.LENGTH_LONG).show();
            }
        });
        buttonSignUp.setOnClickListener(v -> {
            startActivity(new Intent(this,SignUpActivity.class));
            finish();
        });


    }









}
