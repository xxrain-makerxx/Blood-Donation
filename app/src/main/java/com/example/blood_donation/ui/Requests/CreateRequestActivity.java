package com.example.blood_donation.ui.Requests;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blood_donation.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateRequestActivity extends AppCompatActivity {
    TextInputEditText name,place,blood,message,phone;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_requests);
        save=findViewById(R.id.req_save);
        name=findViewById(R.id.req_name);
        place=findViewById(R.id.req_place);
        blood=findViewById(R.id.req_blood);
        message=findViewById(R.id.req_message);
        phone=findViewById(R.id.req_phone);
        RequestsModel model=new RequestsModel();
        save.setOnClickListener(v->{
            model.setBlood(blood.getText().toString());
            model.setMessage(message.getText().toString());
            model.setName(name.getText().toString());
            model.setPlace(place.getText().toString());
            model.setPhone(phone.getText().toString());

            String city= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("City","Delhi");
            model.setCity(city);
            FirebaseFirestore.getInstance().collection("Requests").document().set(model);
            Toast.makeText(this,"Done",Toast.LENGTH_LONG).show();
            finish();

        });

    }
}
