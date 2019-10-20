package com.example.blood_donation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class CreateProfileActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    EditText name,age,phone,city,blood;
    Button save;
    int show=0;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        name=findViewById(R.id.profile_name);
        age=findViewById(R.id.profile_age);
        city=findViewById(R.id.profile_city);
        phone=findViewById(R.id.profile_phone);
        blood=findViewById(R.id.profile_blood);
        save=findViewById(R.id.profile_save);

        firebaseFirestore=FirebaseFirestore.getInstance();

        save.setOnClickListener(v->{
            ProfileModel model=new ProfileModel();
            model.setName(name.getText().toString());
            model.setAge(age.getText().toString());
            model.setCity(city.getText().toString());
            model.setPhone(phone.getText().toString());
            model.setUid(FirebaseAuth.getInstance().getUid());
            model.setBlood(blood.getText().toString());
            model.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
            firebaseFirestore.collection("Users").document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).set(model);
            Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,MainActivity.class));
            finish();
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
            editor.putString("City",city.getText().toString()).apply();
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(show==1){
            Toast.makeText(this,"You need to complete this",Toast.LENGTH_LONG).show();
        }
        show=1;
    }
}
